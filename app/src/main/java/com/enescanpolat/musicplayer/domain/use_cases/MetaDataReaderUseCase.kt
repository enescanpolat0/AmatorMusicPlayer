package com.enescanpolat.musicplayer.domain.use_cases

import android.net.Uri
import com.enescanpolat.musicplayer.domain.repository.MetaDataReaderRepository
import javax.inject.Inject

class MetaDataReaderUseCase @Inject constructor(private val metaDataReader: MetaDataReaderRepository) {


    fun getMetaData(contentUri:Uri)=metaDataReader.getMetaDataFromUri(contentUri)

}