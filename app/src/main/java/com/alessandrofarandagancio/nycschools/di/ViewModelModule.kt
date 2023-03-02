package com.alessandrofarandagancio.nycschools.di

import androidx.lifecycle.ViewModel
import com.alessandrofarandagancio.nycschools.school.viewmodel.SchoolListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SchoolListViewModel::class)
    abstract fun bindSchoolListViewModel(schoolListViewModel: SchoolListViewModel): ViewModel

}