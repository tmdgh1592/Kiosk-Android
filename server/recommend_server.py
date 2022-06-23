from flask      import Flask, jsonify, request
from flask_restx import Api, Resource, reqparse, fields
import pymysql;
from sqlalchemy import create_engine, text
import configparser

config = configparser.ConfigParser()
config.read('server_config.ini', encoding='utf-8')
port = config['server_2']['port']
db_pass = config['server_2']['db_pass']


app = Flask(__name__)


api = Api(app, version='1.0', title='키오스크 주문 분석 API 문서', description='Swagger 문서', doc="/api-docs")
api = api.namespace('v1')

@app.route('/')
def hello_world():
    return 'Hello World!'


def select_best(age,gender) :
    best_menu = ""
    best_opt = ""
    ad_msg = [
        "상큼한 아이스티!", # 0 대
        "상큼한 아이스티!", # 10 대
        "20대를 힘차게 시험기간으로 보내고 있는 당신!\n화이팅하세요", # 20 대
        "달달한 라떼 한잔 어때요", # 30 대
        "카페인이 없어 저녁에 편한해요!", # 40 대
        "카페인이 없어 저녁에 편안해요!!", # 50 대
        "향긋한 캐모마일 어떠신가요??", # 60 대
        "향긋한 캐모마일 어떠신가요??", # 70 대
        "80대 추천 메시지", # 80 대
        "90대 추천 메시지", # 90 대

    ]

    default_menu = [
        "자몽에이드", # 메뉴
        "cold", #opt1
        "사장님이 추천! 시원한 자몽에이드~~!!" #메시지
    ]


    if age >= 100 :
        return {'menu':default_menu[0], 'opt1':default_menu[1], 'msg' : default_menu[2]}

    sql = "select menu, count(menu)  from log_order where age =" +str(age)+ " and gender = '" +gender +"'  group by menu having count(menu) > 1 order by count(menu) desc ;"
    db = pymysql.connect(host='localhost', user='root', db='kiosk', password=db_pass, charset='utf8')
    curs = db.cursor()
    curs.execute(sql)
    rows = curs.fetchall()
    ret = []
    for e in rows:
        temp = {'menu':e[0],'count':e[1]}
        print(temp)
        ret.append(temp)

    if len(ret) == 0 :
        db.commit()
        db.close()

        # 리턴값 없음
        #return {}

        #defualt 메뉴도 리턴가능
        return {'menu':default_menu[0], 'opt1':default_menu[1], 'msg' : default_menu[2]}


    best_menu = ret[0]['menu']

    sql = "select opt1, count(opt1)  from log_order where age =" +str(age)+ " and gender = '" +gender +"' and menu = '" + best_menu  +"'  group by opt1 having count(menu) >= 1 order by count(opt1) desc ;"
    print(sql)
    db = pymysql.connect(host='localhost', user='root', db='kiosk', password=db_pass, charset='utf8')
    curs = db.cursor()
    curs.execute(sql)
    rows = curs.fetchall()
    ret = []
    for e in rows:
        temp = {'opt1':e[0],'count':e[1]}
        print(temp)
        ret.append(temp)

    best_opt1 = ret[0]['opt1']

    db.commit()
    db.close()
    return{'menu':best_menu, 'opt1':best_opt1, 'msg' : ad_msg[age//10]}






def insert_order (age,gender, menu, opt1, opt2, opt3, in_out) :
    sql = "insert into log_order (age,gender,menu,opt1, opt2, opt3, in_out) values (" + str(age) + ", '" + gender + "', '" + menu + "', '" + opt1 + "', '"+opt2 +"', '"+ opt3+ "', '" +  in_out +"' );"
    print(sql)
    try:
        db = pymysql.connect(host='localhost', user='root', db='kiosk', password='pass8#$', charset='utf8')
        curs = db.cursor()
        curs.execute(sql)
        db.commit()
        db.close()
        return True
    except Exception as e:
        print(e)
        return False




@api.route('/best/<int:age>/<string:gender>',methods = ['GET','POST'])
class Best(Resource) :
    def get(self, age, gender) :
        best_menu = ""
        age = (age//10)*10
        print("get best" + str(age) + gender)
        # SQL get best menu
        best_menu = select_best(age,gender)

        body = {}
        body['best'] = best_menu;
        return body



order_body = reqparse.RequestParser()
order_body.add_argument('age',type = int, required = True ,location='json')
order_body.add_argument('gender',type = str, required = True, location='json')
order_body.add_argument('menu',type = str, required = True, location='json')
order_body.add_argument('opt1',type = str, required = True, location='json')
order_body.add_argument('opt2',type = str, required = True, location='json')
order_body.add_argument('opt3',type = str, required = True, location='json')
order_body.add_argument('in_out',type = str, required = True, location='json')
order_body.add_argument('num',type = int, required = True, location='json')

@api.route('/order',methods = ['POST', "GET"])
class Order(Resource) :
    @api.expect(order_body)
    def post(self) :

        order_flag = False;

        age = request.json['age']
        gender = request.json['gender']
        menu = request.json['menu']
        opt1 = request.json['opt1']
        opt2 = request.json['opt2']
        opt3 = request.json['opt3']
        in_out = request.json['in_out']
        num = request.json['num']

        age = (age//10)*10

        print("post order" + str(age) + gender + in_out + menu);

        #SQL log data insert
        for _ in range(num) :
            order_flag = insert_order(age,gender,menu, opt1, opt2, opt3, in_out)
            if order_flag == False :
                body = {}
                body["success"] = order_flag;
                return body


        body = {}
        body["success"] = order_flag;
        return body

    def get(self) :
        sql = "select * from log_order order by id desc"
        print(sql)
        db = pymysql.connect(host='localhost', user='root', db='kiosk', password=db_pass, charset='utf8')
        curs = db.cursor()
        curs.execute(sql)
        rows = curs.fetchall()
        ret = []
        for e in rows:
            temp = {'주문시간':str(e[8]),'나이':e[1],'성별':e[2],'메뉴':e[3],'옵션1':e[4],'옵션2':e[5],'옵션3':e[6], '매장' : e[7]}
            ret.append(temp)

        db.commit()
        db.close()
        return ret;




if __name__ == '__main__':
    app.run(host='0.0.0.0', port=port)