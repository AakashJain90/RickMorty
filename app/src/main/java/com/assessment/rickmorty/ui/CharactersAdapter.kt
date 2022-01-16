package com.assessment.rickmorty.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.assessment.rickmorty.data.model.Character
import com.assessment.rickmorty.databinding.RowItemCharacterBinding
import com.assessment.rickmorty.ui.base.BaseRecyclerViewAdapter

class CharactersAdapter(items: MutableList<Character>, val listener:ICharacterSelectedListener) : BaseRecyclerViewAdapter<Character>(items) {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val myViewHolder = MyViewHolder(
            RowItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        myViewHolder.binding.cardCharacter.setOnClickListener {
            val position: Int = it.tag as Int
            listener.onCharacterSelected(items[position])
        }

        return myViewHolder
    }

    inner class MyViewHolder(val binding: RowItemCharacterBinding) : BaseViewHolder(binding.root) {

        override fun onBind(position: Int) {
            binding.character = items[position]
            binding.executePendingBindings()
            binding.cardCharacter.tag = position
        }
    }

    interface ICharacterSelectedListener {
        fun onCharacterSelected(character: Character)
    }

}