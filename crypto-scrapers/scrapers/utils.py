from datetime import date

from coinmarketcap import Market
from injector import inject


class ApiAccessor:
    @inject
    def __init__(self, market: Market):
        self._market = market

    def _get_all_currencies(self):
        return self._market.ticker(limit=0)

    def get_currency_ids(self):
        currencies = self._get_all_currencies()
        return list(map(lambda x: x['id'], currencies))


class UrlListGenerator:
    @inject
    def __init__(self, api_accessor: ApiAccessor):
        self.api_accessor = api_accessor

    def generate_cmc_url_list(self):
        base_url_string = "https://coinmarketcap.com/currencies/{}/historical-data/?start=20130428&end={}"
        currency_ids = self.api_accessor.get_currency_ids()
        url_list = []
        for currency_id in currency_ids:
            url_list.append(base_url_string.format(currency_id, date.today().strftime('%Y%m%d')))
        return url_list
