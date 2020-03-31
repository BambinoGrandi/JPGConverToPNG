package com.grandi.bambino.jpgconverttopng.mvp.presenter


import com.grandi.bambino.jpgconverttopng.BackButtonListener
import com.grandi.bambino.jpgconverttopng.mvp.view.RxBindingView
import io.reactivex.Scheduler

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

@InjectViewState
class RxBindingPresenter(val router: Router) :
    MvpPresenter<RxBindingView>(), BackButtonListener {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }


    fun textChanges(text: CharSequence) {
        viewState.setText(text)
    }

    override fun onBackClicked(): Boolean {
        router.exit()
        return true
    }

}