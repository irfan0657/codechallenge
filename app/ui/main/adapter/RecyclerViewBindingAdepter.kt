package com.example.codechallenge.ui.main.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codechallenge.data.model.AcromineResponse

@BindingAdapter("variations")
fun setVariations(
    recyclerView: RecyclerView,
    list: List<AcromineResponse.AcromineResponseItem.Lf.Var?>
) {
    val variationAdapter = VariationAdapter()
    recyclerView.adapter = variationAdapter
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    variationAdapter.submitList(list)
}