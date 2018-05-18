# -*- coding: utf-8 -*-

import requests, random
from bs4 import BeautifulSoup
import json

payload = {"ip": "127.0.0.1", "port": "8080", "name": "Tools", "param1":"ftp"}
headers={'content-type': 'application/json'}
r = requests.post("http://localhost:8082/python/call", data=json.dumps(payload), headers=headers)
print r.text
#
# page = requests.get("http://data.eastmoney.com/dxf/detail/002157.html")
# soup = BeautifulSoup(page.text, 'lxml')
# div = soup.select("#page > div.main > div.framecontent > div.sitebody > div > div")
# print div