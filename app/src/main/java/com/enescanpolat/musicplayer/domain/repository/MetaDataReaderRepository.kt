package com.enescanpolat.musicplayer.domain.repository

import android.net.Uri
import com.enescanpolat.musicplayer.domain.model.MetaData

interface MetaDataReaderRepository {
    fun getMetaDataFromUri(contentUri: Uri):MetaData?
}