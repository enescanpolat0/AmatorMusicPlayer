package com.enescanpolat.musicplayer.data.dependencyinjection

import android.app.Application
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.enescanpolat.musicplayer.data.repository.MetaDataReaderRepositoryImpl
import com.enescanpolat.musicplayer.domain.repository.MetaDataReaderRepository
import com.enescanpolat.musicplayer.domain.use_cases.MetaDataReaderUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object appModule {


    @Singleton
    @Provides
    fun injectMusicPlayerApp(app:Application):Player{
        return ExoPlayer.Builder(app)
            .build()
    }

    @Singleton
    @Provides
    fun injectMetaDataReader(app: Application):MetaDataReaderRepository{
        return MetaDataReaderRepositoryImpl(app)
    }

    @Singleton
    @Provides
    fun injectMetaDataReaderUseCase(metaDataReaderRepository: MetaDataReaderRepository):MetaDataReaderUseCase{
        return MetaDataReaderUseCase(metaDataReaderRepository)
    }

}