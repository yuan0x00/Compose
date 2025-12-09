package com.rapid.compose.di

import com.rapid.compose.MainViewModel
import com.rapid.compose.core.data.repository.user.UserRepository
import com.rapid.compose.viewmodel.ProxySettingsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::UserRepository)
    viewModelOf(::MainViewModel)
    viewModelOf(::ProxySettingsViewModel)
}