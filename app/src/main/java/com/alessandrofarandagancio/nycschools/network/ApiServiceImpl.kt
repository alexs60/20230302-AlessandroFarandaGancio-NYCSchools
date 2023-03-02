package com.alessandrofarandagancio.nycschools.network
import com.alessandrofarandagancio.nycschools.school.model.Count
import com.alessandrofarandagancio.nycschools.school.model.SATResult
import com.alessandrofarandagancio.nycschools.school.model.SchoolDetailed
import com.alessandrofarandagancio.nycschools.school.model.SchoolSimple
import com.alessandrofarandagancio.nycschools.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceImpl : ApiService {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_SODA_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(ApiService::class.java)

    override fun getData(
        resourceId: String,
        limit: Int,
        offset: Int,
        select: String
    ): Call<List<SchoolSimple>> {
        return api.getData(resourceId, limit, offset, select)
    }

    override fun getSchoolDetail(resourceId: String, dbn: String): Call<List<SchoolDetailed>> {
        return api.getSchoolDetail(resourceId, dbn)
    }

    override fun getSchoolSATData(resourceId: String, dbn: String): Call<List<SATResult>> {
        return api.getSchoolSATData(resourceId, dbn)
    }

    override fun getDataCount(resourceId: String): Call<List<Count>> {
        return api.getDataCount(resourceId)
    }
}