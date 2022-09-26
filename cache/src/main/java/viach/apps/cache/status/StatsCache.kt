package viach.apps.cache.status

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import viach.apps.cache.StatsPreferences

interface StatsCache {
    val all: Flow<StatsPreferences>
    fun addEasyWinLossPoint(win: Boolean): Job
    fun addNormalWinLossPoint(win: Boolean): Job
    fun addHardWinLossPoint(win: Boolean): Job
    fun clear(): Job
}