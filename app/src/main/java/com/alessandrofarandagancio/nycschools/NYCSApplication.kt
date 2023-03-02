package com.alessandrofarandagancio.nycschools

import android.app.Application
import com.alessandrofarandagancio.nycschools.di.AppComponent
import com.alessandrofarandagancio.nycschools.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class NYCSApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}