package cz.cvut.fel.dcgi.zan.zan_kuznetsova.di

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.datasource.LaunchDBDataSource
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.datasource.LaunchDBDataSourceImpl
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.LaunchDatabase
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.repository.LaunchRepository
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel.LaunchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single<LaunchDatabase> {
        LaunchDatabase.getDatabase(context = get())
    }

    single<LaunchDBDataSource> {
        LaunchDBDataSourceImpl(launchDao = get<LaunchDatabase>().launchDao())
//        LaunchDBDataSourceFake()
    }

    single<LaunchRepository> {
        LaunchRepository(launchDBDataSource = get())
    }

    viewModel<LaunchViewModel>{
        LaunchViewModel(repository = get())
    }
}