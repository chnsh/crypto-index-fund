import scrapy


class CoinMarketCapItem(scrapy.Item):
    date = scrapy.Field(serializer=str)
    open_price = scrapy.Field()
    high_price = scrapy.Field()
    low_price = scrapy.Field()
    close_price = scrapy.Field()
    volume = scrapy.Field()
    market_cap = scrapy.Field()
    coin = scrapy.Field()


class CoinGeckoItem(scrapy.Item):
    rank = scrapy.Field()
    twenty_four_hour_change = scrapy.Field()
    coin_symbol = scrapy.Field()
    coin_name = scrapy.Field()
    coin_algorithm = scrapy.Field()
    price = scrapy.Field()
    market_cap = scrapy.Field()
    liquidity = scrapy.Field()
    developer_score = scrapy.Field()
    community_score = scrapy.Field()
    public_interest_score = scrapy.Field()
    total_score = scrapy.Field()
