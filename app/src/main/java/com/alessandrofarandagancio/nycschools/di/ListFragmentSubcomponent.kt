package com.alessandrofarandagancio.nycschools.di
import com.alessandrofarandagancio.nycschools.school.view.ListFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface ListFragmentSubcomponent : AndroidInjector<ListFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<ListFragment>
}
