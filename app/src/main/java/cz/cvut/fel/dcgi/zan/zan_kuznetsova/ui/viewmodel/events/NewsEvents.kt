package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel.events

sealed class NewsEvent {
    data class OnSearchQueryChange(val query: String) : NewsEvent()
    object OnDownloadRequested : NewsEvent()
    object OnClearDatabase : NewsEvent()
}
