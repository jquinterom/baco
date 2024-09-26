package co.baco.baco.ui.screens.homeScreen.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.baco.baco.common.entities.Constants
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

    private val _registerItem = mutableStateOf<RegisterItem?>(null)
    val registerItem: MutableState<RegisterItem?> = _registerItem

    private fun clearRegister() {
        _registerItem.value = null
    }

    fun insertRegister(registerItem: RegisterItem) {
        viewModelScope.launch {
            _isLoading.value = true
            insertRegisterUseCase(registerItem)
            clearRegister()
            _isLoading.value = false
        }
    }

    fun updateComment(newComment: String) {
        val comment = newComment.ifEmpty { null }

        _registerItem.value = _registerItem.value?.copy(comment = comment)
            ?: RegisterItem(
                amount = 0f,
                type = Constants.RegisterType.NONE,
                comment = comment
            )
    }

    fun updateAmount(newAmount: Float) {
        _registerItem.value = _registerItem.value?.copy(amount = newAmount)
            ?: RegisterItem(
                amount = newAmount,
                type = Constants.RegisterType.NONE
            )
    }

    fun updateType(newType: Constants.RegisterType) {
        _registerItem.value = _registerItem.value?.copy(type = newType)
            ?: RegisterItem(amount = 0f, type = newType)
    }


}