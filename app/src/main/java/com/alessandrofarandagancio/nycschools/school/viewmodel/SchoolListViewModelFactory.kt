package com.alessandrofarandagancio.nycschools.school.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alessandrofarandagancio.nycschools.network.ApiService
import javax.inject.Inject

class SchoolListViewModelFactory @Inject constructor(
    private val apiService: ApiService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SchoolListViewModel::class.java)) {
            return SchoolListViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }



}