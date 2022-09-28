package viach.apps.cache.stats

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import viach.apps.cache.StatsPreferences
import viach.apps.cache.status.ProtoDataStoreStatsCache
import viach.apps.cache.status.StatsCache
import viach.apps.cache.util.TestStats

@RunWith(AndroidJUnit4::class)
class ProtoDataStoreStatsCacheTest {

    @Test
    fun all_returnsAllData() = runBlocking {
        val cache = ProtoDataStoreStatsCache(TestStats())
        cache.all.first().assertEqualsDefault()
    }

    @Test
    fun addEasyWinLossPoint_addsEasyModeWin() = addWinLossPoint(
        setUpCache = { addEasyWinLossPoint(true) },
        providePoints = { easyModeWinsCount }
    )

    @Test
    fun addEasyWinLossPoint_addsLossModeWin() = addWinLossPoint(
        setUpCache = { addEasyWinLossPoint(false) },
        providePoints = { easyModeLossesCount }
    )

    @Test
    fun addNormalWinLossPoint_addsEasyModeWin() = addWinLossPoint(
        setUpCache = { addNormalWinLossPoint(true) },
        providePoints = { normalModeWinsCount }
    )

    @Test
    fun addNormalWinLossPoint_addsLossModeWin() = addWinLossPoint(
        setUpCache = { addNormalWinLossPoint(false) },
        providePoints = { normalModeLossesCount }
    )

    @Test
    fun addHardWinLossPoint_addsEasyModeWin() = addWinLossPoint(
        setUpCache = { addHardWinLossPoint(true) },
        providePoints = { hardModeWinsCount }
    )

    @Test
    fun addHardWinLossPoint_addsLossModeWin() = addWinLossPoint(
        setUpCache = { addHardWinLossPoint(false) },
        providePoints = { hardModeLossesCount }
    )

    @Test
    fun clear_returnsAllToDefaultValues() = runBlocking {
        val data = StatsPreferences.newBuilder()
            .setEasyModeLossesCount(1)
            .setEasyModeWinsCount(2)
            .setNormalModeLossesCount(1)
            .setNormalModeWinsCount(2)
            .setHardModeLossesCount(1)
            .setHardModeWinsCount(2)
            .build()
        val cache = ProtoDataStoreStatsCache(TestStats(data))
        cache.clear()
        cache.all.first().assertEqualsDefault()
    }

    private fun addWinLossPoint(
        setUpCache: StatsCache.() -> Unit,
        providePoints: TestStats.() -> Int
    ) {
        val stats = TestStats()
        val cache = ProtoDataStoreStatsCache(stats)
        setUpCache(cache)
        assertEquals(1, providePoints(stats))
    }

    private fun StatsPreferences.assertEqualsDefault() {
        val defaultStats = StatsPreferences.getDefaultInstance()
        if (easyModeWinsCount != defaultStats.easyModeWinsCount ||
                easyModeLossesCount != defaultStats.easyModeLossesCount ||
                normalModeWinsCount != defaultStats.normalModeWinsCount ||
                normalModeLossesCount != defaultStats.normalModeLossesCount ||
                hardModeWinsCount != defaultStats.hardModeWinsCount ||
                hardModeLossesCount != defaultStats.hardModeLossesCount) {
            throw IllegalArgumentException("StatsPreferences are not equal")
        }
    }
}