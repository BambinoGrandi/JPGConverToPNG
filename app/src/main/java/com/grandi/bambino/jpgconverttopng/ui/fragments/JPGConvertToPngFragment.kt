package com.grandi.bambino.jpgconverttopng.ui.fragments


import android.app.Activity
import android.app.AlertDialog

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.grandi.bambino.jpgconverttopng.App
import com.grandi.bambino.jpgconverttopng.R
import com.grandi.bambino.jpgconverttopng.converter.AndroidConverter
import com.grandi.bambino.jpgconverttopng.mvp.model.Image
import com.grandi.bambino.jpgconverttopng.mvp.presenter.JpgConvertToPngPresenter
import com.grandi.bambino.jpgconverttopng.mvp.view.JPGConvertToPngView
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_jpgconvert_to_png.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import timber.log.Timber


class JPGConvertToPngFragment : MvpAppCompatFragment(), JPGConvertToPngView {

    companion object {
        private const val GALLERY_REQUEST = 1
        fun newInstance() = JPGConvertToPngFragment()
    }

    @InjectPresenter
    lateinit var presenter: JpgConvertToPngPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.fragment_jpgconvert_to_png, null)

    @ProvidePresenter
    fun providerPresenter() = JpgConvertToPngPresenter(
        AndroidConverter(context!!),
        App.instance.getRouter(),
        AndroidSchedulers.mainThread()
    )


    override fun init() {
        btn_convert.clicks()
            .subscribe {
                presenter.openGalleryClick()
            }
    }

    //Открываем галлерею
    override fun openGallery() {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GALLERY_REQUEST) {
            if (requestCode == Activity.RESULT_OK) {
                data?.data?.let { uri ->
                    val byteArray = context?.contentResolver?.openInputStream(uri)?.buffered()?.use { it.readBytes() }
                    byteArray?.let {
                        presenter.imageConvert(Image(byteArray))
                    }
                }
            }
        }
    }

    var progressDialog: Dialog? = null

    //Отоброжаем диалог с кнопкой cancel
    override fun showProgress() {
        progressDialog = AlertDialog.Builder(context)
            .setMessage(R.string.process_in_convert)
            .setNegativeButton(R.string.cancel) {dialog, which -> presenter.cancelButton() }
            .create()
        Timber.d("Show progress")
    }

    //Прячем диалог
    override fun hideProgress() {
        progressDialog?.dismiss()
    }

    override fun showSuccessMessage() {
        Toast.makeText(context, R.string.success_convert, Toast.LENGTH_LONG).show()
    }

    override fun showErrorMessage() {
        Toast.makeText(context, R.string.error_convert, Toast.LENGTH_LONG).show()
    }

    override fun showCancelMessage() {
        Toast.makeText(context, R.string.cancel_convert, Toast.LENGTH_LONG).show()
    }
}
