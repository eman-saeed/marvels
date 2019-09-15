package com.example.marvels.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvels.R
import com.example.marvels.adapter.ItemsRecyclerViewAdapter
import com.example.marvels.adapter.LinksRecyclerViewAdapter
import com.example.marvels.databinding.ActivityCharacterDetailsBinding
import com.example.marvels.model.Results
import com.example.marvels.util.ImageUtil
import com.example.marvels.viewModel.CharacterDetailsViewModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class CharacterDetailsActivity : AppCompatActivity(){
    var characterDetails: Results? = null

    val characterDetailsViewModel: CharacterDetailsViewModel by lazy {
        ViewModelProviders.of(this).get(CharacterDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var viewBinder =
            DataBindingUtil.setContentView<ActivityCharacterDetailsBinding>(this, R.layout.activity_character_details)

        setSupportActionBar(viewBinder.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        viewBinder.toolBar.bringToFront()

        if (intent.hasExtra(MainActivity.CHARACTER_DETAILS)) {
            characterDetails =
                Gson().fromJson(intent.getStringExtra(MainActivity.CHARACTER_DETAILS), Results::class.java)
        }
        viewBinder.textView2.text = characterDetails?.description
        viewBinder.characterName.text = characterDetails?.name

        ImageUtil.setImageViewResource(viewBinder.characterImageView, characterDetails?.thumbnail?.getImageURI()!!)

        viewBinder.comicsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewBinder.seriesRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewBinder.storiesRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewBinder.eventsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        if (characterDetails?.description.isNullOrEmpty()) {
            viewBinder.textView.visibility = View.GONE
            viewBinder.textView2.visibility = View.GONE
        }

        //get comics for this characters
        if (!characterDetails?.comics?.items.isNullOrEmpty()) {
            characterDetailsViewModel.getDetails(characterDetails!!.id, COMICS_SERVICE)?.observe(this, Observer { t ->
                viewBinder.comicsRecyclerView.adapter = ItemsRecyclerViewAdapter(t.data.results)
            })
        } else {
            viewBinder.comicsRecyclerView.visibility = View.GONE
            viewBinder.comicsLbl.visibility = View.GONE
        }

        //get series for this character
        if (!characterDetails?.series?.items.isNullOrEmpty()) {
            characterDetailsViewModel.getDetails(characterDetails!!.id, SERIES_SERVICE)?.observe(this, Observer { t ->
                viewBinder.seriesRecyclerView.adapter = ItemsRecyclerViewAdapter(t.data.results)
            })
        } else {
            viewBinder.seriesRecyclerView.visibility = View.GONE
            viewBinder.textView5.visibility = View.GONE
        }

        if (!characterDetails?.stories?.items.isNullOrEmpty()) {
            characterDetailsViewModel.getDetails(characterDetails!!.id, STORIES_SERVICE)?.observe(this, Observer { t ->
                viewBinder.storiesRecyclerView.adapter = ItemsRecyclerViewAdapter(t.data.results)
            })
        } else {
            viewBinder.storiesRecyclerView.visibility = View.GONE
            viewBinder.textView4.visibility = View.GONE
        }

        if (!characterDetails?.events?.items.isNullOrEmpty()) {
            characterDetailsViewModel.getDetails(characterDetails!!.id, EVENTS_SERVICE)?.observe(this, Observer { t ->
                viewBinder.eventsRecyclerView.adapter = ItemsRecyclerViewAdapter(t.data.results)
            })
        } else {
            viewBinder.eventsRecyclerView.visibility = View.GONE
            viewBinder.textView6.visibility = View.GONE
        }

        if (!characterDetails?.urls.isNullOrEmpty()) {
            viewBinder.relatedLinksRecyclerView.adapter = LinksRecyclerViewAdapter(characterDetails?.urls!!)
            viewBinder.relatedLinksRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    this,
                    DividerItemDecoration.VERTICAL
                )
            )
        } else {
            viewBinder.relatedLinksRecyclerView.visibility = View.GONE
            viewBinder.textView7.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val COMICS_SERVICE = "comics"
        const val EVENTS_SERVICE = "events"
        const val STORIES_SERVICE = "stories"
        const val SERIES_SERVICE = "series"
    }
}