package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.News
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.temporary.sampleNews
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val _newsState = MutableStateFlow<News?>(null)
    val newsState = _newsState.asStateFlow()

    fun applyNews(id: Int) {
        viewModelScope.launch {
            _newsState.value = repository.getNewsById(id)
        }
    }

    val news = repository.getAllNews()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun downloadNews() {
        viewModelScope.launch {
            repository.insertNews(sampleNews)
        }
    }

    fun clearDatabase() {
        viewModelScope.launch {
            repository.deleteAllNews()
        }
    }
}