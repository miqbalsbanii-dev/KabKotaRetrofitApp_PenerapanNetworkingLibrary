package id.ac.polbeng.depandienda.kabkotaretrofitapp.response

import com.google.gson.annotations.SerializedName
import id.ac.polbeng.depandienda.kabkotaretrofitapp.model.KabKotaItem

data class KabKotaResponse(
    @SerializedName("data")
    val data: List<KabKotaItem>?,
    @SerializedName("message")
    val message: String?
)