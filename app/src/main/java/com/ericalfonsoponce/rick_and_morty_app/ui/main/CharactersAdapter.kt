package com.ericalfonsoponce.rick_and_morty_app.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ericalfonsoponce.rick_and_morty_app.domain.model.Character
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ericalfonsoponce.rick_and_morty_app.R
import com.ericalfonsoponce.rick_and_morty_app.databinding.ItemCharacterBinding
import com.ericalfonsoponce.rick_and_morty_app.helpers.Constants

class CharactersAdapter(
    private val onCharacterClick: (Character) -> Unit
): ListAdapter<Character, CharactersAdapter.CharacterViewHolder>(CharactersDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position), onCharacterClick)
    }

    class CharacterViewHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Character,
            onCharacterClick: (Character) -> Unit
        ) {

            val context = binding.root.context

            binding.character = item
            binding.layoutItem.setOnClickListener { onCharacterClick(item) }

            Glide.with(context)
                .load(item.image)
                .error(ContextCompat.getDrawable(context, R.drawable.rick_morty_loading))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(GenericTransitionOptions.with(com.bumptech.glide.R.anim.abc_fade_in))
                .into(binding.imageCharacter)

            with(item) {
                binding.textStatusCharacter.setTextColor(
                    ContextCompat.getColor(
                        context,
                        if (status?.equals(Constants.ALIVE, true) == true)
                            R.color.green
                        else if (status?.equals(Constants.DEAD, true) == true)
                            R.color.red
                        else R.color.white
                    )
                )

                Glide.with(context)
                    .load(
                        ContextCompat.getDrawable(
                            context,
                            if (gender?.equals(Constants.MALE, true) == true)
                                R.drawable.ic_gender_male
                            else if (gender?.equals(Constants.FEMALE, true) == true)
                                R.drawable.ic_gender_female
                            else R.drawable.ic_gender_unknown
                        )
                    )
                    .error(ContextCompat.getDrawable(context, R.drawable.ic_gender_unknown))
                    .into(binding.imageGender)
            }
        }

        companion object {
            fun from(parent: ViewGroup): CharacterViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
                return CharacterViewHolder(binding)
            }
        }
    }

    class CharactersDiffCallback() : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }
    }
}