package com.assessment.rickmorty.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.assessment.rickmorty.BR
import com.assessment.rickmorty.R
import com.assessment.rickmorty.databinding.ActivityCharactersBinding
import com.assessment.rickmorty.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeActivity : BaseActivity<ActivityCharactersBinding, HomeViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_characters

    override val viewModel: HomeViewModel
        get() = getViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // configure action bar.
        val navController = findNavController(R.id.fragment_navigation_host)
        setupActionBarWithNavController(navController, AppBarConfiguration(navController.graph))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_navigation_host)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}