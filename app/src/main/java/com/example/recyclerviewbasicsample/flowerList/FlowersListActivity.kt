package com.example.recyclerviewbasicsample.flowerList

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewbasicsample.R

const val FLOWER_ID = "flower id"

class FlowersListActivity : AppCompatActivity() {
    private val newFlowerActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}