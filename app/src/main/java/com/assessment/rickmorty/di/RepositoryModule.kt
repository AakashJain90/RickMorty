package com.assessment.rickmorty.di

import com.assessment.rickmorty.data.repository.DataRepository
import com.assessment.rickmorty.data.repository.IDataRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.binds
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val repositoryModule = module {

    single { DataRepository(androidContext(), get<Retrofit>().create()) } binds arrayOf(IDataRepository::class)

}