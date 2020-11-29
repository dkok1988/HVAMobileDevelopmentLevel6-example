package nl.hva.level6example.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.hva.level6example.repository.TriviaRepository

class TriviaViewModel(application: Application) : AndroidViewModel(application) {
    private val triviaRepository = TriviaRepository()

    val trivia = triviaRepository.trivia

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    val errorText: LiveData<String>
        get() = _errorText

    fun getTriviaNumber() {
        viewModelScope.launch {
            try {
                triviaRepository.getRandomNumberTrivia()
            } catch (error: TriviaRepository.TriviaRefreshError) {
                _errorText.value = error.message
                Log.e("Triva error", error.cause.toString())
            }
        }
    }
}
