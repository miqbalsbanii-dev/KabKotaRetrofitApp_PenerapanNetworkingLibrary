package id.ac.polbeng.depandienda.kabkotaretrofitapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class KabKotaItem(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("kabupaten_kota")
    val kabupatenKota: String,

    @field:SerializedName("pusat_pemerintahan")
    val pusatPemerintahan: String,

    @field:SerializedName("bupati_walikota")
    val bupatiWalikota: String,

    @field:SerializedName("luas_wilayah")
    val luasWilayah: Double,

    @field:SerializedName("jumlah_penduduk")
    val jumlahPenduduk: Int,

    @field:SerializedName("jumlah_kecamatan")
    val jumlahKecamatan: Int,

    @field:SerializedName("jumlah_kelurahan")
    val jumlahKelurahan: Int,

    @field:SerializedName("jumlah_desa")
    val jumlahDesa: Int,

    @field:SerializedName("url_peta_wilayah")
    val urlPetaWilayah: String,

    @field:SerializedName("deskripsi")
    val deskripsi: String,

    @field:SerializedName("logo")
    val logo: String
) : Parcelable