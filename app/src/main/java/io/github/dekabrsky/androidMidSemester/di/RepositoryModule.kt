package io.github.dekabrsky.androidMidSemester.di

import io.github.dekabrsky.androidMidSemester.data.cache.MatchesCache
import io.github.dekabrsky.androidMidSemester.data.repository.EspnMatchRepositoryImpl
import io.github.dekabrsky.androidMidSemester.data.repository.MatchRepositoryImpl
import io.github.dekabrsky.androidMidSemester.domain.repository.MatchRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single { MatchesCache() }

    // Текущий поставщик данных — ESPN.
    single<MatchRepository> { EspnMatchRepositoryImpl(get(), get()) }

    // Прежний поставщик sstats оставлен на случай возврата бизнеса:
    // чтобы вернуться, достаточно поменять биндинг MatchRepository на этот бин.
    single(named("sstats")) { MatchRepositoryImpl(get(), get()) }
}
