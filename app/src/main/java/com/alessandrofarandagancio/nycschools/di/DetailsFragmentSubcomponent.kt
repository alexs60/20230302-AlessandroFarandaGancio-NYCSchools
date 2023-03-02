package com.alessandrofarandagancio.nycschools.di

import com.alessandrofarandagancio.nycschools.school.view.details.DetailsFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface DetailsFragmentSubcomponent : AndroidInjector<DetailsFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<DetailsFragment>
}
