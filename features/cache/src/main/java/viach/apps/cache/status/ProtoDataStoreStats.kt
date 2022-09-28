package viach.apps.cache.status

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import viach.apps.cache.StatsPreferences
import viach.apps.cache.extensions.preferences
import java.io.IOException

class ProtoDataStoreStats(
    context: Context,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : Stats {
    override var currentData: StatsPreferences = StatsPreferences.getDefaultInstance()
    private val preferences: DataStore<StatsPreferences> = context.preferences

    init {
        coroutineScope.launch {
            data.collectLatest { data ->
                currentData = data
            }
        }
    }

    override val data: Flow<StatsPreferences>
        get() = preferences.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(StatsPreferences.getDefaultInstance())
                } else {
                    throw exception
                }
            }

    override fun update(
        statsBuilder: suspend StatsPreferences.Builder.() -> StatsPreferences.Builder
    ): Job = coroutineScope.launch {
        preferences.updateData {
            statsBuilder(it.toBuilder()).build()
        }
    }
}