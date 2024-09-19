package co.baco.baco.ui.screens.homeScreen.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.baco.baco.common.entities.RegisterItem
import co.baco.baco.domain.model.InsertRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val insertRegisterUseCase: InsertRegisterUseCase
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: MutableLiveData<Boolean> = _isLoading

    fun insertRegister(registerItem: RegisterItem) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            insertRegisterUseCase(registerItem)
            _isLoading.postValue(false)
        }
    }
}