package com.example.dessertclicker.ui.theme

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.determineDessertToShow
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DessertViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DessertUIState())
    val uiState: StateFlow<DessertUIState> = _uiState.asStateFlow()

    private val currentIndex: Int = 0;

    init {}

    fun onDessertTap() {
       val isIndexUpdate  =  isDessertIndexUpdated(_uiState.value.dessertSold.inc())
        val currentDessert = Datasource.dessertList[_uiState.value.currentDessertIndex]
        _uiState.value = DessertUIState(
            revenue = _uiState.value.revenue + currentDessert.price,
            dessertSold = _uiState.value.dessertSold.inc(),
    currentDessertIndex = _uiState.value.currentDessertIndex.inc()

            )
    }


    fun isDessertIndexUpdated(
        dessertSold: Int
    ): Boolean {
        var dessertToShow = Datasource.dessertList.first()
        for (dessert in Datasource.dessertList) {
            if (dessertSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
                return true
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return false
    }

}