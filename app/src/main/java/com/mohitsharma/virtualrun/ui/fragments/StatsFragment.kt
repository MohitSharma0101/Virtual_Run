package com.mohitsharma.virtualrun.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mohitsharma.virtualrun.ui.viewModels.MainViewModel
import com.mohitsharma.virtualrun.ui.viewModels.StatsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatsFragment: Fragment() {

    private val viewModel: StatsViewModel by viewModels()
}