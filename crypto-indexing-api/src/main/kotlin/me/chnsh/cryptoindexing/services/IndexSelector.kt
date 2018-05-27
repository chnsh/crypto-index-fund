package me.chnsh.cryptoindexing.services

import me.chnsh.cryptoindexing.entities.CoinGeckoHistoricData
import me.chnsh.cryptoindexing.entities.CryptoSymbolAndVolatility

/**
 * author: Chintan Shah
 * project: crypto-indexing
 * date: 2/2/18
 */
interface IndexSelector {
    fun select(coinGeckoHistoricDataList: List<CoinGeckoHistoricData>): Map<Int, CryptoSymbolAndVolatility>
}