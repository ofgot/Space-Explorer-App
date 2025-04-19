package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.temporary

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.Agency
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.Image
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.Launch
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.Rocket
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.RocketDetails
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.Status


val sampleLaunches = listOf(
    Launch(
        id = "1",
        name = "Falcon 9 | Starlink Group 7-2",
        status = Status(name = "Launch Successful", abbrev = "Success"),
        net = "2025-03-21T08:45:00Z",
        image = Image(
            name = "Falcon 9 Image",
            url = "https://thespacedevs-prod.nyc3.digitaloceanspaces.com/media/images/spectrum_on_the_image_20250321072643.jpeg",
        ),
        agency = Agency(
            name = "SpaceX",
            abbrev = "SPX",
            country = "USA",
            description = "Private aerospace company founded by Elon Musk.",
            logo = Image(
                name = "SpaceX Logo",
                url = "https://example.com/spacex_logo.png"
            ),
            totalLaunchCount = 250,
            successfulLaunches = 240,
            failedLaunches = 10,
            wikiUrl = "https://en.wikipedia.org/wiki/SpaceX",
            infoUrl = "https://spacex.com"
        ),
        rocket = Rocket(
            name = "Falcon 9 Block 5",
            rocketDetails = RocketDetails(
                infoUrl = "https://example.com/falcon9/info",
                wikiUrl = "https://en.wikipedia.org/wiki/Falcon_9",

                height = 70.0,
                diameter = 3.7,
                maxStage = 2,
                massToLEO = 22800.0,
                massToGTO = 8300.0,
                liftoffMass = 549000.0,
                liftoffThrust = 7607.0,

                successfulLaunches = 240,
                maidenFlight = "2010-06-04",
                failedLaunches = 10
            )
        ),
        location = "USA",
        webcastLive = ""
    ),

    Launch(
        id = "2",
        name = "Soyuz 2.1a | Progress MS-21",
        status = Status(name = "Launch Successful", abbrev = "Success"),
        net = "2025-03-18T04:20:00Z",
        image = Image(
            name = "Soyuz Launch",
            url = "https://thespacedevs-prod.nyc3.digitaloceanspaces.com/media/images/sputnik_8k74ps_image_20210830185541.jpg",
        ),
        agency = Agency(
            name = "Roscosmos",
            abbrev = "ROSCOSMOS",
            country = "Russia",
            description = "State Corporation for Space Activities.",
            logo = Image(
                name = "Roscosmos Logo",
                url = "https://example.com/roscosmos_logo.png"
            ),
            totalLaunchCount = 3500,
            successfulLaunches = 3300,
            failedLaunches = 200,
            wikiUrl = "https://en.wikipedia.org/wiki/Roscosmos",
            infoUrl = "https://roscosmos.ru"
        ),
        rocket = Rocket(
            name = "Soyuz 2.1a",
            rocketDetails = RocketDetails(
                infoUrl = "https://example.com/soyuz/info",
                wikiUrl = "",

                height = 46.1,
                diameter = 2.95,
                maxStage = 3,
                massToLEO = 8200.0,
                massToGTO = 0.0,
                liftoffMass = 312000.0,
                liftoffThrust = 4140.0,

                successfulLaunches = 3300,
                maidenFlight = "2004-11-08",
                failedLaunches = 200
            )
        ),
        location = "Russia",
        webcastLive = "https://www.youtube.com/watch?v=nULmCwYcWwk&list=RDnULmCwYcWwk&start_radio=1"
    ),
    Launch(
        id = "4",
        name = "Falcon 9 | Starlink Group 6-27",
        status = Status("Launch Successful", "Success"),
        net = "2025-03-25T14:30:00Z",
        location = "Cape Canaveral, USA",
        webcastLive = "https://youtube.com/spacex/starlink",
        image = Image(
            name = "Falcon 9 launching",
            url = "https://example.com/falcon9.jpg"
        ),
        agency = Agency(
            name = "SpaceX",
            abbrev = "SPX",
            country = "USA",
            description = "Private aerospace company",
            logo = Image("SpaceX Logo", "https://example.com/spacex-logo.png"),
            totalLaunchCount = 287,
            successfulLaunches = 275,
            failedLaunches = 12,
            wikiUrl = "https://en.wikipedia.org/wiki/SpaceX",
            infoUrl = "https://spacex.com"
        ),
        rocket = Rocket(
            name = "Falcon 9",
            rocketDetails = RocketDetails(
                infoUrl = "https://spacex.com/falcon9",
                wikiUrl = "https://en.wikipedia.org/wiki/Falcon_9",
                height = 70.0,
                diameter = 3.7,
                maxStage = 2,
                massToLEO = 22800.0,
                massToGTO = 8300.0,
                liftoffMass = 549054.0,
                liftoffThrust = 7607.0,
                successfulLaunches = 260,
                maidenFlight = "2010-06-04",
                failedLaunches = 2
            )
        )
    ),
    Launch(
        id = "3",
        name = "Ariane 5 | JUICE",
        status = Status("Launch Successful", "Success"),
        net = "2023-04-14T12:15:00Z",
        location = "Guiana Space Centre, French Guiana",
        webcastLive = "https://youtube.com/esa/juice",
        image = Image(
            name = "Ariane 5 JUICE",
            url = "https://example.com/ariane5-juice.jpg"
        ),
        agency = Agency(
            name = "European Space Agency",
            abbrev = "ESA",
            country = "Europe",
            description = "Intergovernmental space agency of Europe",
            logo = Image("ESA Logo", "https://example.com/esa-logo.png"),
            totalLaunchCount = 85,
            successfulLaunches = 83,
            failedLaunches = 2,
            wikiUrl = "https://en.wikipedia.org/wiki/European_Space_Agency",
            infoUrl = "https://esa.int"
        ),
        rocket = Rocket(
            name = "Ariane 5",
            rocketDetails = RocketDetails(
                infoUrl = "https://esa.int/ariane5",
                wikiUrl = "https://en.wikipedia.org/wiki/Ariane_5",
                height = 53.0,
                diameter = 5.4,
                maxStage = 2,
                massToLEO = 21000.0,
                massToGTO = 10400.0,
                liftoffMass = 780000.0,
                liftoffThrust = 13300.0,
                successfulLaunches = 81,
                maidenFlight = "1996-06-04",
                failedLaunches = 2
            )
        )
    )
)
