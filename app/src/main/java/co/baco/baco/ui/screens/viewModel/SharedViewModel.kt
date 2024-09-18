package co.baco.baco.ui.screens.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.baco.baco.common.entities.RegisterItem
import co.baco.baco.domain.model.GetRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getItemsUseCase: GetRegisterUseCase
) : ViewModel() {

    private val _registers = MutableLiveData<List<RegisterItem>>()
    val items: LiveData<List<RegisterItem>> = _registers

    private val _isLoading = MutableLiveData<Boolean>()

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val registers = getItemsUseCase()
            registers.collect {
                _registers.value = it
            }
            _isLoading.postValue(false)
        }
    }
}