package com.assessment.rickmorty.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.assessment.rickmorty.BR
import com.assessment.rickmorty.R
import com.assessment.rickmorty.data.model.Character
import com.assessment.rickmorty.databinding.FragmentCharacterListBinding
import com.assessment.rickmorty.ui.base.BaseFragment
import com.assessment.rickmorty.ui.base.NavigationCommand
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel

class CharacterListFragment : BaseFragment<FragmentCharacterListBinding, HomeViewModel>(), CharactersAdapter.ICharacterSelectedListener {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_character_list

    override val viewModel: HomeViewModel
        get() = getSharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        // recycle view setup.
        viewDataBinding().rvCharacters.layoutManager = LinearLayoutManager(this.context)
        viewDataBinding().rvCharacters.adapter = CharactersAdapter(mutableListOf(), this)
    }

    override fun onCharacterSelected(character: Character) {
        viewModel.characterSelected(character)
        navigate(NavigationCommand.To(CharacterListFragmentDirections.toCharacterDetailsFragment()))
    }

}