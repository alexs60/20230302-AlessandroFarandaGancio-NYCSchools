package com.alessandrofarandagancio.nycschools.school.viewmodel
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.alessandrofarandagancio.nycschools.R
import com.alessandrofarandagancio.nycschools.network.ApiService
import com.alessandrofarandagancio.nycschools.school.model.Count
import com.alessandrofarandagancio.nycschools.school.model.SATResult
import com.alessandrofarandagancio.nycschools.school.model.SchoolDetailed
import com.alessandrofarandagancio.nycschools.school.model.SchoolSimple
import com.alessandrofarandagancio.nycschools.school.view.ListFragment
import com.alessandrofarandagancio.nycschools.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchoolListViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    private val _schoolList = MutableLiveData<List<SchoolSimple>>()

    val schoolList: LiveData<List<SchoolSimple>>
        get() = _schoolList

    private val _schoolDetail = MutableLiveData<List<SchoolDetailed>>()

    val schoolDetail: LiveData<List<SchoolDetailed>>
        get() = _schoolDetail

    private val _schoolSATDetail = MutableLiveData<List<SATResult>>()

    val schoolSATDetail: LiveData<List<SATResult>>
        get() = _schoolSATDetail

    private val _pageCount = MutableLiveData<List<Count>>()

    val pageCount: LiveData<List<Count>>
        get() = _pageCount

    private val _errorListFragment = MutableLiveData<String>()
    val errorListFragment: LiveData<String>
        get() = _errorListFragment

    private val _errorDetailsFragment = MutableLiveData<String>()
    val errorDetailsFragment: LiveData<String>
        get() = _errorDetailsFragment

    private var currentSchool: SchoolSimple? = null

    private val pageSize = 10

    private val page = 1

    private val listData = "dbn,school_name,location"

    fun loadSchoolList() {
        apiService.getData(
            Constants.HIGH_SCHOOL_DIRECTORY_2017,
            pageSize,
            (page - 1) * pageSize,
            listData
        )
            .enqueue(object : Callback<List<SchoolSimple>> {
                override fun onResponse(
                    call: Call<List<SchoolSimple>>,
                    response: Response<List<SchoolSimple>>
                ) {
                    if (response.isSuccessful) {
                        _schoolList.value = response.body() as List<SchoolSimple>
                    } else {
                        _errorListFragment.value = "Error: ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<List<SchoolSimple>>, t: Throwable) {
                    _errorListFragment.value = t.message
                }
            })
    }

    fun loadPaginationDatas() {
        apiService.getDataCount(Constants.HIGH_SCHOOL_DIRECTORY_2017)
            .enqueue(object : Callback<List<Count>> {
                override fun onResponse(
                    call: Call<List<Count>>,
                    response: Response<List<Count>>
                ) {
                    if (response.isSuccessful) {
                        _pageCount.value = response.body() as List<Count>
                    } else {
                        _errorListFragment.value = "Error: ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<List<Count>>, t: Throwable) {
                    _errorListFragment.value = t.message
                }
            })
    }

    fun goToSchoolDetail(listFragment: ListFragment, schoolSimple: SchoolSimple) {
        val bundle = bundleOf("school" to schoolSimple.dbn)
        findNavController(listFragment).navigate(R.id.action_ListFragment_to_DetailsFragment, bundle)
    }

    fun loadSchoolDetail(school: String) {
        apiService.getSchoolDetail(Constants.HIGH_SCHOOL_DIRECTORY_2017, school)
            .enqueue(object : Callback<List<SchoolDetailed>> {
                override fun onResponse(
                    call: Call<List<SchoolDetailed>>,
                    response: Response<List<SchoolDetailed>>
                ) {
                    if (response.isSuccessful) {
                        _schoolDetail.value = response.body() as List<SchoolDetailed>
                    } else {
                        _errorListFragment.value = "Error: ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<List<SchoolDetailed>>, t: Throwable) {
                    _errorListFragment.value = t.message
                }
            })

    }

    fun loadSchoolSATDetail(school: String) {
        apiService.getSchoolSATData(Constants.SAT_RESULTS_2012, school)
            .enqueue(object : Callback<List<SATResult>> {
                override fun onResponse(
                    call: Call<List<SATResult>>,
                    response: Response<List<SATResult>>
                ) {
                    if (response.isSuccessful) {
                        _schoolSATDetail.value = response.body() as List<SATResult>
                    } else {
                        _errorListFragment.value = "Error: ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<List<SATResult>>, t: Throwable) {
                    _errorListFragment.value = t.message
                }
            })

    }

}