package com.happyfresh.happydialog

/**
 * Created by adefruandta on 2/26/18.
 */
interface HappyDialogInterface {

    fun dismiss()

    fun cancel()

    interface OnCancelListener {

        fun onCancel(dialog: HappyDialogInterface)
    }

    interface OnDismissListener {

        fun onDismiss(dialog: HappyDialogInterface)
    }
}