package cz.cvut.fel.dcgi.zan.zan_kuznetsova.di

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.datasource.LaunchDBDataSource
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.datasource.LaunchDBDataSourceImpl
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.LaunchDatabase
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.NewsDatabase
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.datasource.DBDataSource
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.datasource.NewsDBDataSourceImpl
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.repository.LaunchRepository
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel.LaunchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.News
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.repository.NewsRepository
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel.NewsViewModel


val appModule = module {

    // LaunchModule
    single<LaunchDatabase> {
        LaunchDatabase.getDatabase(context = get())
    }

    single<LaunchDBDataSource> {
        LaunchDBDataSourceImpl(launchDao = get<LaunchDatabase>().launchDao())
    }

    single<LaunchRepository> {
        LaunchRepository(launchDBDataSource = get())
    }

    viewModel<LaunchViewModel> {
        LaunchViewModel(repository = get())
    }

    // NewsModule
    single<NewsDatabase> {
        NewsDatabase.getDatabase(context = get())
    }

    single<DBDataSource<News, Int>> {
        NewsDBDataSourceImpl(newsDao = get<NewsDatabase>().newsDao())
    }

    single<NewsRepository> {
        NewsRepository(newsDBDataSource = get())
    }

    viewModel<NewsViewModel> {
        NewsViewModel(repository = get())
    }
}