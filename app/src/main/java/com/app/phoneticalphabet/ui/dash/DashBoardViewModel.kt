package com.app.phoneticalphabet.ui.dash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.phoneticalphabet.helpers.Resource
import com.app.phoneticalphabet.models.HighScore
import com.app.phoneticalphabet.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _res = MutableLiveData<Resource<HighScore>>()

    val res: LiveData<Resource<HighScore>>
        get() = _res

    init {
        getHighScores()
    }

    private fun getHighScores() = viewModelScope.launch {
        val cachedHighScores = repository.getHighScore()
        _res.postValue(Resource.success(cachedHighScores))
    }

    fun insertHighScores(highScores: HighScore) {
        viewModelScope.launch {
            repository.insertHighScore(highScores)
        }
    }
}