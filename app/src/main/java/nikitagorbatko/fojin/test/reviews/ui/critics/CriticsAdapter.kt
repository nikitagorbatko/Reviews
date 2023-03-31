package nikitagorbatko.fojin.test.reviews.ui.critics

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import nikitagorbatko.fojin.test.reviews.databinding.ItemCriticBinding

class CriticsAdapter : PagingDataAdapter<Int, CriticsAdapter.ViewHolder>(DiffUtilCallback()) {

    inner class ViewHolder(binding: ItemCriticBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem
}