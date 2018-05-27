package me.chnsh.cryptoindexing.repositories

import me.chnsh.cryptoindexing.entities.CoinGeckoHistoricData
import me.chnsh.cryptoindexing.entities.CoinGeckoHistoricDataID
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.*

/**
 * author: Chintan Shah
 * project: crypto-indexing
 * date: 28/1/18
 */
interface CoinGeckoHistoricDataRepository :
    CrudRepository<CoinGeckoHistoricData, CoinGeckoHistoricDataID> {
    @Query("select c from CoinGeckoHistoricData c where c.id.rank <= :numCoins and c.id.date >= :date ")
    fun fetchTopNCoinsAfterDate(@Param("numCoins") numCoins: Int, @Param("date") date: Date): List<CoinGeckoHistoricData>
}