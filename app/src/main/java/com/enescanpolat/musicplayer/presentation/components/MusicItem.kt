package com.enescanpolat.musicplayer.presentation.components

import android.net.Uri
import androidx.media3.common.MediaItem

data class MusicItem(
    val contentUri:Uri,
    val mediaItem:MediaItem,
    val name:String
)