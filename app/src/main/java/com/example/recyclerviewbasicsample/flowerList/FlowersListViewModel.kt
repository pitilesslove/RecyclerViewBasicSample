package com.example.recyclerviewbasicsample.flowerList

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerviewbasicsample.data.Flower
import com.example.recyclerviewbasicsample.data.flowerList

class FlowersListViewModel(val flowerList: List<Flower>) : ViewModel() {
    val flowersLiveData = MutableLiveData(flowerList)

}

class FlowersListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlowersListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FlowersListViewModel(
                flowerList = flowerList(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}