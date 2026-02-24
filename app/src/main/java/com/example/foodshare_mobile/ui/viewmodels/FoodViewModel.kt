package com.example.foodshare_mobile.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.foodshare_mobile.ui.screens.FoodItem

class FoodViewModel : ViewModel() {
    // This holds the item the user clicked on
    private val _selectedFood = MutableStateFlow<FoodItem?>(null)
    val selectedFood: StateFlow<FoodItem?> = _selectedFood

    // Current items in the Active Cart (Pending)
    // We use a Map to track FoodItem -> Quantity
    private val _pendingCart = MutableStateFlow<Map<FoodItem, Int>>(emptyMap())
    val pendingCart: StateFlow<Map<FoodItem, Int>> = _pendingCart

    // Items that have been "Claimed" (Complete)
    private val _completeOrders = MutableStateFlow<List<FoodItem>>(emptyList())
    val completeOrders: StateFlow<List<FoodItem>> = _completeOrders

    fun selectFood(item: FoodItem) { _selectedFood.value = item }
    fun clearSelection() { _selectedFood.value = null }

    fun addToCart(item: FoodItem) {
        val current = _pendingCart.value.toMutableMap()
        current[item] = (current[item] ?: 0) + 1
        _pendingCart.value = current
    }

    fun updateQuantity(item: FoodItem, increase: Boolean) {
        val current = _pendingCart.value.toMutableMap()
        val count = current[item] ?: 0
        if (increase) {
            current[item] = count + 1
        } else if (count > 1) {
            current[item] = count - 1
        } else {
            current.remove(item)
        }
        _pendingCart.value = current
    }

    fun finalizeOrder() {
        // Move all items from pending to complete and clear pending
        val finalizedItems = _pendingCart.value.keys.toList()
        _completeOrders.value = _completeOrders.value + finalizedItems
        _pendingCart.value = emptyMap()
    }
}