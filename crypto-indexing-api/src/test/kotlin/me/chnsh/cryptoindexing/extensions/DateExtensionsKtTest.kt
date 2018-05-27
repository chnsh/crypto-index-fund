package me.chnsh.cryptoindexing.extensions

import org.junit.Assert
import org.junit.Test
import java.util.*

/**
 * author: Chintan Shah
 * project: crypto-indexing
 * date: 30/1/18
 */
class DateExtensionsKtTest {

    @Test
    fun clearMinutes() {
        val date = Date().with(2018, Calendar.JANUARY, 21, 10, 10, 10, 0)
        Assert.assertEquals(
            Date().with(2018, Calendar.JANUARY, 21, 10, 0, 0, 0),
            date.clearMinutes()
        )
    }

    @Test
    fun clearTime() {
        val date = Date().with(2018, Calendar.JANUARY, 21, 10, 10, 10, 0)
        Assert.assertEquals(
            Date().with(2018, Calendar.JANUARY, 21, 0, 0, 0, 0),
            date.clearTime()
        )
    }

    @Test
    fun lastWeek() {
        val date = Date().with(2018, Calendar.JANUARY, 21, 10, 10, 10, 0)
        Assert.assertEquals(
            Date().with(2018, Calendar.JANUARY, 14, 10, 10, 10, 0),
            date.lastWeek()
        )
    }

    @Test
    fun dateApplyTest() {
        val date = Date().with(2018, Calendar.JANUARY, 21, 10, 10, 10, 0)
        Assert.assertEquals(
            Date().with(2018, Calendar.JANUARY, 14, 0, 0, 0, 0),
            date.clearTime().lastWeek()
        )
    }
}