package me.chnsh.cryptoindexing.services

import me.chnsh.cryptoindexing.entities.CoinGeckoHistoricData
import me.chnsh.cryptoindexing.entities.CoinGeckoHistoricDataID
import me.chnsh.cryptoindexing.entities.CryptoSymbol
import me.chnsh.cryptoindexing.entities.CryptoSymbolAndVolatility
import org.springframework.stereotype.Service


/**
 * author: Chintan Shah
 * project: crypto-indexing
 * date: 29/1/18
 */
@Service
class FrequencyBasedIndexSelector : IndexSelector {
    /**
     *  Given the parameters, computes  [CoinGeckoHistoricDataID.rank] to [CryptoSymbol]
     *  using [topRankingSymbols] and then calculates average volatility
     *
     *
     *  @param coinGeckoHistoricDataList List of Coin Gecko Historic Datum for multiple days
     *
     */
    override fun select(coinGeckoHistoricDataList: List<CoinGeckoHistoricData>): Map<Int, CryptoSymbolAndVolatility> {
        return coinGeckoHistoricDataList
            .groupBy { it.coinSymbol }
            .let {
                topRankingSymbols(coinGeckoHistoricDataList)
                    .mapIndexed { index, cryptoSymbol ->
                        index to CryptoSymbolAndVolatility(
                            cryptoSymbol,
                            it[cryptoSymbol]!!.let {
                                it.sumByDouble { it.twentyFourHourChange }.div(it.size)
                            }
                        )
                    }
                    .toMap()
            }
    }

    /**
     *  Selects symbols by their frequency of occurrence in the top rankings
     *
     *  It groups by rank, then groups by symbol, then maps the 2nd group to
     *  the size of list in rank, symbol group. This size is actually the frequency.
     *
     *  After mapping to size, it selects higher frequency currency if available else
     *  selects the currency with the recent highest total score and then again
     *  ungroups in order to create final mapping of
     *  [CoinGeckoHistoricDataID.rank] to [CryptoSymbol]
     *
     *
     *  @param coinGeckoHistoricDataList List of Coin Gecko Historic Datum for multiple days
     *
     */
    private fun topRankingSymbols(coinGeckoHistoricDataList: List<CoinGeckoHistoricData>): List<CryptoSymbol> {
        return coinGeckoHistoricDataList
            .groupBy { it.id.rank }
            .mapValues {
                it.value
                    .groupBy { it.coinSymbol }
                    .maxWith(Comparator { o1, o2 ->
                        if (o1.value.size - o2.value.size != 0)
                            o1.value.size - o2.value.size
                        else
                            (o1.value.last().totalScore
                                    - o2.value.last().totalScore).toInt()

                    })
            }
            .mapValues { it.value!!.key }
            .values
            .toList()
    }
}