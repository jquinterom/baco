package co.baco.baco.ui.screens.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.baco.baco.common.entities.RegisterItem
import co.baco.baco.common.utils.Constants
import co.baco.baco.domain.model.GetRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getItemsUseCase: GetRegisterUseCase
) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()

    private val _registers = MutableLiveData<List<RegisterItem>>()
    val items: LiveData<List<RegisterItem>> = _registers

    private val _deposits = MutableLiveData<Double>()
    val deposits: LiveData<Double> = _deposits

    private val _withDraws = MutableLiveData<Double>()
    val withDraws: LiveData<Double> = _withDraws

    private val _depositsPercentage = MutableLiveData<Float>()
    val depositsPercentage: LiveData<Float> = _depositsPercentage

    private val _withDrawsPercentage = MutableLiveData<Float>()
    val withDrawsPercentage: LiveData<Float> = _withDrawsPercentage

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val registers = getItemsUseCase()

            registers.collect { registerList ->
                registerList.forEach { register ->
                    getValues(register.type, register.amount)
                }

                calculatePercentage()

                _registers.value = registerList
            }
            _isLoading.postValue(false)
        }
    }

    private fun getValues(type: Constants.RegisterType, value: String) {
        if (type == Constants.RegisterType.DEPOSIT) {
            _deposits.value = (_deposits.value ?: 0.0) + value.toDouble()
        } else {
            _withDraws.value = (_withDraws.value ?: 0.0) + value.toDouble()
        }
    }

    private fun calculatePercentage() {
        val deposits = deposits.value ?: 0.0
        val withDraws = withDraws.value ?: 0.0
        val total = deposits + withDraws
        if (total > 0) {
            _depositsPercentage.value = (deposits / total ).toFloat()
            _withDrawsPercentage.value = (withDraws / total).toFloat()
        }
    }
}