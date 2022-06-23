from flask import Flask, request, send_file
from flask_restx import Api, Resource, reqparse, fields
from PIL import Image
from io import BytesIO
from urllib.parse import urlencode, quote_plus
import werkzeug


import requests
import json
import random
import os
import uuid

app = Flask(__name__)
port = 5052

api = Api(app, version='1.0', title='키오스크 API 문서', description='Swagger 문서', doc="/api-docs")

api = api.namespace('v1')

@api.route('/')
class Test(Resource):
    def get(self):
        return 'Hello World!'

l = reqparse.RequestParser()
server_addr = "<ADDR>" + str(port)
l.add_argument('image', type=werkzeug.datastructures.FileStorage, default=None, required=True, location='files', help="image file(PNG or JPG)")
resp_model = api.model('', {
    'success': fields.Boolean,
    'age': fields.Integer,
    'gender': fields.String,
})
@api.route('/detect', methods=['POST'])
class Detect(Resource):
    @api.expect(l)
    @api.response(200, 'Success', resp_model)
    def post(self):
        print("started")
        # 클라에서 image(png or jpg)를 받아옴
        image = request.files['image']
        # XXX: multipart/form-data 관련 작동이 제대로 안됨.
        # 카카오 서버에서 500 error만 나고 이유를 알려주지 않음
        # 따라서 먼저 서버에 이미지 저장을 해주고, 해당 url을 카카오 서버로 전송
        filename = str(uuid.uuid4())
        image.save(os.path.join('uploads/', filename))
        print(filename)
        api_key = "<API Key>"
        api_url = "https://dapi.kakao.com/v2/vision/face/detect"
        headers, data = {}, {}
        headers['Content-Type'] = 'application/x-www-form-urlencoded'
        headers['Authorization'] = 'KakaoAK %s'%api_key

        threshold = 0.7
        data['threshold'] = threshold
        # TODO: url encode
        data["image_url"] = server_addr + "/uploads/?path=" + filename
        #print(data["image_url"])
        # kakao dev에 요청을 보냄
        r = requests.post(api_url, data=data, headers=headers)
        # 받아온 요청을 기반으로 클라로 보내줌.
        # NOTE(junsoo): 얼굴이 여러개 있을 때는 가장 늙은 사람의 정보 1개만 보내줌.
        if r.status_code == 200:
            body = {}
            result = json.loads(r.text)['result']
            print(result)

            # 모종의 사유로 얼굴이 인식되지 않은 경우 클라에서 retry
            if len(result["faces"]) == 0:
                body = {}
                body["success"] = False
                return body

            max_age, max_gender = -1, ""
            for i in range(len(result["faces"])):
                info = result["faces"][i]["facial_attributes"]
                if info["age"] > max_age:
                    max_age = info["age"]
                    m_f = result["faces"][i]["facial_attributes"]["gender"]
                    if m_f["male"] >= 0.5:
                        max_gender = "male"
                    else:
                        max_gender = "female"

            body["success"] = True
            body["age"] = int(max_age)
            body["gender"] = max_gender
            #assert(max_age != -1)
            return body
        else:
            # TODO: status code?
            body = {}
            body["success"] = False
            return body

@app.route('/uploads/')
def down_image():
    #print(request.view_args)
    path = request.args.get('path')
    return send_file("uploads/" + path)


if __name__ == '__main__':
    from waitress import serve
    serve(app, host='0.0.0.0', port=port)