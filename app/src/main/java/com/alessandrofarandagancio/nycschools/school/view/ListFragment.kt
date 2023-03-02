package com.alessandrofarandagancio.nycschools.school.view
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.alessandrofarandagancio.nycschools.databinding.FragmentListBinding
import com.alessandrofarandagancio.nycschools.network.ApiService
import com.alessandrofarandagancio.nycschools.school.model.SchoolSimple
import com.alessandrofarandagancio.nycschools.school.viewmodel.SchoolListViewModel
import com.alessandrofarandagancio.nycschools.school.viewmodel.SchoolListViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ListFragment : Fragment(), HasAndroidInjector {

    private var _binding: FragmentListBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var viewModelFactory: SchoolListViewModelFactory

    private val schoolListViewModel: SchoolListViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    /*
    TODO manage the scroll to load more item using pagination, the service is already ready
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)

        viewModelFactory = SchoolListViewModelFactory(apiService)

        var schoolAdapter = SchoolItemAdapter { school -> adapterOnClick(school) }
        binding.recyclerView.adapter = schoolAdapter

        schoolListViewModel.schoolList.observe(viewLifecycleOwner, Observer {
            schoolAdapter.submitList(it)
        })

        schoolListViewModel.pageCount.observe(viewLifecycleOwner, Observer {
            binding.totalPages.text = it[0].count
        })

        schoolListViewModel.errorListFragment.observe(viewLifecycleOwner, Observer {
            binding.errorInView.text = it
            binding.errorInView.setTextColor(Color.RED)
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        schoolListViewModel.loadSchoolList()
        schoolListViewModel.loadPaginationDatas()

        return binding.root

    }

    private fun adapterOnClick(school: SchoolSimple) {
        schoolListViewModel.goToSchoolDetail(this, school)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}