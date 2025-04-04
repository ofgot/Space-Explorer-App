package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel

import androidx.lifecycle.ViewModel
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.Image
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.Launch
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewsDetailsViewModel : ViewModel() {
    val sampleNews = listOf(
        News(
            id = 1,
            title = "NASA Successfully Tests New Mars Lander Engines",
            author = "Eric Berger",
            image = Image(
                name = "Mars Lander",
                url = "https://example.com/images/mars_lander.jpg"
            ),
            publishedAt = "2025-04-01T15:30:00Z",
            url = "https://space-news.com/nasa-mars-lander-engine-test",
            summary = "NASA has successfully tested its next-generation Mars lander engines, preparing for future manned missions to the Red Planet."
        ),
        News(
            id = 2,
            title = "SpaceX Breaks Reuse Record With Falcon 9 Launch",
            author = "Sarah Kaplan",
            image = Image(
                name = "Falcon 9 Launch",
                url = "https://example.com/images/falcon9.jpg"
            ),
            publishedAt = "2025-04-02T10:00:00Z",
            url = "https://spacereports.com/falcon9-reuse-record",
            summary = "SpaceX has achieved a milestone by launching the same Falcon 9 booster for the 20th time, setting a new industry standard."
        ),
        News(
            id = 3,
            title = "Blue Origin Announces First Crewed Flight of New Shepard",
            author = "Jessica Watkins",
            image = Image(
                name = "New Shepard",
                url = "https://example.com/images/new_shepard.jpg"
            ),
            publishedAt = "2025-04-03T12:45:00Z",
            url = "https://blueupdate.com/new-shepard-crew-flight",
            summary = "Blue Origin is ready to send its first passengers into suborbital space on its newly updated New Shepard vehicle."
        ),
        News(
            id = 4,
            title = "China Launches Earth Observation Satellite From Taiyuan",
            author = "Wei Zhang",
            image = Image(
                name = "Earth Observation Satellite",
                url = "https://example.com/images/china_satellite.jpg"
            ),
            publishedAt = "2025-04-03T18:00:00Z",
            url = "https://chinapace.cn/earth-observation-launch",
            summary = "China has successfully launched a new Earth observation satellite that will aid in agriculture, climate studies, and disaster monitoring."
        ),
        News(
            id = 5,
            title = "ESA and Roscosmos Discuss Joint Mission to Venus",
            author = "Luca Rossi",
            image = Image(
                name = "Venus Mission Concept",
                url = "https://example.com/images/venus_mission.jpg"
            ),
            publishedAt = "2025-04-04T09:00:00Z",
            url = "https://spacecollab.eu/esa-roscosmos-venus",
            summary = "The European Space Agency and Roscosmos are in talks about a future collaborative mission to study Venus' atmosphere and geology."
        )
    )

    private val _state = MutableStateFlow(sampleNews)
    val state = _state.asStateFlow()

    private val _newsState = MutableStateFlow<News?>(null)
    val newsState = _newsState.asStateFlow()

    fun applyNews(id: String) {
        val news = sampleNews.find { it.id.toString() == id }
        _newsState.update { news }
    }

}