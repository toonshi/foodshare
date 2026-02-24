package com.example.foodshare_mobile.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.foodshare_mobile.ui.screens.FoodItem

class FoodViewModel : ViewModel() {
    // This holds the item the user clicked on
    private val _selectedFood = MutableStateFlow<FoodItem?>(null)
    val selectedFood: StateFlow<FoodItem?> = _selectedFood

    fun selectFood(item: FoodItem) {
        _selectedFood.value = item
    }

    fun clearSelection() {
        _selectedFood.value = null
    }
}
