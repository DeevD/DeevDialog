package com.example.heinhtet.deevddialog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.heinhtet.deevd.deevdialog.dialog.DeevDialog
import com.heinhtet.deevd.deevdialog.dialog.DeevDialogAnimation
import com.heinhtet.deevd.deevdialog.dialog.DeevDialogCallback
import com.heinhtet.deevd.deevdialog.dialog.DeevDialogStyle

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
        lateinit var mDialog: DeevDialog.Instance
//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDialog = DeevDialog.Instance



        progress_dialog.setOnClickListener {
            DeevDialog.
                    into(this@MainActivity, DeevDialogAnimation.FADE_ANIMATION).
                    setStyle(DeevDialogStyle.PROGRESS).setTitleText("Fatching...").
                    setMessage("My Loading...").setProgressLoadingColorRes(R.color.progress_color).make()
        }

        custom_dialog.setOnClickListener {
            mDialog.into(this@MainActivity, DeevDialogAnimation.PUSH_ANIMATION)
                    .setStyle(DeevDialogStyle.CUSTOM)
                    .setCustomView(R.layout.dialog_layout)
                    .setCustomViewCallback(object : DeevDialogCallback.CustomViewRenderingListener {
                        override fun onBind(dialog: DeevDialog.Instance.DeevDialog) {
                            var z = dialog.findViewById<TextView>(R.id.txt_dia)
                            z.text = "This is custom DeevDialog"
                            var ok = dialog.findViewById<Button>(R.id.btn_yes)
                            ok.setOnClickListener { Toast.makeText(this@MainActivity, "Click Ok", Toast.LENGTH_SHORT).show() }

                     }
                    }).make()
        }
        message_dialog.setOnClickListener {
            mDialog.into(this@MainActivity, DeevDialogAnimation.PULSE_ANIMATION).
                    setStyle(DeevDialogStyle.MESSAGE).
                    setOnPositiveListener(object : DeevDialogCallback.onPositiveClickListener {
                        override fun onClick(dialog: DeevDialog.Instance.DeevDialog) {
                            mDialog.release()
                            Toast.makeText(this@MainActivity, "adfadf", Toast.LENGTH_SHORT).show()
                        }
                    })
                    .setOnNegativeClickListener(object : DeevDialogCallback.onNegativeClickListener {
                        override fun onClick(dialog: DeevDialog.Instance.DeevDialog) {
                            mDialog.release()
                        }
                    })
                    .setMessage("This is DeevDialog!!")
                    .setPositiveTextColorRes(R.color.message_color)
                    .setNegativeTextColorRes(R.color.message_color)
                    .setMessageTextColorRes(R.color.message_color)
                    .setTitleColorRes(R.color.title_text_color)
                    .setTitleText("This is DeevDialog")
                    .setBackgroundColorRes(R.color.colorAccent)
                    .setPositiveText("click ok")
                    .setNegativeText("click cancel")
                    .setCancelableDeev(false).make()

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (mDialog.isShowing()){
            mDialog.release()
        }
    }
}
