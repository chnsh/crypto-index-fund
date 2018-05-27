from locale import *

import scrapy

from scrapers.items import CoinGeckoItem

setlocale(LC_NUMERIC, '')


class CoinGeckoSpider(scrapy.Spider):
    name = "coin_gecko"
    start_urls = ['https://www.coingecko.com/en']
    custom_settings = {
        'ITEM_PIPELINES': {
            'scrapers.pipelines.CoinGeckoPipeline': 100,
        }
    }

    def parse(self, response):
        for row in response.css('#gecko-table tbody tr'):
            try:
                yield CoinGeckoItem(
                    rank=int(atoi(row.css('.table-number::text').extract_first().strip())),
                    twenty_four_hour_change=float(
                        atof(row.css('.td-change24h.change24h.stat-percent span::text').extract_first().strip()[0:-1])),
                    coin_symbol=row.css('.coin-name .coin-icon span::text').extract_first(),
                    coin_name=row.css('.coin-name .coin-content span::text').extract_first(),
                    coin_algorithm=row.css('.coin-name .coin-detail .coin-content small::text').extract_first(),
                    price=float(
                        atof(row.css('.td-price.price .currency-exchangable::text').extract_first().strip()[1:])),
                    market_cap=float(
                        atof(row.css('.td-market_cap.cap .currency-exchangable::text').extract_first().strip()[1:])),
                    liquidity=float(atof(
                        row.css('.td-liquidity_score.lit .currency-exchangable::text').extract_first().strip()[1:])),
                    developer_score=float(
                        atof(row.css('.td-developer_score.dev .percent::text').extract_first().strip()[0:-1])),
                    community_score=float(atof(
                        row.css('.td-community_score.community .percent::text').extract_first().strip()[0:-1])),
                    public_interest_score=float(atof(
                        row.css('.td-public_interest_score.pb-interest .percent::text').extract_first().strip()[0:-1])),
                    total_score=float(atof(row.css('.total .percent::text').extract_first().strip()[0:-1]))
                )
            except Exception as e:
                # The idea is that if some record has an exception,
                # it is a non-famous coin and the exception won't matter in indexing
                import logging
                logging.error("Exception in parsing coin gecko", e)

        next_page = response.css('ul.pagination li a[rel=next]::attr(href)').extract_first()
        if next_page is not None:
            next_page = response.urljoin(next_page)
            yield scrapy.Request(next_page, callback=self.parse)
