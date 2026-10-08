package id.ac.polbeng.depandienda.kabkotaretrofitapp.retrofit

import id.ac.polbeng.depandienda.kabkotaretrofitapp.response.KabKotaResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/read_kabkota.php")
    fun getListUsers(): Call<KabKotaResponse>
}