from unittest import TestCase

from injector import Injector
from scrapers.utils import ApiAccessor, UrlListGenerator


class TestCMCApiResponse(TestCase):
    def test_api(self):
        currency_ids = Injector().get(ApiAccessor).get_currency_ids()
        self.assertIsNotNone(currency_ids)
        self.assertTrue(len(currency_ids) == 1398)


class TestUrlListGeneration(TestCase):
    def test_generation(self):
        url_list = Injector().get(UrlListGenerator).generate_cmc_url_list()
        self.assertIsNotNone(url_list)
        self.assertTrue(len(url_list) == 1398)
