package com.arif.paging_android.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arif.paging_android.repository.StarWarRepository


class StarWarViewModel(private val repository: StarWarRepository) : ViewModel() {
    val characterList = repository.getCharacter().cachedIn(viewModelScope)
    val starShipList = repository.getStarship().cachedIn(viewModelScope)
    val planetList = repository.getPlanet().cachedIn(viewModelScope)


}

