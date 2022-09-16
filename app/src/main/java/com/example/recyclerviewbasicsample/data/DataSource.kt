package com.example.recyclerviewbasicsample.data

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData

class DataSource(resources: Resources) {
    private val initialFlowerList = flowerList(resources)
    private val flowersLiveData = MutableLiveData(initialFlowerList)

    // 꽃을 추가하고 liveData에 값을 넣는다.
    fun addFlower(flower: Flower) {
        val currentList = flowersLiveData.value
        if (currentList == null) {
            flowersLiveData.postValue(listOf(flower))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, flower)
            flowersLiveData.postValue(updatedList)
        }
    }

    // 꽃을 liveData에서 지워준다.
    fun removeFlower(flower: Flower) {
        val currentList = flowersLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(flower)
            flowersLiveData.postValue(updatedList)
        }
    }

    //ID로 꽃을 검색하여 전달해준다. 없으면 null
    fun getFlowerForId(id: Long): Flower? {
        flowersLiveData.value?.let { flowers ->
            return flowers.firstOrNull { it.id == id }

        }
        return null
    }

    fun getFlowerList() = flowersLiveData

    // 랜덤한 꽃의 image리소스를 찾아준다.
    fun getRandomFlowerImageAsset(): Int? {
        val randomNumber = (initialFlowerList.indices).random()
        return initialFlowerList[randomNumber].image
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }

}