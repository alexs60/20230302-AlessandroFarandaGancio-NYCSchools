package com.alessandrofarandagancio.nycschools.school.view.details
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
import com.alessandrofarandagancio.nycschools.databinding.FragmentDetailsBinding
import com.alessandrofarandagancio.nycschools.network.ApiService
import com.alessandrofarandagancio.nycschools.school.viewmodel.SchoolListViewModel
import com.alessandrofarandagancio.nycschools.school.viewmodel.SchoolListViewModelFactory
import com.alessandrofarandagancio.nycschools.utils.Utils
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DetailsFragment : Fragment(), HasAndroidInjector {

    private var _binding: FragmentDetailsBinding? = null

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        viewModelFactory = SchoolListViewModelFactory(apiService)

        binding.SchoolDetailLayout.visibility = View.GONE
        binding.SATLayout.visibility = View.GONE
        binding.SATLayoutEmpty.visibility = View.GONE


        /*
        TODO manage the sequential retrieval of the details data (SAT and School detail).
        Use a pair of data in MutableLiveData in VM or use a map of observable with rxjava
        or attach the mutableLiveData each other
         */

        schoolListViewModel.schoolDetail.observe(viewLifecycleOwner, Observer {
            binding.schoolName.text = it[0].school_name
            binding.schoolLocation.text = Utils.removeBrachetsFromLocationString(it[0].location)
            binding.schoolEmail.text = it[0].school_email
            binding.schoolWebsite.text = it[0].website
            binding.SchoolDetailLayout.visibility = View.VISIBLE
        })

        schoolListViewModel.schoolSATDetail.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                binding.SATLayout.visibility = View.GONE
                binding.SchoolDetailLayout.visibility = View.VISIBLE
                binding.SATLayoutEmpty.visibility = View.VISIBLE
            } else {
                binding.schoolSATTestTakers.text = it[0].num_of_sat_test_takers
                binding.schoolSATReadingAverageScore.text = it[0].sat_critical_reading_avg_score
                binding.schoolSATMathAverageScore.text = it[0].sat_math_avg_score
                binding.schoolSATWritingAverageScore.text = it[0].sat_writing_avg_score
                binding.SATLayoutEmpty.visibility = View.GONE
                binding.SATLayout.visibility = View.VISIBLE
            }
        })

        schoolListViewModel.errorDetailsFragment.observe(viewLifecycleOwner, Observer {
            binding.errorInView.text = it
            binding.errorInView.setTextColor(Color.RED)
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        val school = arguments?.getString("school")
        if (school != null) {
            schoolListViewModel.loadSchoolDetail(school)
            schoolListViewModel.loadSchoolSATDetail(school)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}