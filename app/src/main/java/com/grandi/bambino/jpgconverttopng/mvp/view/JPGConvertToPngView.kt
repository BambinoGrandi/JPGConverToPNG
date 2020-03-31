package com.grandi.bambino.jpgconverttopng.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface JPGConvertToPngView : MvpView {
    fun init()

    fun openGallery()

    fun showProgress()
    fun hideProgress()
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showSuccessMessage()
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showErrorMessage()
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showCancelMessage()


}