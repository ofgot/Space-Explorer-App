package cz.cvut.fel.dcgi.zan.zan_kuznetsova.di

import SettingsViewModel
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.components.PreferencesManager
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.datasource.LaunchDBDataSourceImpl
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.LaunchDatabase
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.NewsDatabase
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.datasource.DBDataSource
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.datasource.NewsDBDataSourceImpl
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.Launch
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.repository.LaunchRepository
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel.LaunchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.News
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.api.LaunchLibraryApi
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.api.NewsLibraryApi
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.source.LaunchApiDataSource
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.source.NewsApiDataSource
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.repository.NewsRepository
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel.NewsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val appModule = module {

    // LaunchModule
    single<LaunchDatabase> {
        LaunchDatabase.getDatabase(context = get())
    }

    single<DBDataSource<Launch, String>>(named("launch")) {
        LaunchDBDataSourceImpl(launchDao = get<LaunchDatabase>().launchDao())
    }

    single<LaunchRepository> {
        LaunchRepository(launchDBDataSource = get(named("launch")))
    }

    viewModel<LaunchViewModel> {
        LaunchViewModel(repository = get(), remoteDataSource = get(), preferences = get())
    }

    // NewsModule
    single<NewsDatabase> {
        NewsDatabase.getDatabase(context = get())
    }

    single<DBDataSource<News, Int>>(named("news")) {
        NewsDBDataSourceImpl(newsDao = get<NewsDatabase>().newsDao())
    }

    single<NewsRepository> {
        NewsRepository(newsDBDataSource = get(named("news")))
    }

    viewModel<NewsViewModel> {
        NewsViewModel(
            repository = get(),
            remoteDataSource = get(),
            preferences = get()
        )
    }

    // Network

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    // Launch ///
    single(named("launchRetrofit")) {
        Retrofit.Builder()
            .baseUrl("https://ll.thespacedevs.com/2.2.0/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        get<Retrofit>(named("launchRetrofit")).create(LaunchLibraryApi::class.java)
    }

    single<LaunchApiDataSource> {
        LaunchApiDataSource(get())
    }

    ////////////

    /// News ///
    single(named("newsRetrofit")) {
        Retrofit.Builder()
            .baseUrl("https://api.spaceflightnewsapi.net/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        get<Retrofit>(named("newsRetrofit")).create(NewsLibraryApi::class.java)
    }

    single<NewsApiDataSource> {
        NewsApiDataSource(get())
    }

    ////////////

    single { PreferencesManager(get()) }


    /// Settings ///
    viewModel<SettingsViewModel> {
        SettingsViewModel(preferencesManager = get())
    }
}