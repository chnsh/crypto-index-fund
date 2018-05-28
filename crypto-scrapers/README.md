# Crypto Scrapers

This micro-service currently implements a CoinGecko.com scraper, and persists that data into MySQL. 

### Installation
```mysql
create database crypto_indexing;

CREATE TABLE `coin_gecko_historic_data`
  (
     `rank`                    INT(11) NOT NULL,
     `date`                    DATETIME NOT NULL,
     `coin_symbol`             VARCHAR(10) DEFAULT NULL,
     `coin_name`               VARCHAR(50) DEFAULT NULL,
     `coin_algorithm`          VARCHAR(50) DEFAULT NULL,
     `price`                   FLOAT DEFAULT NULL,
     `market_cap`              FLOAT DEFAULT NULL,
     `liquidity`               FLOAT DEFAULT NULL,
     `developer_score`         FLOAT DEFAULT NULL,
     `community_score`         FLOAT DEFAULT NULL,
     `public_interest_score`   FLOAT DEFAULT NULL,
     `total_score`             FLOAT DEFAULT NULL,
     `twenty_four_hour_change` FLOAT DEFAULT NULL,
     PRIMARY KEY (`rank`, `date`),
     KEY `ix_coin_gecko_historic_data_coin_symbol` (`coin_symbol`)
  )  
```


```sh
docker build . -t crypto-scrapers
docker run -e "DB_USER=USER" -e "DB_PASS=PASS "DB_HOST=HOST" crypto-scrapers scrapy crawl coin_gecko
```

License
----
MIT

