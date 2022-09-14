package com.example.recyclerviewbasicsample.flowerList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewbasicsample.databinding.HeaderItemBinding

class HeaderAdapter: RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private lateinit var binding : HeaderItemBinding
    private var flowerCount: Int = 0

    // Holder에서 데이터 세팅을 하는 주체가 된다.
    class HeaderViewHolder(binding: HeaderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val flowerNumberTextView = binding.flowerNumberText

        fun bind(flowerCount: Int) {
            flowerNumberTextView.text = flowerCount.toString()
        }

    }

    // Adapter에서 ViewHolder를 만들어준다. ( 이를 호출하는 건 LayoutManager )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        binding = HeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeaderViewHolder(binding)
    }

    // Adapter에서 ViewHolder의 데이터를 세팅하여 UI를 구성 한다. ( 이를 호출하는 건 LayoutManager )
    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(flowerCount)    // ViewHolder의 그리기 메서드를 호출한다.
    }

    // Adapter에서 가지는 모든 dataSet의 크기
    override fun getItemCount(): Int {
        return 1
    }

    // 꽃(아이템)을 더하거나 뺄 때 꽃의 수를 표시하도록 헤더를 업데이트합니다.
    fun updateFlowerCount(updatedFlowerCount: Int) {
        flowerCount = updatedFlowerCount
        notifyDataSetChanged()
    }
}