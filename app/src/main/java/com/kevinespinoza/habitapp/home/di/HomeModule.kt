package com.kevinespinoza.habitapp.home.di

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.kevinespinoza.habitapp.home.data.alarm.AlarmHandlerImpl
import com.kevinespinoza.habitapp.home.data.local.HomeDao
import com.kevinespinoza.habitapp.home.data.local.HomeDatabase
import com.kevinespinoza.habitapp.home.data.local.typeconverter.HomeTypeConverter
import com.kevinespinoza.habitapp.home.data.remote.HomeApi
import com.kevinespinoza.habitapp.home.data.repository.HomeRepositoryImpl
import com.kevinespinoza.habitapp.home.domain.alarm.AlarmHandler
import com.kevinespinoza.habitapp.home.domain.detail.usecase.DetailUsesCases
import com.kevinespinoza.habitapp.home.domain.detail.usecase.GetHabitByIdUseCase
import com.kevinespinoza.habitapp.home.domain.detail.usecase.InsertHabitUseCase
import com.kevinespinoza.habitapp.home.domain.home.usecase.CompleteHabitUseCase
import com.kevinespinoza.habitapp.home.domain.home.usecase.GetHabitsForDateUseCase
import com.kevinespinoza.habitapp.home.domain.home.usecase.HomeUseCases
import com.kevinespinoza.habitapp.home.domain.home.usecase.SyncHabitUseCase
import com.kevinespinoza.habitapp.home.domain.repository.HomeRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun provideHomeUseCases(repository: HomeRepository): HomeUseCases {
        return HomeUseCases(
            completeHabitUseCase = CompleteHabitUseCase(repository),
            getHabitsForDateUseCase = GetHabitsForDateUseCase(repository),
            syncHabitUseCase = SyncHabitUseCase(repository),
        )
    }

    @Singleton
    @Provides
    fun provideDetailUseCases(repository: HomeRepository): DetailUsesCases {
        return DetailUsesCases(
            insertHabitUseCase = InsertHabitUseCase(repository),
            getHabitByIdUseCase = GetHabitByIdUseCase(repository),
        )
    }

    @Singleton
    @Provides
    fun provideHabitDao(@ApplicationContext context: Context): HomeDao {
        return Room.databaseBuilder(
            context,
            HomeDatabase::class.java,
            "habit_db"
        ).addTypeConverter(HomeTypeConverter()).build().dao
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()
    }

    @Singleton
    @Provides
    fun provideHomeApi(client: OkHttpClient): HomeApi {
        return Retrofit.Builder().baseUrl(HomeApi.BASE_URL).client(client).addConverterFactory(MoshiConverterFactory.create()).build().create(HomeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(dao: HomeDao, api: HomeApi, alarmHandler: AlarmHandler, workManager: WorkManager): HomeRepository {
        return HomeRepositoryImpl(dao, api, alarmHandler, workManager)
    }

    @Singleton
    @Provides
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager{
        return WorkManager.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideAlarmHandler(@ApplicationContext context: Context): AlarmHandler {
        return AlarmHandlerImpl(context)
    }

}