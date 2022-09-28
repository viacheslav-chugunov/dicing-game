package viach.apps.cache.status

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import viach.apps.cache.StatsPreferences
import java.io.InputStream
import java.io.OutputStream

object StatsPreferencesSerializer : Serializer<StatsPreferences> {
    override val defaultValue: StatsPreferences
        get() = StatsPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): StatsPreferences =
        withContext(Dispatchers.IO) {
            try {
                StatsPreferences.parseFrom(input)
            } catch (exception: InvalidProtocolBufferException) {
                throw CorruptionException("Cannot read proto.", exception)
            }
        }

    override suspend fun writeTo(t: StatsPreferences, output: OutputStream) =
        withContext(Dispatchers.IO) {
            t.writeTo(output)
        }
}