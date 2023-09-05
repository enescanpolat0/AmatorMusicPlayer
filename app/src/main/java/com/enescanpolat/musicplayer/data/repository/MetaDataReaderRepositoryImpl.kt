package com.enescanpolat.musicplayer.data.repository

import android.app.Application
import android.net.Uri
import android.provider.MediaStore
import com.enescanpolat.musicplayer.domain.model.MetaData
import com.enescanpolat.musicplayer.domain.repository.MetaDataReaderRepository
import javax.inject.Inject

class MetaDataReaderRepositoryImpl @Inject constructor(private val app:Application):MetaDataReaderRepository {
    override fun getMetaDataFromUri(contentUri: Uri):MetaData? {

        if(contentUri.scheme!="content"){
            return null
        }
        val fileName=app.contentResolver
            .query(
                contentUri,
                arrayOf(MediaStore.Audio.AudioColumns.DISPLAY_NAME),
                null,
                null,
                null
            )
            ?.use {cursor->
                val index = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DISPLAY_NAME)
                cursor.moveToFirst()
                cursor.getString(index)
            }
        return fileName?.let {fullFileName->
            MetaData(
                fileName=Uri.parse(fullFileName).lastPathSegment ?:return null
            )

        }

    }
}