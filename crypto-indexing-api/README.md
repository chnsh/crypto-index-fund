# Crypto Indexing API

This micro-service listening on `/api/index` queries raw data that has been stored in MySQL, calculates the index fund and the average volatility and returns a JSON response:


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


### Installation And Running

```sh
mvn build package
docker build . -t crypto-indexing-api
docker run -e "SPRING_DATASOURCE_URL=JDBC_URL" -e "SPRING_DATASOURCE_USERNAME=USER" -e "SPRING_DATASOURCE_PASSWORD=PASS" -p 8080:8080 -d crypto-indexing-api
```

License
----
MIT
