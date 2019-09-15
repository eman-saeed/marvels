package com.example.marvels.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvels.R
import com.example.marvels.databinding.MarvelSearchRecyclerViewCellBinding
import com.example.marvels.databinding.MarvelsListCellBinding
import com.example.marvels.model.Results
import com.example.marvels.util.ImageUtil
import com.squareup.picasso.Picasso

class MarvelsSearchRecyclerViewAdapter(
    private var marvels: ArrayList<Results>,
    characterClicked: MarvelsRecyclerViewAdapter.CharacterClickedListener
) : RecyclerView.Adapter<MarvelsSearchRecyclerViewAdapter.ViewHolder>() {

    val characterClickedListener = characterClicked;

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(MarvelSearchRecyclerViewCellBinding.inflate(LayoutInflater.from(p0.context), p0, false))
    }

    override fun getItemCount(): Int {
        return marvels.size;
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) = p0.bind(
        results = marvels.get(p1), position = p1,
        characterClicked = characterClickedListener
    )

    fun setSearchList(marvels: ArrayList<Results>) {
        this.marvels = marvels
        notifyDataSetChanged()
    }

    //***********************************************View Holder********************************************************
    class ViewHolder(val marvelsListCellBinding: MarvelSearchRecyclerViewCellBinding) :
        RecyclerView.ViewHolder(marvelsListCellBinding.root), View.OnClickListener {

        private var position: Int? = null

        var characterClickedListener: MarvelsRecyclerViewAdapter.CharacterClickedListener? = null;

        override fun onClick(p0: View?) {
            characterClickedListener?.whenCharacterClicked(position)
        }

        fun bind(
            results: Results,
            position: Int,
            characterClicked: MarvelsRecyclerViewAdapter.CharacterClickedListener
        ) {
            this.itemView.setOnClickListener(this)
            this.position = position
            this.characterClickedListener = characterClicked
            ImageUtil.setImageViewResource(marvelsListCellBinding.characterImageView, results.thumbnail.getImageURI())
            marvelsListCellBinding.characterName.text = results.name
        }
    }
}