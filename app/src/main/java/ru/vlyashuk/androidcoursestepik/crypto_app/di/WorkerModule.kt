package ru.vlyashuk.androidcoursestepik.crypto_app.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.vlyashuk.androidcoursestepik.crypto_app.data.workers.ChildWorkerFactory
import ru.vlyashuk.androidcoursestepik.crypto_app.data.workers.RefreshDataWorker

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory(worker: RefreshDataWorker.Factory): ChildWorkerFactory
}