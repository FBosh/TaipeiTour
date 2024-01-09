package taipei.travel.taipeitour.util

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

class Utils {
    companion object {
        fun getColor(context: Context, @ColorRes colorRes: Int) = ContextCompat.getColor(context, colorRes)

        fun setImageViewByGlide(
                context: Context,
                strURL: String,
                @DrawableRes placeholder: Int,
                imageview: ImageView
        ) {
            Glide.with(context)
                    .load(strURL)
                    .placeholder(placeholder)
                    .centerCrop()
                    .into(imageview)
                    .view
        }
    }
}

interface BoshLogs {
    fun printBoshLog(msg: String) = Log.i("Bosh_Tag", msg)

    fun printLog(msg: String) = Log.i(javaClass.simpleName, msg)
}
