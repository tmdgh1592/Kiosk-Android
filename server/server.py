from flask import Flask, request
from flask_restx import Api, Resource, reqparse
from PIL import Image
from io import BytesIO
from urllib.parse import urlencode, quote_plus

import requests
import json
import random
import os

app = Flask(__name__)

api = Api(app, version='1.0', title='키오스크 API 문서', description='Swagger 문서', doc="/api-docs")

api = api.namespace('v1')

@api.route('/')
class Test(Resource):
    def get(self):
        return 'Hello World!'

l = reqparse.RequestParser()
#server_addr = "http://" + server_addr + "/uploads/" + image.filename
server_addr = "https://www.sciencetimes.co.kr/wp-content/uploads/2018/10/fc08a3_ecc89ba4706a4199a9a51be9500037d0mv2_d_1754_2480_s_2.jpg"
l.add_argument('image', type=bytes, default=None, help="image file(PNG or JPG)")
@api.route('/detect', methods=['POST'])
class Detect(Resource):
    @api.expect(l)
    def post(self):
        # 클라에서 image(png or jpg)를 받아옴
        image = request.files['image']
        # XXX: multipart/form-data 관련 작동이 제대로 안됨. 
        # 카카오 서버에서 500 error만 나고 이유를 알려주지 않음
        # 따라서 먼저 서버에 이미지 저장을 해주고, 해당 url을 카카오 서버로 전송
        image.save(os.path.join('uploads/', image.filename))
        
        api_key = "<API-KEY>"
        api_url = "https://dapi.kakao.com/v2/vision/face/detect"
        headers, data = {}, {}
        headers['Content-Type'] = 'application/x-www-form-urlencoded'
        headers['Authorization'] = 'KakaoAK %s'%api_key
        
        threshold = 0.7
        data['threshold'] = threshold
        # TODO: url encode
        data["image_url"] = server_addr
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

            body["age"] = int(max_age)
            body["gender"] = max_gender
            #assert(max_age != -1)
            return body
        else:
            # TODO: status code?
            body = {}
            body["success"] = False
            return body

if __name__ == '__main__':
    app.run(host='0.0.0.0')