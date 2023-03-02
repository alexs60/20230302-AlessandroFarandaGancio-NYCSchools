package com.alessandrofarandagancio.nycschools.school.viewmodel;
import com.alessandrofarandagancio.nycschools.network.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
class SchoolListViewModelModule {

    @Provides
    fun provideMainViewModelFactory(apiService: ApiService): SchoolListViewModelFactory {
        return SchoolListViewModelFactory(apiService)
    }
}
