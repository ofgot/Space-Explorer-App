package cz.cvut.fel.dcgi.zan.zan_kuznetsova.di

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
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.repository.NewsRepository
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel.NewsViewModel
import org.koin.core.qualifier.named


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
        LaunchViewModel(repository = get())
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
        NewsViewModel(repository = get())
    }
}