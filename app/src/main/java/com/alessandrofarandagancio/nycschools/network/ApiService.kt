package com.alessandrofarandagancio.nycschools.network
import com.alessandrofarandagancio.nycschools.school.model.Count
import com.alessandrofarandagancio.nycschools.school.model.SATResult
import com.alessandrofarandagancio.nycschools.school.model.SchoolDetailed
import com.alessandrofarandagancio.nycschools.school.model.SchoolSimple
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("resource/{resourceId}.json")
    fun getData(
        @Path("resourceId") resourceId: String,
        @Query("\$limit") limit: Int,
        @Query("\$offset") offset: Int,
        @Query("\$select") select: String
    ): Call<List<SchoolSimple>>

    @GET("resource/{resourceId}.json")
    fun getSchoolDetail(
        @Path("resourceId") resourceId: String,
        @Query("dbn") dbn: String,
    ): Call<List<SchoolDetailed>>

    @GET("resource/{resourceId}.json")
    fun getSchoolSATData(
        @Path("resourceId") resourceId: String,
        @Query("dbn") dbn: String,
    ): Call<List<SATResult>>

    @GET("resource/{resourceId}.json?\$select=count(*)")
    fun getDataCount(@Path("resourceId") resourceId: String): Call<List<Count>>
}