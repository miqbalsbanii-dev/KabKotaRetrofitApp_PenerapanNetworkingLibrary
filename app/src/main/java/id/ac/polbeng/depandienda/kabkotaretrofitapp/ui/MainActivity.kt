package id.ac.polbeng.depandienda.kabkotaretrofitapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.polbeng.depandienda.kabkotaretrofitapp.adapter.KabKotaAdapter
import id.ac.polbeng.depandienda.kabkotaretrofitapp.databinding.ActivityMainBinding
import id.ac.polbeng.depandienda.kabkotaretrofitapp.helpers.Config
import id.ac.polbeng.depandienda.kabkotaretrofitapp.model.KabKotaItem
import id.ac.polbeng.depandienda.kabkotaretrofitapp.response.KabKotaResponse
import id.ac.polbeng.depandienda.kabkotaretrofitapp.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: KabKotaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        getListKabKota()

        binding.swipeRefresh.setOnRefreshListener {
            getListKabKota()
        }
    }

    private fun setupRecyclerView() {
        adapter = KabKotaAdapter()
        binding.rvKabKota.layoutManager = LinearLayoutManager(this)
        binding.rvKabKota.adapter = adapter

        adapter.setOnItemClickCallback(object : KabKotaAdapter.OnItemClickCallback {
            override fun onItemClicked(data: KabKotaItem) {
                val intent = Intent(this@MainActivity, DetailKabKotaActivity::class.java)
                intent.putExtra(Config.EXTRA_KAB_KOTA, data)
                startActivity(intent)
            }
        })
    }

    private fun getListKabKota() {
        showLoading(true)
        val client = ApiConfig.getApiService().getListUsers()
        client.enqueue(object : Callback<KabKotaResponse> {
            override fun onResponse(
                call: Call<KabKotaResponse>,
                response: Response<KabKotaResponse>
            ) {
                showLoading(false)
                binding.swipeRefresh.isRefreshing = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val listData = responseBody?.data
                    if (!listData.isNullOrEmpty()) {
                        adapter.submitList(listData)
                    } else {
                        Toast.makeText(this@MainActivity, "Data kosong", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Request failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<KabKotaResponse>, t: Throwable) {
                showLoading(false)
                binding.swipeRefresh.isRefreshing = false
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}