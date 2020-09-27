package com.mohitsharma.virtualrun.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mohitsharma.virtualrun.ui.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackingFragment: Fragment() {

    private val viewModel: MainViewModel by viewModels()
}