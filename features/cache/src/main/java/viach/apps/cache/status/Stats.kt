package viach.apps.cache.status

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import viach.apps.cache.StatsPreferences

interface Stats {
    val data: Flow<StatsPreferences>
    val currentData: StatsPreferences
    fun update(statsBuilder: suspend StatsPreferences.Builder.() -> StatsPreferences.Builder): Job
}