package com.example.codechallenge.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.codechallenge.data.model.AcromineResponse
import com.example.codechallenge.databinding.VariationsItemsBinding

class VariationAdapter : RecyclerView.Adapter<VariationAdapter.VariationsVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VariationsVH {
        val variationsItemsBinding =
            VariationsItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VariationsVH(variationsItemsBinding)
    }

    override fun onBindViewHolder(holder: VariationsVH, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    val DiffCallbBack =
        object : DiffUtil.ItemCallback<AcromineResponse.AcromineResponseItem.Lf.Var?>() {

            override fun areItemsTheSame(
                oldItem: AcromineResponse.AcromineResponseItem.Lf.Var,
                newItem: AcromineResponse.AcromineResponseItem.Lf.Var
            ): Boolean {
                return oldItem.freq == newItem.freq
            }

            override fun areContentsTheSame(
                oldItem: AcromineResponse.AcromineResponseItem.Lf.Var,
                newItem: AcromineResponse.AcromineResponseItem.Lf.Var
            ): Boolean {
                return oldItem == newItem
            }
        }

    private val differ: AsyncListDiffer<AcromineResponse.AcromineResponseItem.Lf.Var?> =
        AsyncListDiffer<AcromineResponse.AcromineResponseItem.Lf.Var?>(this, DiffCallbBack)

    fun submitList(variationsList: List<AcromineResponse.AcromineResponseItem.Lf.Var?>?) {
        differ.submitList(variationsList)
    }

    class VariationsVH(private val variationsItemsBinding: VariationsItemsBinding) :
        RecyclerView.ViewHolder(variationsItemsBinding.root) {
        fun bind(varitaion: AcromineResponse.AcromineResponseItem.Lf.Var?) {
            variationsItemsBinding.varItem = varitaion
            variationsItemsBinding.executePendingBindings()
        }
    }
}