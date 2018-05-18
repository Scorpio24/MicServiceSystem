# coding: utf-8

import requests
from bs4 import BeautifulSoup
import random
import time
import sys
import os


class Tools(object):
    def __init__(self, proxy_list=[], sys_path=sys.path[0]):
        """
        初始化
        """
        self.__proxy_list = proxy_list
        self.__sys_path = sys_path
        self.__cache_url = ""
        self.__fast_cache_url = ""
        self.__baidu_url = "http://www.baidu.com/s?ie=utf-8&wd=西刺代理"

    def __get_cache_url(self):
        """
        获取西刺代理的百度快照url
        :return: 无
        """
        if self.__cache_url != "":
            return
        for i in range(5):
            page = requests.get(self.__baidu_url)
            if page.status_code != requests.codes.ok:
                continue
            else:
                break
        soup = BeautifulSoup(page.text, "lxml")
        body = soup.select_one("body")
        div = body.select_one('div[id="wrapper_wrapper"]')
        div = div.select_one('div[id="container"]')
        div = div.select_one('div[id="content_left"]')
        div_results = div.select('div[class="result c-container "]')
        for div in div_results:
            div_f13 = div.select_one('div[class="f13"]')
            if div_f13.select_one('a[class="c-showurl"]').text == u"www.xicidaili.com/ ":
                self.__cache_url = div_f13.select_one('a[class="m"]')["href"]
                break

    def __get_fast_cache_url(self):
        """
        获取西刺代理的百度快照快速版url
        :return: 无
        """
        if self.__fast_cache_url != "":
            return
        if self.__cache_url == "":
            self.__get_cache_url()
        for i in range(5):
            page = requests.get(self.__cache_url)
            page.encoding = "gb2312"
            if page.status_code != requests.codes.ok:
                continue
            else:
                break
        soup = BeautifulSoup(page.text, "lxml")
        body = soup.select_one("body")
        div = body.select_one('div[id="bd_snap"]')
        div = div.select_one('div[id="bd_snap_txt"]')
        a_list = div.select('a')
        for a in a_list:
            if a.text == u"快速版":
                self.__fast_cache_url = a["href"]
                break

    def get_proxy_list(self, test_url="http://ip.chinaz.com/", timeout=0.2, proxytype="http"):
        """
        从百度快照中爬取西刺代理的ip和端口
        :param test_url: 测试代理用的网站，建议换成需要爬取的网站的主域名
        :param debug: 是否输出信息
        :param timeout: 为测试代理是否可用的超时时间，建议0.2
        :return: 由形如{"https": "127.0.0.1"}的dict构成的代理list
        """
        if len(self.__proxy_list) != 0:
            return
        old_flag = False
        if os.path.exists(self.__sys_path + "/proxy.bak"):
            old_proxy_list = []
            with open(self.__sys_path + "/proxy.bak", "r") as fp:
                lines = fp.readlines()
            for one_line in lines:
                proxy = {one_line.split("#")[0]: one_line.split("#")[1][:-1], }
                old_proxy_list.append(proxy)
            old_flag = True
        url = 'http://www.xicidaili.com/'
        headers = {
            'Host': 'www.xicidaili.com',
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) '
                          'AppleWebKit/537.36 (KHTML, like Gecko) '
                          'Chrome/66.0.3359.117 Safari/537.36'
        }
        for j in range(3):
            if old_flag:
                proxy = old_proxy_list[random.randint(0, len(old_proxy_list) - 1)]
            else:
                proxy = None
            try:
                page = requests.get(url, headers=headers, proxies=proxy)
            except:
                continue
            if page.status_code != requests.codes.ok:
                continue
            else:
                soup = BeautifulSoup(page.text, 'lxml')
                div = soup.select_one('#body')
                tr_list = div.select('tr')
                for tr in tr_list:
                    td_list = tr.select('td')
                    if len(td_list) == 0:
                        continue
                    if td_list[5].string.lower() == proxytype:
                        proxy = {td_list[5].string.lower(): td_list[1].string + ':' + td_list[2].string, }
                        try:
                            requests.get(test_url, proxies=proxy, timeout=timeout)
                        except Exception:
                            continue
                        else:
                            self.__proxy_list.append(proxy)
                            if len(self.__proxy_list) > 10:
                                break
                            for _proxy in proxy:
                                print "\"%s\": \"%s\"" % (_proxy, proxy[_proxy])
            # if debug:
            #     print u'\n西刺代理爬取结束\n'
            if len(self.__proxy_list) <= 10:
                self.__proxy_list = old_proxy_list
                # print "网络故障，使用备用代理"
            else:
                if os.path.exists(self.__sys_path + "/proxy.bak"):
                    os.remove(self.__sys_path + "/proxy.bak")
                with open(self.__sys_path + "/proxy.bak", "a") as fp:
                    for proxy in self.__proxy_list:
                        for _proxy in proxy:
                            fp.write("%s#%s\n" % (_proxy, proxy[_proxy]))
            return self.__proxy_list

    def use_proxy_spider(self, url, proxy_list=[], encoding="utf-8", times=50, timeout=1, header = {}):
        """
        利用代理爬取url的page然后利用BeautifulSoup转换并返回
        :param url: 需要爬取的url
        :param proxy_list: 代理列表，默认使用类中的代理列表
        :param encoding: url中head中的编码
        :param times: 尝试爬取最多次数
        :param timeout: 超时时间
        :return: url的BeautifulSoup类型变量
        """
        if len(proxy_list) == 0:
            self.get_proxy_list()
            proxy_list = self.__proxy_list
        for i in range(times):
            proxy = proxy_list[random.randint(0, len(proxy_list) - 1)]
            time.sleep(0.3)
            try:
                if header:
                    page = requests.get(url, proxies=proxy, timeout=timeout)
                else:
                    page = requests.get(url, proxies=proxy, timeout=timeout, headers=header)
                page.encoding = encoding
            except Exception:
                continue
            if page.status_code != requests.codes.ok:
                continue
            soup = BeautifulSoup(page.text, 'lxml')
            return soup
        return None

    def formal_print_dict(self, dict_inf, tabs=0):
        for key in dict_inf:
            if isinstance(dict_inf[key], dict):
                print "\t" * tabs + "%s :" % key
                self.formal_print_dict(dict_inf[key], tabs + 1)
            else:
                print "\t" * tabs + ("%s : %s" % (key, dict_inf[key]))


if __name__ == "__main__":
    proxy = Tools()

    if len(sys.argv) == 1:
        proxy.get_proxy_list()
    elif len(sys.argv) > 1:
        proxytype = ""
        try:
            proxytype = sys.argv[1]
            if proxytype != "http" and proxytype != "https":
                print "Error: Wrong Param: " + proxytype
                exit(-1)
        except SystemExit:
            pass
        except Exception, e:
            print "Error: Wrong Param: " + proxytype
            exit(-1)
        proxy.get_proxy_list(proxytype=proxytype)
