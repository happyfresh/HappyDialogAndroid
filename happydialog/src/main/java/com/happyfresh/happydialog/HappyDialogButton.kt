package com.happyfresh.happydialog

import android.graphics.drawable.Drawable
import android.view.View

/**
 * Created by adefruandta on 2/24/18.
 */
open class HappyDialogButton {

    interface Type {
        val id: Int

        val background: Drawable

        val textColor: Int
    }

    interface OnClickListener {

        fun onClick(view: View, button: HappyDialogButton, dialog: HappyDialogInterface)
    }

    open lateinit var type: Type

    open lateinit var text: String

    open lateinit var listener: OnClickListener
}