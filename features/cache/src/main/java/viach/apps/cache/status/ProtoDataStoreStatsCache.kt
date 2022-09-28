package viach.apps.cache.status

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import viach.apps.cache.StatsPreferences
import java.io.IOException

class ProtoDataStoreStatsCache(
    private val stats: Stats
) : StatsCache {

    override val all: Flow<StatsPreferences>
        get() = stats.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(StatsPreferences.getDefaultInstance())
                } else {
                    throw exception
                }
            }

    override fun addEasyWinLossPoint(win: Boolean): Job = stats.update {
        if (win) {
            setEasyModeWinsCount(stats.currentData.easyModeWinsCount + 1)
        } else {
            setEasyModeLossesCount(stats.currentData.easyModeLossesCount + 1)
        }
    }

    override fun addNormalWinLossPoint(win: Boolean): Job = stats.update {
        if (win) {
            setNormalModeWinsCount(stats.currentData.normalModeWinsCount + 1)
        } else {
            setNormalModeLossesCount(stats.currentData.normalModeLossesCount + 1)
        }
    }

    override fun addHardWinLossPoint(win: Boolean): Job = stats.update {
        if (win) {
            setHardModeWinsCount(stats.currentData.hardModeWinsCount + 1)
        } else {
            setHardModeLossesCount(stats.currentData.hardModeLossesCount + 1)
        }
    }

    override fun clear(): Job =
        stats.update { clear() }
}