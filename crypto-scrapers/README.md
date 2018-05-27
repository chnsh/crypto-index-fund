# Crypto Scrapers

This micro-service currently implements a CoinGecko.com scraper, and persists that data into MySQL. 
### Installation

```sh
docker build . -t crypto-scrapers
docker run -e "DB_USER=USER" -e "DB_PASS=PASS "DB_HOST=HOST" crypto-scrapers scrapy crawl coin_gecko
```

License
----
MIT
