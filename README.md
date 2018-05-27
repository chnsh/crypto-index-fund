# Crypto Index Fund API

Crypto Indexing is a passive investing strategy, and this is my attempt to hack an investing system together. This very basic system lists the top 5 cryptocurrencies to include in the portfolio according to an equi-weighted strategy.

I have attempted to avoid the market-cap bias by using data provided by the amazing CoinGecko.com. The idea is to achieve a **Value Investing**-styled strategy by trying to assess the overall quality of a coin. 

CoinGecko.com does an outstanding job of ranking a cryptocurrency in a wholistic fashion by taking into account metrics like Development, Community Interactions, and a Publicity Score. 

### Architecture

The system consists of 2 micro-services:

* **crypto-scrapers** : This is a Python-based scrapy project that crawls [CoinGecko.com] and persists the data through SQLAlchemy
* **crypto-indexing**: This is a Kotlin-based Spring Boot microservice that serves as the API for the crypto-fund

### Installation

### Development

Want to contribute? Great! Feel free to send a request

### Docker

### Todos

 - Write MORE Tests
 - Price prediction models that helps to decide the exact time to invest
 - Automated order placing service that takes the output of the **crypto-indexing** API and places an order with any exchange of choice

License
----

MIT



[//]: 
   [CoinGecko.com]: <http://coingecko.com>
