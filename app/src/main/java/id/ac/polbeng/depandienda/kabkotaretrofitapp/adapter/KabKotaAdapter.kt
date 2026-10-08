package id.ac.polbeng.depandienda.kabkotaretrofitapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ac.polbeng.depandienda.kabkotaretrofitapp.databinding.KabkotaItemBinding
import id.ac.polbeng.depandienda.kabkotaretrofitapp.helpers.Config
import id.ac.polbeng.depandienda.kabkotaretrofitapp.model.KabKotaItem

class KabKotaAdapter : ListAdapter<KabKotaItem, KabKotaAdapter.ViewHolder>(DIFF_CALLBACK) {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: KabKotaItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = KabkotaItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, onItemClickCallback)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(
        private val binding: KabkotaItemBinding,
        private val onItemClickCallback: OnItemClickCallback?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: KabKotaItem) {
            binding.tvKabKota.text = item.kabupatenKota
            binding.tvPusatPemerintahan.text = item.pusatPemerintahan

            val logoUrl = "${Config.LOGO_URL}/${item.logo}"
            Glide.with(itemView.context)
                .load(logoUrl)
                .into(binding.imgLogo)

            itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(item)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<KabKotaItem>() {
            override fun areItemsTheSame(
                oldItem: KabKotaItem,
                newItem: KabKotaItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: KabKotaItem,
                newItem: KabKotaItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}