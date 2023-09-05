package com.enescanpolat.musicplayer.presentation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.enescanpolat.musicplayer.domain.use_cases.MetaDataReaderUseCase
import com.enescanpolat.musicplayer.presentation.components.MusicItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val metaDataReaderUseCase: MetaDataReaderUseCase,
    val player : Player,
    private val savedStateHandle: SavedStateHandle,
):ViewModel() {

    private val musicUris = savedStateHandle.getStateFlow("musicUris", emptyList<Uri>())

    val musicItems = musicUris.map {uris->
        uris.map {uri->
            MusicItem(
                contentUri = uri,
                mediaItem = MediaItem.fromUri(uri),
                name=metaDataReaderUseCase.getMetaData(uri)?.fileName ?:"no name"
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        player.prepare()
    }

    fun addMusic(uri: Uri){
        savedStateHandle["musicUris"]=musicUris.value + uri
        player.addMediaItem(MediaItem.fromUri(uri))
    }

    fun playMusic(uri: Uri){
        player.setMediaItem(
            musicItems.value.find{ it.contentUri== uri }?.mediaItem ?:return
        )
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }

}