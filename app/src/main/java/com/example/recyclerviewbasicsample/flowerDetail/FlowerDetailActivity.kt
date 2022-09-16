package com.example.recyclerviewbasicsample.flowerDetail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerviewbasicsample.R
import com.example.recyclerviewbasicsample.databinding.FlowerDetailActivityBinding
import com.example.recyclerviewbasicsample.flowerList.FLOWER_ID

class FlowerDetailActivity : AppCompatActivity() {

    private lateinit var binding : FlowerDetailActivityBinding
    private val flowerDetailViewModel by viewModels<FlowerDetailViewModel> {
        FlowerDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FlowerDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var currentFlowerId: Long? = null

        val flowerName = binding.flowerDetailName // TextView = findViewById(R.id.flower_detail_name)
        val flowerImage = binding.flowerDetailImage // ImageView = findViewById(R.id.flower_detail_image)
        val flowerDescription = binding.flowerDetailDescription // TextView = findViewById(R.id.flower_detail_description)
        val removeFlowerButton = binding.removeButton // Button = findViewById(R.id.remove_button)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentFlowerId = bundle.getLong(FLOWER_ID)
        }

        currentFlowerId?.let {
            val currentFlower = flowerDetailViewModel.getFlowerForId(it)
            flowerName.text = currentFlower?.name
            if (currentFlower?.image == null) {
                flowerImage.setImageResource(R.drawable.rose)
            } else {
                flowerImage.setImageResource(currentFlower.image)
            }
            flowerDescription.text = currentFlower?.description

            removeFlowerButton.setOnClickListener {
                if (currentFlower != null) {
                    flowerDetailViewModel.removeFlower(currentFlower)
                }
                finish()
            }
        }
    }
}