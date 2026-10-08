package id.ac.polbeng.depandienda.kabkotaretrofitapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.ac.polbeng.depandienda.kabkotaretrofitapp.databinding.ActivityDetailKabKotaBinding
import id.ac.polbeng.depandienda.kabkotaretrofitapp.helpers.Config
import id.ac.polbeng.depandienda.kabkotaretrofitapp.model.KabKotaItem

class DetailKabKotaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailKabKotaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailKabKotaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Kabupaten/Kota"

        val kabKota = intent.getParcelableExtra<KabKotaItem>(Config.EXTRA_KAB_KOTA)

        if (kabKota != null) {
            binding.tvKabKota.text = kabKota.kabupatenKota
            binding.tvPusatPemerintahan.text = kabKota.pusatPemerintahan
            binding.tvBupatiWalikota.text = kabKota.bupatiWalikota
            binding.tvLuasWilayah.text = "${kabKota.luasWilayah} km²"
            binding.tvJumlahPenduduk.text = kabKota.jumlahPenduduk.toString()
            binding.tvJumlahKecamatan.text = kabKota.jumlahKecamatan.toString()
            binding.tvJumlahKelurahan.text = kabKota.jumlahKelurahan.toString()
            binding.tvJumlahDesa.text = kabKota.jumlahDesa.toString()
            binding.tvDeskripsi.text = kabKota.deskripsi

            Glide.with(this)
                .load("${Config.LOGO_URL}/${kabKota.logo}")
                .into(binding.imgLogo)

            binding.btnViewPeta.setOnClickListener {
                val intent = Intent(this, ViewPetaActivity::class.java)
                intent.putExtra(Config.EXTRA_URL_PETA, kabKota.urlPetaWilayah)
                startActivity(intent)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}