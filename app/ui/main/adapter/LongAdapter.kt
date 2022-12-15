package com.example.codechallenge.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.codechallenge.data.model.AcromineResponse
import com.example.codechallenge.databinding.LongItemsBinding

class LongAdapter : RecyclerView.Adapter<LongAdapter.LongformVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LongformVH {
        val longItemsBinding =
            LongItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LongformVH(longItemsBinding)
    }

    override fun onBindViewHolder(holder: LongformVH, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    val DiffCallback =
        object : DiffUtil.ItemCallback<AcromineResponse.AcromineResponseItem.Lf>() {

            override fun areItemsTheSame(
                oldItem: AcromineResponse.AcromineResponseItem.Lf,
                newItem: AcromineResponse.AcromineResponseItem.Lf
            ): Boolean {
                return oldItem.freq == newItem.freq
            }

            override fun areContentsTheSame(
                oldItem: AcromineResponse.AcromineResponseItem.Lf,
                newItem: AcromineResponse.AcromineResponseItem.Lf
            ): Boolean {
                return oldItem == newItem
            }

        }
    private val differ: AsyncListDiffer<AcromineResponse.AcromineResponseItem.Lf> =
        AsyncListDiffer(this, DiffCallback)


    fun submitList(variationsList: List<AcromineResponse.AcromineResponseItem.Lf?>?) {
        differ.submitList(variationsList)
    }

    class LongformVH(private val longItemsBinding: LongItemsBinding) :
        RecyclerView.ViewHolder(longItemsBinding.root) {
        fun bind(lf: AcromineResponse.AcromineResponseItem.Lf?) {
            longItemsBinding.longform = lf
            longItemsBinding.executePendingBindings()
        }
    }
}