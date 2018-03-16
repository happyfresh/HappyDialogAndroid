package com.happyfresh.happydialog

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View

/**
 * Created by adefruandta on 2/24/18.
 */
open class HappyDialogBuilder(val context: Context) {

    open var featureImage: Drawable? = null

    open var title: String? = null

    open var message: String? = null

    open var customView: View? = null

    open var cancelable: Boolean = true

    open var onCancelListeners: MutableList<HappyDialogInterface.OnCancelListener> = ArrayList()

    open var onDismissListeners: MutableList<HappyDialogInterface.OnDismissListener> = ArrayList()

    open var buttons: MutableList<HappyDialogButton> = ArrayList()

    open fun setFeatureImage(imageId: Int) {
        featureImage = ContextCompat.getDrawable(context, imageId)
    }

    open fun setTitle(titleId: Int) {
        title = getString(titleId)
    }

    open fun setMessage(messageId: Int) {
        message = getString(messageId)
    }

    open fun setCustomView(viewId: Int) {
        customView = LayoutInflater.from(context).inflate(viewId, null)
    }

    open fun addButton(button: HappyDialogButton) {
        buttons.add(button)
    }

    open fun addButton(type: HappyDialogButton.Type, text: String, listener: HappyDialogButton.OnClickListener) {
        addButton(HappyDialogButton().also {
            it.type = type
            it.text = text
            it.listener = listener
        })
    }

    open fun addButton(type: HappyDialogButton.Type, textId: Int, listener: HappyDialogButton.OnClickListener) {
        addButton(type, getString(textId), listener)
    }

    open fun addOnCancelListener(listener: (HappyDialogInterface) -> Unit) {
        addOnCancelListener(object : HappyDialogInterface.OnCancelListener {
            override fun onCancel(dialog: HappyDialogInterface) {
                listener(dialog)
            }
        })
    }

    open fun addOnCancelListener(listener: HappyDialogInterface.OnCancelListener) {
        if (onCancelListeners.contains(listener)) {
            return
        }

        onCancelListeners.add(listener)
    }

    open fun addOnDismissListener(listener: (HappyDialogInterface) -> Unit) {
        addOnDismissListener(object : HappyDialogInterface.OnDismissListener {
            override fun onDismiss(dialog: HappyDialogInterface) {
                listener(dialog)
            }
        })
    }

    open fun addOnDismissListener(listener: HappyDialogInterface.OnDismissListener) {
        if (onDismissListeners.contains(listener)) {
            return
        }

        onDismissListeners.add(listener)
    }

    open fun getString(resId: Int): String {
        return context.getString(resId)
    }

    open fun create(): HappyDialog {
        return HappyDialog(this)
    }

    open fun show(): HappyDialog {
        val dialog = create()
        dialog.show()
        return dialog
    }
}