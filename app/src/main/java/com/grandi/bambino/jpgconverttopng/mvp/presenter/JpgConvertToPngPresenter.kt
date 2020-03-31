package com.grandi.bambino.jpgconverttopng.mvp.presenter

import com.grandi.bambino.jpgconverttopng.BackButtonListener
import com.grandi.bambino.jpgconverttopng.converter.Converter
import com.grandi.bambino.jpgconverttopng.mvp.model.Image
import com.grandi.bambino.jpgconverttopng.mvp.view.JPGConvertToPngView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class JpgConvertToPngPresenter(val converter: Converter, val router: Router, val mainThread: Scheduler) : MvpPresenter<JPGConvertToPngView>(), BackButtonListener {

        var convertDisposable: Disposable? = null

        override fun onFirstViewAttach() {
            super.onFirstViewAttach()
            viewState.init()
        }

        override fun onBackClicked(): Boolean {
            router.exit()
            return true
        }

        fun cancelButton() {
            convertDisposable?.dispose()
            viewState.hideProgress()
            viewState.showCancelMessage()
        }

        fun openGalleryClick() {
            viewState.openGallery()
        }


        fun imageConvert(image: Image) {
            viewState.showProgress()
            convertDisposable = converter.convert(image)
                .observeOn(mainThread)
                .subscribe({
                    viewState.hideProgress()
                    viewState.showSuccessMessage()},
                    {
                        viewState.hideProgress()
                        viewState.showErrorMessage()
                    })

        }
    }
