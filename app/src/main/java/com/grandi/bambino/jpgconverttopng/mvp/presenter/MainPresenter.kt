package com.grandi.bambino.jpgconverttopng.mvp.presenter

import com.grandi.bambino.jpgconverttopng.mvp.view.MainView
import com.grandi.bambino.jpgconverttopng.navigator.Screens
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

@InjectViewState
class MainPresenter(val router: Router) : MvpPresenter<MainView>(){

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(){
        router.exit()
    }

    fun openFirstTask(){
        router.replaceScreen(Screens.RxBindingScreen())
    }

    fun openSecondTask(){
        router.replaceScreen(Screens.JPGConvertToPNGScreen())
    }

}