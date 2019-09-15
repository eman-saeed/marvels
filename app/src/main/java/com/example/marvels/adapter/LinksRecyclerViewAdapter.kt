package com.example.marvels.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvels.databinding.RelatedLinkCellBinding
import com.example.marvels.model.URLs

class LinksRecyclerViewAdapter(private val urls: ArrayList<URLs>) :
    RecyclerView.Adapter<LinksRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RelatedLinkCellBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return urls.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(url = urls.get(position), position = position)


    //************************* View Holder***********************************************
    class ViewHolder(val relatedLinkCellBinding: RelatedLinkCellBinding) :
        RecyclerView.ViewHolder(relatedLinkCellBinding.root) {

        var position: Int? = null

        fun bind(url: URLs, position: Int) {
            this.position = position
            relatedLinkCellBinding.url = url
        }
    }
}