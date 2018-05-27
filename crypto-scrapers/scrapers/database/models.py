from datetime import datetime

from sqlalchemy import Column, Date, DateTime, Float, String, Integer

from scrapers.database.connection import Base, engine


class CMCHistoricData(Base):
    __tablename__ = 'cmc_historic_data'
    id = Column(Integer, primary_key=True)
    date = Column(Date, index=True)
    coin = Column(String(10), index=True)
    open_price = Column(Float)
    high_price = Column(Float)
    low_price = Column(Float)
    close_price = Column(Float)
    volume = Column(Float)
    market_cap = Column(Float)

    def __init__(self, date=None,
                 open_price=None,
                 high_price=None,
                 low_price=None,
                 close_price=None,
                 volume=None,
                 market_cap=None,
                 coin=None):
        self.date = date
        self.open_price = open_price
        self.high_price = high_price
        self.low_price = low_price
        self.close_price = close_price
        self.volume = volume
        self.market_cap = market_cap
        self.coin = coin


class CoinGeckoHistoricData(Base):
    __tablename__ = 'coin_gecko_historic_data'
    rank = Column(Integer, primary_key=True)
    date = Column(DateTime, primary_key=True)
    coin_symbol = Column(String(10), index=True)
    coin_name = Column(String(50))
    coin_algorithm = Column(String(50))
    price = Column(Float)
    market_cap = Column(Float)
    liquidity = Column(Float)
    developer_score = Column(Float)
    community_score = Column(Float)
    public_interest_score = Column(Float)
    total_score = Column(Float)
    twenty_four_hour_change = Column(Float)

    def __init__(self, rank=None,
                 coin_symbol=None,
                 coin_name=None,
                 coin_algorithm=None,
                 price=None,
                 market_cap=None,
                 liquidity=None,
                 developer_score=None,
                 community_score=None,
                 public_interest_score=None,
                 total_score=None,
                 twenty_four_hour_change=None
                 ):
        self.rank = rank
        self.date = datetime.utcnow().replace(second=0).replace(microsecond=0).replace(minute=0)
        self.coin_symbol = coin_symbol
        self.coin_name = coin_name
        self.coin_algorithm = coin_algorithm
        self.price = price
        self.market_cap = market_cap
        self.liquidity = liquidity
        self.developer_score = developer_score
        self.community_score = community_score
        self.public_interest_score = public_interest_score
        self.total_score = total_score
        self.twenty_four_hour_change = twenty_four_hour_change


Base.metadata.create_all(engine)
