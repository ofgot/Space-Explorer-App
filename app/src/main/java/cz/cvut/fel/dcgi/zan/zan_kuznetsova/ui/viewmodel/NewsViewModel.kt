package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.News
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.temporary.sampleNews
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    // State

    private val _newsState = MutableStateFlow<News?>(null)
    val newsState = _newsState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _commentState = MutableStateFlow("")
    val commentState = _commentState.asStateFlow()

    private val _isEditing = MutableStateFlow(false)
    val isEditing = _isEditing.asStateFlow()

    // Data

    val news = repository.getAllNews()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun downloadNews() {
        viewModelScope.launch {
            if (!repository.hasNews()) {
                repository.insertNews(sampleNews)
            }
        }
    }

    fun checkAndDownloadNewsIfNeeded() {
        viewModelScope.launch {
            if (!repository.hasNews()) {
                repository.insertNews(sampleNews)
            }
        }
    }

    fun clearDatabase() {
        viewModelScope.launch {
            repository.deleteAllNews()
        }
    }

    // Searching

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    val filteredNews = combine(news, searchQuery) { list, query ->
        if (query.isBlank()) list
        else list.filter { it.title.contains(query, ignoreCase = true) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    // Comment

    fun applyNews(id: Int) {
        viewModelScope.launch {
            val news = repository.getNewsById(id)
            _newsState.value = news
            _commentState.value = news.comment
        }
    }

    fun onCommentChange(value: String) {
        _commentState.value = value
    }

    fun saveComment() {
        val currentNews = _newsState.value ?: return
        val updatedComment = _commentState.value

        viewModelScope.launch {
            repository.updateComment(currentNews.id, updatedComment)
            _newsState.value = currentNews.copy(comment = updatedComment)
        }
    }

    // Editing

    fun toggleEditing() {
        _isEditing.value = !_isEditing.value
    }

    fun setEditing(value: Boolean) {
        _isEditing.value = value
    }

}