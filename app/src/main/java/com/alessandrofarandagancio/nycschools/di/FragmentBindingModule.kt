package com.alessandrofarandagancio.nycschools.di

import com.alessandrofarandagancio.nycschools.school.view.ListFragment
import com.alessandrofarandagancio.nycschools.school.view.details.DetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(subcomponents = [ListFragmentSubcomponent::class, DetailsFragmentSubcomponent::class])
abstract class FragmentBindingModule {

    @ContributesAndroidInjector()
    abstract fun listFragment(): ListFragment

    @ContributesAndroidInjector()
    abstract fun detailsFragment(): DetailsFragment
}