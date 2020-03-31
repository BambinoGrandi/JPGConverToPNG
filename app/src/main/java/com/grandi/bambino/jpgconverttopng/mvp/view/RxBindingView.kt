package com.grandi.bambino.jpgconverttopng.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RxBindingView : MvpView {

    fun init()
    fun setText(text: CharSequence)
}