package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel.events

sealed class LaunchesEvent {
    data class OnSearchQueryChange(val query: String) : LaunchesEvent()
    object OnDownloadRequested : LaunchesEvent()
    object OnClearDatabase : LaunchesEvent()
}
