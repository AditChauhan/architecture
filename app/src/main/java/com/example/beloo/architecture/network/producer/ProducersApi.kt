package com.example.beloo.architecture.network.producer

import com.example.beloo.architecture.network.ServerContract
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProducersApi {

    /** get list of all user repositories */
    @GET(ServerContract.Producers.PRODUCERS)
    fun getProducers(
        @Query("page") page: Int,
        @Query("per_page_limit") perPageLimit: Int = 10): Single<ProducersListPoJo>

}
