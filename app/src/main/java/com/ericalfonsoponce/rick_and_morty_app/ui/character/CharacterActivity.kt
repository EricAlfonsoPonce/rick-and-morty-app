package com.ericalfonsoponce.rick_and_morty_app.ui.character

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ericalfonsoponce.rick_and_morty_app.R
import com.ericalfonsoponce.rick_and_morty_app.databinding.ActivityCharacterBinding
import com.ericalfonsoponce.rick_and_morty_app.helpers.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterBinding
    private lateinit var viewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCharacterBinding.inflate(layoutInflater, null, false)
        viewModel = ViewModelProvider(this)[CharacterViewModel::class.java]

        binding.activity = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        initObservers()
        viewModel.checkIntent(intent.extras)
    }


    fun initObservers() {
        lifecycleScope.launch {
            viewModel.uiState
                .map { it.characterDetails }
                .distinctUntilChanged()
                .collect { character ->
                    Glide.with(this@CharacterActivity)
                        .load(character.image)
                        .error(
                            ContextCompat.getDrawable(
                                this@CharacterActivity,
                                R.drawable.rick_morty_loading
                            )
                        )
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transition(GenericTransitionOptions.with(com.bumptech.glide.R.anim.abc_fade_in))
                        .into(binding.imageCharacter)

                    binding.imageGender.setImageResource(
                        when (character.gender?.capitalize()) {
                            Constants.MALE -> R.drawable.ic_gender_male
                            Constants.FEMALE -> R.drawable.ic_gender_female
                            else -> R.drawable.ic_gender_unknown
                        }
                    )

                    binding.textStatus.setTextColor(
                        ContextCompat.getColor(
                            this@CharacterActivity,
                            when (character.status?.capitalize()) {
                                Constants.ALIVE -> R.color.green
                                Constants.DEAD -> R.color.red
                                else -> R.color.black
                            }
                        )
                    )
                }
        }
    }


}