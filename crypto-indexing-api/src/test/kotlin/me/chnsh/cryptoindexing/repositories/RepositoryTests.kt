package me.chnsh.cryptoindexing.repositories

import me.chnsh.cryptoindexing.entities.CoinGeckoHistoricDataID
import me.chnsh.cryptoindexing.entities.CryptoSymbol
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
 * date: 28/1/18
 */

@SpringBootTest
@RunWith(SpringRunner::class)
class RepositoryTests {
    @Autowired
    lateinit var coinGeckoHistoricDataRepository: CoinGeckoHistoricDataRepository

    @Test
    fun `test if coin gecko response arrives through jpa`() {
        val coinGeckoData = coinGeckoHistoricDataRepository.findById(
            CoinGeckoHistoricDataID(
                1,
                Date().with(2018, Calendar.JANUARY, 28, 15, 0, 0, 0)
            )
        )
        Assert.assertTrue(coinGeckoData.isPresent)
        Assert.assertEquals(coinGeckoData.get().coinSymbol, CryptoSymbol.BTC)
    }
}