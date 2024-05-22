package com.example.geniustrade.ViewModel

import androidx.lifecycle.ViewModel
import com.example.geniustrade.Repository.MainRepository

class MainViewModel(
    val repository: MainRepository): ViewModel(){

        constructor():this(MainRepository())

    fun loadData() = repository.items


}