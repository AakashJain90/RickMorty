package com.assessment.rickmorty.ui

import com.assessment.rickmorty.BR
import com.assessment.rickmorty.R
import com.assessment.rickmorty.databinding.FragmentCharacterDetailsBinding
import com.assessment.rickmorty.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel

class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailsBinding, HomeViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_character_details

    override val viewModel: HomeViewModel
        get() = getSharedViewModel()

}