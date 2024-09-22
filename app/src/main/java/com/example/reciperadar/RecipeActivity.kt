package com.example.reciperadar

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.bumptech.glide.Glide
import com.example.reciperadar.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRecipeBinding
    private var recipeImgCrop = true;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(intent.getStringExtra("IMG")).into(binding.recipeImage)
        binding.recipeTitle.text = intent.getStringExtra("TITLE")
        binding.recipeTime.text = intent.getStringExtra("TIME")
        binding.ingredientsText.text = intent.getStringExtra("INGREDIENTS")
        binding.directionsText.text = intent.getStringExtra("DIRECTIONS")

        binding.resizeImageButton.setOnClickListener{
            if (recipeImgCrop) {
                binding.recipeImage.scaleType = ImageView.ScaleType.FIT_CENTER
                Glide.with(this).load(intent.getStringExtra("IMG")).into(binding.recipeImage)
                binding.imageShade.visibility = View.GONE
                recipeImgCrop = !recipeImgCrop
            } else {
                binding.recipeImage.scaleType = ImageView.ScaleType.CENTER_CROP
                Glide.with(this).load(intent.getStringExtra("IMG")).into(binding.recipeImage)
                binding.imageShade.visibility = View.VISIBLE
                recipeImgCrop = !recipeImgCrop
            }
        }

        binding.backButton3.setOnClickListener{finish()}
        binding.ingredientsText.visibility = View.VISIBLE

        // Directions button functionality
        binding.directionsButton.setOnClickListener{
            // Switch visibility: Hide ingredients, show directions
            binding.ingredientsText.visibility = View.GONE
            binding.directionsText.visibility = View.VISIBLE
            // Highlight directions button, unhighlight ingredients button
            highlightButton(binding.directionsButton, true)
            highlightButton(binding.ingredientsButton, false)
        }

        // Ingredients button functionality
        binding.ingredientsButton.setOnClickListener {
            // Switch visibility: Show ingredients, hide directions
            binding.ingredientsText.visibility = View.VISIBLE
            binding.directionsText.visibility = View.GONE
            // Highlight ingredients button, unhighlight directions button
            highlightButton(binding.ingredientsButton, true)
            highlightButton(binding.directionsButton, false)
        }
    }

    // function to highlight or unhighlight a button
    private fun highlightButton(button: View, isHighlighted: Boolean) {
        if (isHighlighted) {
            button.setBackgroundColor(getColor(R.color.dark_red)) // highlight color
            (button as? android.widget.Button)?.setTextColor(getColor(R.color.white)) // white text
        } else {
            button.setBackgroundColor(getColor(R.color.light_red3)) // light red background
            (button as? android.widget.Button)?.setTextColor(getColor(R.color.black)) // black text
        }
    }
}