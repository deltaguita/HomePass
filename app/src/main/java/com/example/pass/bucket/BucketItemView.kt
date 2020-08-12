package com.example.pass.bucket

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.example.pass.R
import kotlinx.android.synthetic.main.bucket_item_view.view.*

@ModelView(saveViewState = true, defaultLayout = R.layout.bucket_item_view, fullSpan = true)
class BucketItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    @TextProp
    fun setCount(charSequence: CharSequence?) {
        bucket_item_title.text = charSequence
    }

    @TextProp
    fun setDescription(charSequence: CharSequence?) {
        bucket_item_status.text = charSequence
    }

    @CallbackProp
    fun setItemClickListener(onClickListener: OnClickListener?) {
        setOnClickListener(onClickListener)
    }


    @CallbackProp
    fun setCheckoutBtnClick(onClickListener: OnClickListener?) {
        bucket_item_check_out_btn.setOnClickListener(onClickListener)
    }


}