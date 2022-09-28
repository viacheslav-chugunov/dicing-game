package viach.apps.cache.util

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import viach.apps.cache.StatsPreferences
import viach.apps.cache.status.Stats

class TestStats(
    var easyModeWinsCount: Int = 0,
    var easyModeLossesCount: Int = 0,
    var normalModeWinsCount: Int = 0,
    var normalModeLossesCount: Int = 0,
    var hardModeWinsCount: Int = 0,
    var hardModeLossesCount: Int = 0,
    override var data: Flow<StatsPreferences> = flowOf(StatsPreferences.getDefaultInstance()),
    override var currentData: StatsPreferences = StatsPreferences.getDefaultInstance(),
) : Stats {

    constructor(data: StatsPreferences) : this(data = flowOf(data), currentData = data)

    override fun update(statsBuilder: suspend StatsPreferences.Builder.() -> StatsPreferences.Builder): Job {
        runBlocking {
            val newData = statsBuilder(StatsPreferences.newBuilder()).build()
            easyModeWinsCount = newData.easyModeWinsCount
            easyModeLossesCount = newData.easyModeLossesCount
            normalModeWinsCount = newData.normalModeWinsCount
            normalModeLossesCount = newData.normalModeLossesCount
            hardModeWinsCount = newData.hardModeWinsCount
            hardModeLossesCount = newData.hardModeLossesCount
            data = flowOf(newData)
            currentData = newData
        }
        return Job()
    }
}