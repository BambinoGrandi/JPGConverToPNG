package com.grandi.bambino.jpgconverttopng.ui.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grandi.bambino.jpgconverttopng.App
import com.grandi.bambino.jpgconverttopng.BackButtonListener
import com.grandi.bambino.jpgconverttopng.R
import com.grandi.bambino.jpgconverttopng.mvp.presenter.RxBindingPresenter
import com.grandi.bambino.jpgconverttopng.mvp.view.RxBindingView
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_rx_binding.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 */
class RxBindingFragment() : MvpAppCompatFragment(), RxBindingView {

    companion object {
        fun newInstance() =
            RxBindingFragment()
    }

    @InjectPresenter
    lateinit var presenter: RxBindingPresenter

    @ProvidePresenter
    fun providerPresenter() = RxBindingPresenter(App.instance.getRouter())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.fragment_rx_binding, null)

    @SuppressLint("CheckResult")
    override fun init() {
        edit_text.textChanges()
            .subscribe(Consumer {
                presenter.textChanges(it)

            })
    }

    override fun setText(text: CharSequence) {
        text_view.text = text
    }
}
