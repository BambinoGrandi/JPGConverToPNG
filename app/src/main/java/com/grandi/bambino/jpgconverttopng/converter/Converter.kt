package com.grandi.bambino.jpgconverttopng.converter

import com.grandi.bambino.jpgconverttopng.mvp.model.Image
import io.reactivex.rxjava3.core.Completable


interface Converter {

    fun convert(image: Image): Completable
}
