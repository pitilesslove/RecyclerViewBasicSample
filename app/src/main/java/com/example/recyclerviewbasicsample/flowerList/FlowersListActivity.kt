package com.example.recyclerviewbasicsample.flowerList

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewbasicsample.R
import com.example.recyclerviewbasicsample.data.Flower
import com.example.recyclerviewbasicsample.databinding.ActivityMainBinding

const val FLOWER_ID = "flower id"
class FlowersListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val newFlowerActivityRequestCode = 1
    private val flowersListViewModel by viewModels<FlowersListViewModel> {
        FlowersListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val headerAdapter = HeaderAdapter()
        val flowersAdapter = FlowersAdapter { flower -> adapterOnClick(flower) }
        val concatAdapter = ConcatAdapter(headerAdapter, flowersAdapter)

        val recyclerView = binding.recyclerView
        recyclerView.adapter = concatAdapter

        flowersListViewModel.flowersLiveData.observe(this) {
            it?.let {
                flowersAdapter.submitList(it as MutableList<Flower>)
                headerAdapter.updateFlowerCount(it.size)
            }
        }

    }

    private fun adapterOnClick(flower: Flower) {
        // nav graph로 다른 Activity로 보내준다.
    }

}