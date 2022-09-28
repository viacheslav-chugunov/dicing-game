package viach.apps.cache.extensions

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import viach.apps.cache.StatsPreferences
import viach.apps.cache.status.StatsPreferencesSerializer

internal val Context.preferences: DataStore<StatsPreferences> by dataStore(
    fileName = "StatsPreferences",
    serializer = StatsPreferencesSerializer
)