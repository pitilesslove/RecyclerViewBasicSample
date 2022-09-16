package com.example.recyclerviewbasicsample.flowerList

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import com.example.recyclerviewbasicsample.addFlower.AddFlowerActivity
import com.example.recyclerviewbasicsample.addFlower.FLOWER_DESCRIPTION
import com.example.recyclerviewbasicsample.addFlower.FLOWER_NAME
import com.example.recyclerviewbasicsample.data.Flower
import com.example.recyclerviewbasicsample.databinding.ActivityMainBinding
import com.example.recyclerviewbasicsample.utils.ALog

const val FLOWER_ID = "flower id"
class FlowersListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val newFlowerActivityRequestCode = 1
    private val activityResultLauncher : ActivityResultLauncher<Intent>

    init {
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == newFlowerActivityRequestCode && it.resultCode == Activity.RESULT_OK) {
                it.data?.let { data ->
                    val flowerName = data.getStringExtra(FLOWER_NAME)
                    val flowerDescription = data.getStringExtra(FLOWER_DESCRIPTION)
                    flowersListViewModel.insertFlower(flowerName, flowerDescription)
                }
            }
        }
    }


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
            ALog.d("flowersLiveData.observe")
            it?.let {
                flowersAdapter.submitList(it)
                headerAdapter.updateFlowerCount(it.size)
            }
        }

        val fab = binding.fab
        fab.setOnClickListener {
            ALog.d("fabOnClick")
            fabOnClick()
        }
    }

    private fun adapterOnClick(flower: Flower) {
        ALog.d("adapterOnClick flower=$flower")
        val intent = Intent(this, AddFlowerActivity::class.java)
        activityResultLauncher.launch(intent)
    }

    /**
     * FAB 클릭 시, 꽃을 리스트에 추가함
     */
    private fun fabOnClick() {
        val intent = Intent(this, AddFlowerActivity::class.java)
        activityResultLauncher.launch(intent)
    }

}