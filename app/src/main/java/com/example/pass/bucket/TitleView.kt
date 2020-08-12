package com.example.pass.bucket

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.example.pass.R
import kotlinx.android.synthetic.main.menu_pass_view.view.*

@ModelView(saveViewState = true, defaultLayout = R.layout.bucket_title_view, fullSpan = true)
class TitleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    @TextProp
    fun setTitle(charSequence: CharSequence?) {
        this.text = charSequence
    }

}