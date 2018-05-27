package me.chnsh.cryptoindexing.controllers

import me.chnsh.cryptoindexing.entities.CryptoSymbolAndVolatility
import me.chnsh.cryptoindexing.extensions.clearTime
import me.chnsh.cryptoindexing.extensions.lastWeek
import me.chnsh.cryptoindexing.services.CoinGeckoDataService
import me.chnsh.cryptoindexing.services.IndexSelector
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * author: Chintan Shah
 * project: crypto-indexing
 * date: 5/2/18
 */
@RestController
class IndexController @Autowired constructor(
    val indexSelector: IndexSelector,
    val coinGeckoDataService: CoinGeckoDataService
) {
    @GetMapping("/index")
    fun index(
        @RequestParam(
            "size",
            required = false,
            defaultValue = "5"
        ) size: Int
    ): Map<Int, CryptoSymbolAndVolatility> {
        val coinList = coinGeckoDataService.fetchTopNCoinsAfterDate(size, Date().clearTime().lastWeek())
        return indexSelector.select(coinList)
    }
}