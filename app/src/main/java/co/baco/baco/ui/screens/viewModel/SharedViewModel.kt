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

        _deposits.value = 2000.4
        _withDraws.value = 300.2
        _withDrawsPercentage.value = 0.3f
        _depositsPercentage.value = 0.7f
    }

    private fun loadItems() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val registers = getItemsUseCase()

            registers.collect {
//                it.map { register ->
//                    getValues(register.type, register.amount)
//                }
                _registers.value = it
            }
            _isLoading.postValue(false)
        }
    }


//    private fun getValues(type: Constants.RegisterType, value: String) {
//
//        if(type == Constants.RegisterType.DEPOSIT){
//            _deposits.value = _deposits.value?.plus(value.toDouble())
//        }else{
//            _withDraws.value = _withDraws.value?.plus(value.toDouble())
//        }
//        Log.d("SharedViewModel", "registers: $type, $value")
//
//    }
}