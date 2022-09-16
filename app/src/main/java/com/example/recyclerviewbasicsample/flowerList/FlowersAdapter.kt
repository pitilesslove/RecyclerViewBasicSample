package com.example.recyclerviewbasicsample.flowerList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewbasicsample.R
import com.example.recyclerviewbasicsample.data.Flower
import com.example.recyclerviewbasicsample.databinding.FlowerItemBinding
import com.example.recyclerviewbasicsample.utils.ALog

class FlowersAdapter(private val onClick: (Flower) -> Unit) : ListAdapter<Flower, FlowersAdapter.FlowerViewHolder>(FlowerDiffCallback) {

    private lateinit var binding : FlowerItemBinding

    init {
        ALog.d("FlowersAdapter init $this")
    }

    class FlowerViewHolder(binding: FlowerItemBinding, val _onClick: (Flower) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        private val flowerTextView = binding.flowerText
        private val flowerImageView = binding.flowerImage
        private var currentFlower: Flower? = null

        init {
            ALog.d("FlowerViewHolder init $this")
            itemView.setOnClickListener {
                currentFlower?.let {
                    _onClick(it)
                }
            }
        }

        fun bind(flower: Flower) {
            ALog.d("bind $flower")
            currentFlower = flower

            flowerTextView.text = flower.name
            if (flower.image != null)
                flowerImageView.setImageResource(flower.image)
            else
                flowerImageView.setImageResource(R.drawable.rose)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        ALog.d("onCreateViewHolder $viewType")
        binding = FlowerItemBinding.inflate(LayoutInflater.from(parent.context), parent ,false)
        return FlowerViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        ALog.d("onBindViewHolder $position")
        val flower = getItem(position)
        holder.bind(flower)
    }

    object FlowerDiffCallback : DiffUtil.ItemCallback<Flower>() {
        override fun areItemsTheSame(oldItem: Flower, newItem: Flower): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Flower, newItem: Flower): Boolean {
            return oldItem.id == newItem.id
        }
    }

}