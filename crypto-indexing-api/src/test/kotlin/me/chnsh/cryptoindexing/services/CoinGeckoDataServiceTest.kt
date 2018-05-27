package me.chnsh.cryptoindexing.services

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
 * date: 30/1/18
 */
@SpringBootTest
@RunWith(SpringRunner::class)
class CoinGeckoDataServiceTest {

    @Autowired
    lateinit var coinGeckoDataService: CoinGeckoDataService

    @Test
    fun fetchTopNCoinsAfterDate() {
        val date = Date().with(2018, Calendar.JANUARY, 28)
        val coinList = coinGeckoDataService.fetchTopNCoinsAfterDate(5, date.clearTime().lastWeek())
        Assert.assertNotNull(coinList)
        Assert.assertEquals(
            Date().with(2018, Calendar.JANUARY, 21, 0, 0, 0, 0),
            coinList.minBy { it.id.date }!!.id.date
        )
    }
}