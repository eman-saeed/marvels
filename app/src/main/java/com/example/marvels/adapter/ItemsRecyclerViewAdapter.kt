package com.example.marvels.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvels.databinding.ModelCellViewBinding
import com.example.marvels.model.Results
import com.example.marvels.util.ImageUtil

class ItemsRecyclerViewAdapter(private val items: ArrayList<Results>) :
    RecyclerView.Adapter<ItemsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ModelCellViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(item = items.get(position))


    //************************************View Holder******************************************
    class ViewHolder(val modelCellViewBinding: ModelCellViewBinding) :
        RecyclerView.ViewHolder(modelCellViewBinding.root) {
        fun bind(item: Results) {
            modelCellViewBinding.item = item
            if (item.thumbnail != null)
                ImageUtil.setImageViewResource(modelCellViewBinding.imageView2, item.thumbnail.getImageURI())
        }
    }
}