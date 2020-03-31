package com.grandi.bambino.jpgconverttopng.converter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.grandi.bambino.jpgconverttopng.mvp.model.Image
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream


class AndroidConverter(val context: Context) : Converter{

    override fun convert(image: Image) = Completable.fromAction{
        context?.let {
            try {
                Thread.sleep(2500)
            }catch (e: InterruptedException){
                return@fromAction
            }
            //Создаем bitmap
            val bitmap = BitmapFactory.decodeByteArray(image.data, 0, image.data.size)
            val pathSave = File(context.getExternalFilesDir(null), "convert.png")
            val inputStream = FileOutputStream(pathSave)

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, inputStream)
            bitmap.recycle()
            inputStream.close()
        }

    }.subscribeOn(Schedulers.io())

}