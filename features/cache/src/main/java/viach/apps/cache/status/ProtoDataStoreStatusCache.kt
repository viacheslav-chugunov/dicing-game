package viach.apps.cache.status

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import viach.apps.cache.StatsPreferences
import java.io.IOException

class ProtoDataStoreStatusCache(
    context: Context,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : StatsCache {
    private val preferences: DataStore<StatsPreferences> = context.preferences

    override val all: Flow<StatsPreferences>
        get() = preferences.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(StatsPreferences.getDefaultInstance())
                } else {
                    throw exception
                }
            }

    override fun addEasyWinLossPoint(win: Boolean): Job =
        updatePreferences {
            if (win) {
                setEasyModeWinsCount(all.first().easyModeWinsCount + 1)
            } else {
                setEasyModeLossesCount(all.first().easyModeLossesCount + 1)
            }
        }

    override fun addNormalWinLossPoint(win: Boolean): Job =
        updatePreferences {
            if (win) {
                setNormalModeWinsCount(all.first().normalModeWinsCount + 1)
            } else {
                setNormalModeLossesCount(all.first().normalModeLossesCount + 1)
            }
        }

    override fun addHardWinLossPoint(win: Boolean): Job =
        updatePreferences {
            if (win) {
                setHardModeWinsCount(all.first().hardModeWinsCount + 1)
            } else {
                setHardModeLossesCount(all.first().hardModeLossesCount + 1)
            }
        }

    override fun clear(): Job =
        updatePreferences { clear() }

    private fun updatePreferences(
        preferencesBuilder: suspend StatsPreferences.Builder.() -> StatsPreferences.Builder
    ) = coroutineScope.launch {
        preferences.updateData { preferencesBuilder(it.toBuilder()).build() }
    }

    companion object {
        private val Context.preferences: DataStore<StatsPreferences> by dataStore(
            fileName = "StatsPreferences",
            serializer = StatsPreferencesSerializer
        )
    }
}