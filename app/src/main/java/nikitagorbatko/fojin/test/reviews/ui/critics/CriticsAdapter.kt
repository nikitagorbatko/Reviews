package nikitagorbatko.fojin.test.reviews.ui.critics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import nikitagorbatko.fojin.test.reviews.R
import nikitagorbatko.fojin.test.reviews.api.CriticDto
import nikitagorbatko.fojin.test.reviews.databinding.ItemCriticBinding

class CriticsAdapter(private val onItemClick: (CriticDto) -> Unit) :
    RecyclerView.Adapter<CriticsAdapter.ViewHolder>() {
    private val critics = mutableListOf<CriticDto?>()

    inner class ViewHolder(val binding: ItemCriticBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCriticBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = critics.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val critic = critics[position]
        with(holder.binding) {
            textViewName.text = critic?.displayName
            root.setOnClickListener {
                onItemClick(critic!!)
            }
            Glide.with(root)
                .load(critic?.multimedia?.resource?.src)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .error(R.drawable.ic_person_circle)
                .into(imageViewCritic)
        }
    }

    fun add(listCritics: List<CriticDto?>) {
        critics.clear()
        critics.addAll(listCritics)
        notifyDataSetChanged()
    }
}