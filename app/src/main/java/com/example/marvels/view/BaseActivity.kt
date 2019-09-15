package com.example.marvels.view

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.marvels.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.view.*

open class BaseActivity : AppCompatActivity() {

    fun hideLoading(viewBinder: ActivityMainBinding) {
        viewBinder.root.progress_circular.visibility = View.GONE
    }

    fun showLoading(viewBinder: ActivityMainBinding) {
        viewBinder.root.progress_circular.visibility = View.VISIBLE
    }
}