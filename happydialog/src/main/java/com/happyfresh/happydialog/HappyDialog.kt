package com.happyfresh.happydialog

import android.app.Dialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.*

/**
 * Created by adefruandta on 2/23/18.
 */
open class HappyDialog(protected val builder: HappyDialogBuilder) : Dialog(builder.context), HappyDialogInterface {

    protected open val layoutResId: Int = R.layout.happy_dialog

    protected open val windowFeatureId: Int = Window.FEATURE_NO_TITLE

    protected open val ivFeature: ImageView by bindView(R.id.iv_feature)

    protected open val tvTitle: TextView by bindView(R.id.tv_title)

    protected open val tvMessage: TextView by bindView(R.id.tv_message)

    protected open val vCustom: FrameLayout by bindView(R.id.v_custom)

    protected open val vButtonContainer: LinearLayout by bindView(R.id.v_button_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // prerequisite
        requestWindowFeature(windowFeatureId)
        setContentView(layoutResId)

        // set content
        setFeatureImage(builder.featureImage)
        setTitle(builder.title)
        setMessage(builder.message)
        setCustomView(builder.customView)
        setButtons(builder.buttons)

        // set listener
        setCancelable(builder.cancelable)
        setOnCancelListener {
            builder.onCancelListeners.forEach {
                it.onCancel(this)
            }
        }
        setOnDismissListener {
            builder.onDismissListeners.forEach {
                it.onDismiss(this)
            }
        }
    }

    override fun setTitle(titleId: Int) {
        setContentView(tvTitle, titleId, {
            tvTitle.setText(it)
        })
    }

    override fun setTitle(title: CharSequence?) {
        setContentView(tvTitle, title, {
            tvTitle.text = it
        })
    }

    open fun setFeatureImage(imageId: Int) {
        setFeatureImage(ContextCompat.getDrawable(context, imageId))
    }

    open fun setFeatureImage(image: Drawable?) {
        setContentView(ivFeature, image, {
            ivFeature.setImageDrawable(it)
        })
    }

    open fun setMessage(messageId: Int) {
        setContentView(tvMessage, messageId, {
            tvMessage.setText(it)
        })
    }

    open fun setMessage(message: CharSequence?) {
        setContentView(tvMessage, message, {
            tvMessage.text = it
        })
    }

    open fun setCustomView(viewId: Int) {
        setCustomView(LayoutInflater.from(context).inflate(viewId, null))
    }

    open fun setCustomView(view: View?) {
        setContentView(vCustom, view, {
            vCustom.removeAllViews()
            vCustom.addView(it)
        })
    }

    open fun setButtons(buttons: List<HappyDialogButton>?) {
        setContentView(vButtonContainer, buttons, {
            vButtonContainer.removeAllViews()
            it?.forEach { button ->
                val view = Button(context)
                view.text = button.text
                view.background = button.type.background
                view.setTextColor(button.type.textColor)
                view.setOnClickListener {
                    button.listener.onClick(view, button, this)
                }

                vButtonContainer.addView(view, buttonParams())
            }
        })
    }

    open fun <T : Any?> setContentView(view: View, content: T, listener: (T) -> Unit) {
        if (content == null) {
            view.visibility = View.GONE
        } else {
            listener(content)
            view.visibility = View.VISIBLE
        }
    }

    open fun buttonParams(): LinearLayout.LayoutParams {
        val margin = dpToPxByScreenDensity(8)
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.bottomMargin = margin
        return layoutParams
    }

    open fun dpToPxByScreenDensity(dp: Int): Int {
        val displayMetrics = context.resources.displayMetrics
        return Math.round(dp * displayMetrics.density)
    }
}