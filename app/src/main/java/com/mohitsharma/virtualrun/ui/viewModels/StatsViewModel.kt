package com.mohitsharma.virtualrun.ui.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.mohitsharma.virtualrun.repositories.MainRepository

class StatsViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
): ViewModel() {

}