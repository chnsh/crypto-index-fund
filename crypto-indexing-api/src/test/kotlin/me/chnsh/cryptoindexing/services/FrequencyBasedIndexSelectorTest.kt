package me.chnsh.cryptoindexing.services

import me.chnsh.cryptoindexing.entities.CryptoSymbol
import me.chnsh.cryptoindexing.extensions.clearTime
import me.chnsh.cryptoindexing.extensions.lastWeek
import me.chnsh.cryptoindexing.extensions.with
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

/**
 * author: Chintan Shah
 * project: crypto-indexing
 * date: 2/2/18
 */
@SpringBootTest
@RunWith(SpringRunner::class)
class FrequencyBasedIndexSelectorTest {

    @Autowired
    lateinit var indexSelector: IndexSelector
    @Autowired
    lateinit var coinGeckoDataService: CoinGeckoDataService

    @Test
    fun `select with varying frequencies`() {
        val date = Date().with(2018, Calendar.JANUARY, 28)
        val coinList = coinGeckoDataService.fetchTopNCoinsAfterDate(5, date.clearTime().lastWeek())
        val index = indexSelector.select(
            coinList.filter {
                it.coinSymbol != CryptoSymbol.ETH
                        && it.coinSymbol != CryptoSymbol.XRP
            }
                    + coinList
                .filter { it.coinSymbol == CryptoSymbol.ETH }
                .map { it.copy(it.id.copy(rank = 3)) }
                    +
                    coinList.filter { it.coinSymbol == CryptoSymbol.XRP }
                        .dropLast(1)

        )
        Assert.assertTrue(!index.containsKey(2))
        Assert.assertTrue(index[3]!!.coinSymbol == CryptoSymbol.ETH)
    }

    @Test
    fun `select with same frequency`() {
        val date = Date().with(2018, Calendar.JANUARY, 28)
        val coinList = coinGeckoDataService.fetchTopNCoinsAfterDate(5, date.clearTime().lastWeek())
        val index = indexSelector.select(
            coinList.filter {
                it.coinSymbol != CryptoSymbol.ETH
                        && it.coinSymbol != CryptoSymbol.XRP
            }
                    + coinList
                .filter { it.coinSymbol == CryptoSymbol.ETH }
                .map { it.copy(it.id.copy(rank = 3)) }
                    +
                    coinList.filter { it.coinSymbol == CryptoSymbol.XRP }

        )
        Assert.assertTrue(!index.containsKey(2))
        Assert.assertTrue(index[3]!!.coinSymbol == CryptoSymbol.ETH)
    }
}