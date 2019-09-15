package com.example.marvels.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.marvels.adapter.MarvelsRecyclerViewAdapter
import com.example.marvels.adapter.MarvelsSearchRecyclerViewAdapter
import com.example.marvels.databinding.ActivityMainBinding
import com.example.marvels.model.Marvel
import com.example.marvels.viewModel.MainActivityViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.view.*
import android.view.MenuItem
import com.example.marvels.R


class MainActivity : BaseActivity(), MarvelsRecyclerViewAdapter.CharacterClickedListener {

    companion object {
        const val CHARACTER_DETAILS: String = "character_details"
    }

    var searchMenuItem: MenuItem? = null

    var marvel: Marvel? = null

    var viewBinder: ActivityMainBinding? = null

    var searchAdapter: MarvelsSearchRecyclerViewAdapter? = null

    val mainViewModel: MainActivityViewModel by lazy {
        ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinder =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        //get the viewModel
        mainViewModel.getCharacters(100, 0)?.observe(this,
            Observer { t ->
                marvel = t
                hideLoading(viewBinder!!)
                showRecyclerView(viewBinder!!, t)
            }
        )
    }

    private fun showRecyclerView(viewBinder: ActivityMainBinding, t: Marvel) {
        searchMenuItem?.setVisible(true)
        viewBinder.root.recycler_view.visibility = View.VISIBLE
        viewBinder.root.recycler_view.adapter = MarvelsRecyclerViewAdapter(t.data.results, this)
        viewBinder.root.search_recycler_view.adapter = MarvelsSearchRecyclerViewAdapter(t.data.results, this)
    }

    override fun whenCharacterClicked(position: Int?) {
        val intent = Intent(this, CharacterDetailsActivity::class.java)
        intent.putExtra(CHARACTER_DETAILS, Gson().toJson(marvel?.data?.results?.get(position!!)))
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)

        val searchManager = this@MainActivity.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(this@MainActivity.componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                if (s.isEmpty()) {
                    viewBinder?.searchRecyclerView?.visibility = View.GONE
                    viewBinder?.recyclerView?.visibility = View.VISIBLE
                } else {
                    val searchList = mainViewModel.searchInsideCharacters(marvel?.data?.results, s)
                    if (!searchList.isNullOrEmpty()) {
                        viewBinder?.searchRecyclerView?.visibility = View.VISIBLE
                        viewBinder?.recyclerView?.visibility = View.GONE
                        searchAdapter?.setSearchList(searchList)
                    }
                }
                return false
            }
        })

        searchMenuItem = menu.findItem(R.id.action_search)
        return super.onCreateOptionsMenu(menu)
    }
}
