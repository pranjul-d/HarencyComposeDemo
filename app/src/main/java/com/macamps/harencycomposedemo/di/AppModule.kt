package com.macamps.harencycomposedemo.di

import android.content.Context
import com.macamps.harencycomposedemo.HarencyApplication
import com.macamps.harencycomposedemo.data.network.ApiService
import com.macamps.harencycomposedemo.repositories.UserRepository
import com.macamps.harencycomposedemo.ui.login.repo.LoginRepository
import com.softradix.jetpackcomposedemo.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): HarencyApplication {
        return app as HarencyApplication
    }


    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao) = UserRepository(userDao)

    @Singleton
    @Provides
    fun provideLoginRepository(api: ApiService) = LoginRepository(api = api)

}