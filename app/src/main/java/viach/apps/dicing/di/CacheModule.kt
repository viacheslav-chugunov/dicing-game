package viach.apps.dicing.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import viach.apps.cache.status.ProtoDataStoreStats
import viach.apps.cache.status.ProtoDataStoreStatsCache
import viach.apps.cache.status.StatsCache

val cacheModule = module {
    factory<StatsCache> {
        ProtoDataStoreStatsCache(ProtoDataStoreStats(androidContext()))
    }
}