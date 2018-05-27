package me.chnsh.cryptoindexing.entities

import java.io.Serializable
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

/**
 * author: Chintan Shah
 * project: crypto-indexing
 * date: 28/1/18
 */

@Embeddable
data class CoinGeckoHistoricDataID(
    val rank: Int,
    @Temporal(TemporalType.TIMESTAMP)
    val date: Date
) : Serializable

@Entity
data class CoinGeckoHistoricData(
    @EmbeddedId val id: CoinGeckoHistoricDataID,
    @Enumerated(EnumType.STRING) val coinSymbol: CryptoSymbol, // Todo: Handle deserialization issues
    val coinName: String,
    val coinAlgorithm: String,
    val price: Double,
    val marketCap: BigDecimal,
    val liquidity: Double,
    val developerScore: Double,
    val communityScore: Double,
    val publicInterestScore: Double,
    val totalScore: Double,
    val twentyFourHourChange: Double
)