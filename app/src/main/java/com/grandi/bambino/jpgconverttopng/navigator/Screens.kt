package com.grandi.bambino.jpgconverttopng.navigator

import com.grandi.bambino.jpgconverttopng.ui.fragments.JPGConvertToPngFragment
import com.grandi.bambino.jpgconverttopng.ui.fragments.RxBindingFragment
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens : Screen() {

    class RxBindingScreen : SupportAppScreen(){
        override fun getFragment() = RxBindingFragment.newInstance()
    }

    class JPGConvertToPNGScreen : SupportAppScreen(){
        override fun getFragment() = JPGConvertToPngFragment.newInstance()
    }
}