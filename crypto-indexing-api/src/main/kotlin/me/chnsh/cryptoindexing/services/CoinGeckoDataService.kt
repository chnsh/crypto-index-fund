package me.chnsh.cryptoindexing.services

import me.chnsh.cryptoindexing.entities.CoinGeckoHistoricData
import me.chnsh.cryptoindexing.repositories.CoinGeckoHistoricDataRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * author: Chintan Shah
 * project: crypto-indexing
 * date: 29/1/18
 */
@Service
class CoinGeckoDataService @Autowired constructor(private val coinGeckoHistoricDataRepository: CoinGeckoHistoricDataRepository) {
    fun fetchTopNCoinsAfterDate(numCoins: Int, date: Date): List<CoinGeckoHistoricData> {
        return coinGeckoHistoricDataRepository.fetchTopNCoinsAfterDate(
            numCoins,
            date
        )
    }
}