package com.alessandrofarandagancio.nycschools.school.view
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.alessandrofarandagancio.nycschools.R
import com.alessandrofarandagancio.nycschools.databinding.ActivitySchoolListBinding
import com.alessandrofarandagancio.nycschools.network.ApiService
import com.alessandrofarandagancio.nycschools.school.viewmodel.SchoolListViewModel
import com.alessandrofarandagancio.nycschools.school.viewmodel.SchoolListViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class SchoolsListActivity : AppCompatActivity(), HasAndroidInjector {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var binding: ActivitySchoolListBinding

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var viewModelFactory: SchoolListViewModelFactory

    private val schoolListViewModel: SchoolListViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        binding = ActivitySchoolListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_school_list)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_school_list)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}