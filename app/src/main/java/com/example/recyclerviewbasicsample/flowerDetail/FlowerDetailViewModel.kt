package com.example.recyclerviewbasicsample.flowerDetail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerviewbasicsample.data.DataSource
import com.example.recyclerviewbasicsample.data.Flower

class FlowerDetailViewModel(private val dataSource: DataSource) : ViewModel() {

    fun getFlowerForId(id: Long) = dataSource.getFlowerForId(id)

    fun removeFlower(flower: Flower) = dataSource.removeFlower(flower)

}

class FlowerDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlowerDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FlowerDetailViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}