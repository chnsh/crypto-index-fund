from datetime import datetime
from locale import *

import scrapy
from injector import Injector

from scrapers.items import CoinMarketCapItem
from scrapers.utils import UrlListGenerator

setlocale(LC_NUMERIC, '')


class CoinMarketCapSpider(scrapy.Spider):
    name = "cmc"
    custom_settings = {
        'ITEM_PIPELINES': {
            'scrapers.pipelines.CMCPipeline': 100,
        }
    }

    def start_requests(self):
        for url in Injector().get(UrlListGenerator).generate_cmc_url_list():
            yield scrapy.Request(url=url, callback=self.parse)

    def parse(self, response):
        coin = response.css('h1.text-large small::text') \
            .extract_first() \
            .replace('(', '') \
            .replace(')', '')
        for row in response.css('table tbody tr'):
            data = row.css('td::text').extract()
            yield CoinMarketCapItem(
                date=datetime.strptime(data[0], '%b %d, %Y').date(),
                open_price=atof(data[1]) if data[1] != '-' else None,
                high_price=atof(data[2]) if data[2] != '-' else None,
                low_price=atof(data[3]) if data[3] != '-' else None,
                close_price=atof(data[4]) if data[4] != '-' else None,
                volume=atof(data[5]) if data[5] != '-' else None,
                market_cap=atof(data[6]) if data[6] != '-' else None,
                coin=coin
            )
