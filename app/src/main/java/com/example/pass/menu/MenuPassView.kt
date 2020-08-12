package com.example.pass.menu

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.example.pass.R
import kotlinx.android.synthetic.main.menu_pass_view.view.*

@ModelView(saveViewState = true, defaultLayout = R.layout.menu_pass_view, fullSpan = true)
class MenuPassView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    @TextProp
    fun setName(charSequence: CharSequence?) {
        name_textView.text = charSequence
    }

    @TextProp
    fun setDescription(charSequence: CharSequence?) {
        description_textView.text = charSequence
    }

    @CallbackProp
    fun setClick(onClickListener: OnClickListener?) {
        add_btn.setOnClickListener(onClickListener)
    }



}