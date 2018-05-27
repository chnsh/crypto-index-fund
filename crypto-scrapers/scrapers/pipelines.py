# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html
from scrapers.database.connection import db
from scrapers.database.models import CMCHistoricData, CoinGeckoHistoricData


class CMCPipeline(object):

    def process_item(self, item, spider):
        record = CMCHistoricData(date=item['date'],
                                 open_price=item['open_price'],
                                 high_price=item['high_price'],
                                 low_price=item['low_price'],
                                 close_price=item['close_price'],
                                 market_cap=item['market_cap'],
                                 volume=item['volume'],
                                 coin=item['coin']
                                 )
        db.add(record)
        db.commit()
        return item


class CoinGeckoPipeline(object):

    def process_item(self, item, spider):
        record = CoinGeckoHistoricData(rank=item['rank'],
                                       coin_symbol=item['coin_symbol'],
                                       coin_name=item['coin_name'],
                                       coin_algorithm=item['coin_algorithm'],
                                       price=item['price'],
                                       market_cap=item['market_cap'],
                                       liquidity=item['liquidity'],
                                       developer_score=item['developer_score'],
                                       community_score=item['community_score'],
                                       public_interest_score=item['public_interest_score'],
                                       total_score=item['total_score'],
                                       twenty_four_hour_change=item['twenty_four_hour_change']
                                       )
        db.add(record)
        db.commit()
        return item
