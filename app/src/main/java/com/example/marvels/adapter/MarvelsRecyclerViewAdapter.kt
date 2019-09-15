package com.example.marvels.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvels.R
import com.example.marvels.databinding.MarvelsListCellBinding
import com.example.marvels.model.Results
import com.example.marvels.util.ImageUtil
import com.squareup.picasso.Picasso

class MarvelsRecyclerViewAdapter(
    private val marvels: ArrayList<Results>,
    characterClicked: CharacterClickedListener
) : RecyclerView.Adapter<MarvelsRecyclerViewAdapter.ViewHolder>() {

    val characterClickedListener = characterClicked;

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(MarvelsListCellBinding.inflate(LayoutInflater.from(p0.context), p0, false))
    }

    override fun getItemCount(): Int {
        return marvels.size;
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) = p0.bind(
        results = marvels.get(p1), position = p1,
        characterClicked = characterClickedListener
    )

    //***********************************************View Holder********************************************************
    class ViewHolder(val marvelsListCellBinding: MarvelsListCellBinding) :
        RecyclerView.ViewHolder(marvelsListCellBinding.root), View.OnClickListener {

        private var position: Int? = null

        var characterClickedListener: CharacterClickedListener? = null;

        override fun onClick(p0: View?) {
            characterClickedListener?.whenCharacterClicked(position)
        }

        fun bind(results: Results, position: Int, characterClicked: CharacterClickedListener) {
            this.itemView.setOnClickListener(this)
            this.position = position
            this.characterClickedListener = characterClicked
            ImageUtil.setImageViewResource(marvelsListCellBinding.imageView, results.thumbnail.getImageURI())
            marvelsListCellBinding.marvelName.text = results.name.toString()
        }
    }

    //******************************interface**********************************************
    interface CharacterClickedListener {
        fun whenCharacterClicked(position: Int?);
    }
}