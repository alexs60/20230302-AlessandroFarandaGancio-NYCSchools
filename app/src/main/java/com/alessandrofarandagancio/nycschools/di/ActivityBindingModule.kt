package com.alessandrofarandagancio.nycschools.di
import com.alessandrofarandagancio.nycschools.school.view.SchoolsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun schoolListActivity(): SchoolsListActivity
}