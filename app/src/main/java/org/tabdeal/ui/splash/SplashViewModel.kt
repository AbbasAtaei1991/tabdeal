package org.tabdeal.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import org.tabdeal.data.model.Resource
import org.tabdeal.repository.SplashRepository

class SplashViewModel @ViewModelInject constructor(
    private val repository: SplashRepository
) : ViewModel() {

    fun getUserProfile() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(repository.checkForUpdate().data))
        } catch (exception: Exception) {
            emit(Resource.error(exception.message ?: "Error Occurred!", null))
        }
    }
}