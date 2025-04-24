package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel.events

sealed class NewsEvent {
    data class OnSearchQueryChange(val query: String) : NewsEvent()
    object OnDownloadRequested : NewsEvent()
    object OnClearDatabase : NewsEvent()

    data class OnCommentChange(val value: String) : NewsEvent()
    object OnSaveComment : NewsEvent()
    object OnToggleEditing : NewsEvent()
    object OnEditingFinished : NewsEvent()

    data class OnToggleSelection(val id: Int) : NewsEvent()
    object OnDeleteSelected : NewsEvent()
}
