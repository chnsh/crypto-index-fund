# Crypto Index Fund API

Crypto Indexing is a passive investing strategy, and this is my attempt to hack an investing system together. This very basic system lists the top 5 cryptocurrencies to include in the portfolio according to an equi-weighted strategy.

I have attempted to avoid the market-cap bias by using data provided by the amazing CoinGecko.com. The idea is to achieve a **Value Investing**-styled strategy by trying to assess the overall quality of a coin.

CoinGecko.com does an outstanding job of ranking a cryptocurrency in a wholistic fashion by taking into account metrics like Development, Community Interactions, and a Publicity Score.

### Output

```json
{
  "0": {
    "coinSymbol": "BTC",
    "averageVolatility": -1.6326956521739127
  },
  "1": {
    "coinSymbol": "ETH",
    "averageVolatility": -3.1893043478260865
  },
  "2": {
    "coinSymbol": "EOS",
    "averageVolatility": -2.628086956521738
  },
  "3": {
    "coinSymbol": "XRP",
    "averageVolatility": -1.9188695652173906
  },
  "4": {
    "coinSymbol": "LTC",
    "averageVolatility": -2.220526315789474
  }
}
```

### Why build something like this?

This is a simple experiment that I find interesting, and is by no means the best or the most scientific investing strategy available. I built this since I did not find any sufficiently open, self-service passive investing system.

### Disclaimer

I developed this for my personal use, and have been using the API to invest a very minor amount in cryptocurrencies. While I have been using it personally, I am no financial expert! Please use with caution.

### Architecture

The system consists of 2 micro-services:

* **crypto-scrapers** : This is a Python-based scrapy project that crawls [CoinGecko.com] and persists the data through SQLAlchemy
* **crypto-indexing**: This is a Kotlin-based Spring Boot microservice that serves as the API for the crypto-fund

### Installation

* Build and run the [cryto-scrapers](crypto-scrapers/README.md), and run the docker command in crontab with an hourly frequency
* Run the [crypto-indexing](crypto-indexing-api/README.md) API, and hit `/api/index` on the exposed port to fetch the fund after the scraper has run.

### Docker
Crypto-scrapers:
```sh
docker pull chnsh/crypto-scrapers [Hourly cron]
docker run -e "DB_USER=USER" -e "DB_PASS=PASS "DB_HOST=HOST" crypto-scrapers scrapy crawl coin_gecko
```

Crypto-indexing:
```sh
docker pull chnsh/crypto-indexing-api
docker run -e "SPRING_DATASOURCE_URL=JDBC_URL" -e "SPRING_DATASOURCE_USERNAME=USER" -e "SPRING_DATASOURCE_PASSWORD=PASS" -p 8080:8080 -d crypto-indexing-api
```


### Development

Want to contribute? Great! Feel free to send a request

### Todos
 - Use the coingecko API instead of scraping
 - Improve build and deployment workflow
 - Write MORE Tests
 - Price prediction models that helps to decide the exact time to invest
 - Automated order placing service that takes the output of the **crypto-indexing** API and places an order with any exchange of choice

License
----

MIT
