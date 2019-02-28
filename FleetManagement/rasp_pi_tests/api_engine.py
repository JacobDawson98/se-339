import requests
# import json - never used
import time
from config import ConfigStore
from threading import Thread


class API_Engine(Thread):
    class __Singleton:
        def __init__(self):
            pass

    instance = None

    def __init__(self):
        super(API_Engine, self).__init__()
        if not API_Engine.instance:
            API_Engine.instance = API_Engine.__Singleton()
            self.instance.log = False
            self.instance.send = False
            self.instance.pid_data = {}
            self.instance.pos_data = {}

    def run(self):
        while True:
            print('*****send data *****')
            time.sleep(1)
            if self.instance.pid_data or self.instance.pos_data:
                pids = []
                for pid in self.instance.pid_data.items():
                    pids.append({'pid': pid[0], 'data': pid[1]})
                pid_body = {'vehicleId': ConfigStore().get_vid(), 'pids': pids}
                print('PID body' + str(pid_body))
                if self.instance.pos_data:
                    pid_body['latititude'] = self.instance.pos_data['latitude']
                    pid_body['longitude'] = self.instance.pos_data['longitude']
                result = self.do_request('post',
                                         url=ConfigStore().get_server_uri() + ':8082' + '/usage/add',
                                         body=pid_body)
                print('++Send data++' + str(result.json()))
                self.instance.pid_data = {}
                self.instance.pos_data = {}

    def set_logging(self, log):
        self.instance.log = log

    def set_send(self, send):
        self.instance.send = send

    def pid_send(self, pid, data):
        self.instance.pid_data[pid] = data

    def pos_send(self, lat, lon):
        self.instance.pos_data = {'latitude': lat, 'longitude': lon}

    def get_vehicle(self):
        result = self.do_request('get', url=ConfigStore().get_server_uri() + ':8080/vehicle/' + ConfigStore().get_vid())
        print('hello', result.json())
        return result.json()

    def do_request(self, type, url, body={}):  # NOQA
        response = ''
        if self.instance.log:
            print(type + ' ' + url + ' ' + str(body))
            time.sleep(.5)
        if self.instance.send:
            if type == 'put':
                response = requests.put(url=url, json=body)
            elif type == 'post':
                response = requests.post(url=url, json=body)
            elif type == 'delete':
                if body:
                    response = requests.delete(url=url, json=body)
                else:
                    response = requests.delete(url=url)
            elif type == 'get':
                response = requests.get(url=url)
                print('DEBUG:url', url, 'response ', response)
        return response
