# This package will contain the spiders of your Scrapy project
#
# Please refer to the documentation for information on how to create and manage
# your spiders.
import scrapy
import re
from tutorial.items import TutorialItem
class CIASpider(scrapy.Spider):
    name = "CIA_Job"
    jobName = ''
    #allowed_domains = ["dmoz.org"]
    start_urls = [
        #"http://www.dmoz.org/Computers/Programming/Languages/Python/Books/",
        #"http://www.dmoz.org/Computers/Programming/Languages/Python/Resources/"
        "http://mo-2b83de737.mo.sap.corp:8080/view/All_CIA/"
    ]

    def parse(self, response):
    	print(response.url)
    	for tr in response.xpath('//tr[@id[starts-with(.,"job")]]'):
    		item = TutorialItem()
    		item['job_status'] = tr.xpath('td[1]/img/@alt').extract()
    		# successTstmp = tr.xpath('td[4]/text()').extract()[0]
    		# x=re.search('\\d.*min|\\d.*days',successTstmp)
    		item['job_name']=tr.xpath('td[4]/text()').extract()[0]
    		item['job_lastsuccess'] = tr.xpath('td[4]/text()').extract()
    		yield item

