package com.example.foodshare_mobile.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.foodshare_mobile.ui.screens.FoodItem

class FoodViewModel : ViewModel() {
    private val _selectedFood = MutableStateFlow<FoodItem?>(null)
    val selectedFood: StateFlow<FoodItem?> = _selectedFood

    // List of items currently in the cart
    private val _cartItems = MutableStateFlow<List<FoodItem>>(listOf(
        FoodItem("Kamande", "2hrs left"),
        FoodItem("Ugali Mrenda", "3hrs left")
    ))
    val cartItems: StateFlow<List<FoodItem>> = _cartItems

    fun selectFood(item: FoodItem) { _selectedFood.value = item }
    fun clearSelection() { _selectedFood.value = null }
    fun addToCart(item: FoodItem) { _cartItems.value = _cartItems.value + item }
}
