package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.temporary

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.Image
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.News

val sampleNews = listOf(
    News(
        id = 2,
        title = "Framonauts Splash Down Near California",
        author = "Marcia Smith",
        image = Image(
            name = "Dragon Recovery",
            url = "https://spacepolicyonline.com/wp-content/uploads/2025/03/dragon-recovery-locations-CA-300x143.png"
        ),
        publishedAt = "2025-04-05T03:32:23Z",
        url = "https://spacepolicyonline.com/news/framonauts-splash-down-near-california/",
        summary = "The four crew members of Fram2, a private astronaut mission that circled the North and South Poles for the first time, is back home. Calling themselves “Framonauts,” they spent three-and-a-half […]"
    ),
    News(
        id = 5,
        title = "LIVE COVERAGE! China Shenzhou 20 Crew Launch",
        author = "Will Robinson-Smith",
        image = Image(
            name = "Starlink Launch",
            url = "https://cdn.tlpnetwork.com/articles/2025/1745475301426.jpeg"
        ),
        publishedAt = "2025-04-05T02:33:27Z",
        url = "https://tlpnetwork.com/news/asia/live-coverage-china-shenzhou-20-crew-launch",
        summary = "LIVE COVERAGE! China Shenzhou 20 Crew Launch"
    ),
    News(
        id = 6,
        title = "NASA seeks proposals for two private astronaut missions to ISS",
        author = "Jeff Foust",
        image = Image(
            name = "ISS Proposal",
            url = "https://i0.wp.com/spacenews.com/wp-content/uploads/2023/03/ax1-dockediss.jpg?fit=1024%2C575&quality=89&ssl=1"
        ),
        publishedAt = "2025-04-04T23:40:43Z",
        url = "",
        summary = "NASA’s latest call for proposals for private astronaut missions to the space station opens the door to having those missions commanded by someone other than a former NASA astronaut."
    ),
    News(
        id = 10,
        title = "Sentinel-1 captures ground shift from Myanmar earthquake",
        author = "Katerine",
        image = Image(
            name = "Mam",
            url = "https://www.esa.int/var/esa/storage/images/esa_multimedia/images/2025/04/sentinel-1_interferogram_of_myanmar_earthquake/26673296-1-eng-GB/Sentinel-1_interferogram_of_Myanmar_earthquake_card_full.jpg"
        ),
        publishedAt = "2025-04-04T23:40:43Z",
        url = "https://www.esa.int/Applications/Observing_the_Earth/Copernicus/Sentinel-1/Sentinel-1_captures_ground_shift_from_Myanmar_earthquake",
        summary = "On 28 March 2025, a powerful magnitude 7.7 earthquake struck central Myanmar, sending shockwaves through the region. While the country is still dealing with the devasting aftermath, scientists have used radar images from the Copernicus Sentinel-1 satellites to reveal a detailed picture of how the ground shifted as a result of the quake – offering new insights into the mechanics of the tectonic Sagaing Fault and the scale of the seismic rupture."
    ),
    News(
        id = 11,
        title = "Houby",
        author = "Katerine",
        image = Image(
            name = "Mam",
            url = "https://www.esa.int/var/esa/storage/images/esa_multimedia/images/2025/04/sentinel-1_interferogram_of_myanmar_earthquake/26673296-1-eng-GB/Sentinel-1_interferogram_of_Myanmar_earthquake_card_full.jpg"
        ),
        publishedAt = "2025-04-04T23:40:43Z",
        url = "https://www.esa.int/Applications/Observing_the_Earth/Copernicus/Sentinel-1/Sentinel-1_captures_ground_shift_from_Myanmar_earthquake",
        summary = "On 28 March 2025, a powerful magnitude 7.7 earthquake struck central Myanmar, sending shockwaves through the region. While the country is still dealing with the devasting aftermath, scientists have used radar images from the Copernicus Sentinel-1 satellites to reveal a detailed picture of how the ground shifted as a result of the quake – offering new insights into the mechanics of the tectonic Sagaing Fault and the scale of the seismic rupture."
    )
)