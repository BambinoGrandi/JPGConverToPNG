package com.grandi.bambino.jpgconverttopng.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.grandi.bambino.jpgconverttopng.App
import com.grandi.bambino.jpgconverttopng.BackButtonListener
import com.grandi.bambino.jpgconverttopng.R
import com.grandi.bambino.jpgconverttopng.mvp.presenter.MainPresenter
import com.grandi.bambino.jpgconverttopng.mvp.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_jpgconvert_to_png.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(), MainView {



    val navigator =  SupportAppNavigator(this, R.id.container)

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providerPresenter() = MainPresenter(App.instance.getRouter())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_open_first_task.setOnClickListener {
            presenter.openFirstTask()
        }

        btn_open_second_task.setOnClickListener {
            presenter.openSecondTask()
        }

    }

    override fun init() {

    }


    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.getNavigatorHolder().removeNavigator()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.fragments.forEach{
            if (it is BackButtonListener && it.onBackClicked()){
                return
            }
            presenter.backPressed()

        }
    }



}
