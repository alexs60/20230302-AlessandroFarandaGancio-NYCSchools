package com.alessandrofarandagancio.nycschools.di
import com.alessandrofarandagancio.nycschools.network.ApiService
import com.alessandrofarandagancio.nycschools.network.ApiServiceImpl
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideApiService(): ApiService {
        // Create and return an instance of the ApiService
        return ApiServiceImpl()
    }
}