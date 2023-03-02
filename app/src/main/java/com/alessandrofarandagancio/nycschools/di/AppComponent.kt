package com.alessandrofarandagancio.nycschools.di
import com.alessandrofarandagancio.nycschools.NYCSApplication
import com.alessandrofarandagancio.nycschools.school.view.ListFragment
import com.alessandrofarandagancio.nycschools.school.view.SchoolsListActivity
import com.alessandrofarandagancio.nycschools.school.view.details.DetailsFragment
import com.alessandrofarandagancio.nycschools.school.viewmodel.SchoolListViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBindingModule::class, FragmentBindingModule::class, ViewModelModule::class, SchoolListViewModelModule::class])
interface AppComponent {
    fun inject(schoolsListActivity: SchoolsListActivity)
    fun inject(listFragment: ListFragment)
    fun inject(detailsFragment: DetailsFragment)
    fun inject(application: NYCSApplication)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: NYCSApplication): Builder
    }

}
