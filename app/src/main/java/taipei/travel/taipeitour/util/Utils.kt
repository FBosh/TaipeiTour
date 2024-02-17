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

enum class SelectedLanguage(val localeName: String, val title: String, val titleNews: String, val titleAttractions: String) {
    ZH_TW("繁體中文", "臺北旅遊", "最新消息", "觀光景點"),
    ZH_CN("简体中文", "台北旅游", "最新消息", "观光景点"),
    EN("English", "Taipei travel", "News", "Attractions"),
    JA("日本語", "台北旅行", "ニュース", "アトラクション"),
    KO("한국어", "타이페이 여행", "소식", "관광명소"),
    ES("Español", "viajes a taipei", "Noticias", "Atracciones"),
    ID("Bahasa Indonesia", "perjalanan Taipei", "Berita", "Atraksi"),
    TH("ไทย", "เที่ยวไทเป", "ข่าว", "สถานที่ท่องเที่ยว"),
    VI("Tiếng Việt", "du lịch Đài Bắc", "Tin tức", "Điểm tham quan");

    companion object {
        fun getOrDefault(strLocaleName: String): SelectedLanguage {
            return entries.find { it.localeName == strLocaleName } ?: ZH_TW
        }
    }

    fun toURL() = name.lowercase().replace('_', '-')
}

interface BoshLogs {
    fun printBoshLog(msg: String) = Log.i("Bosh_Tag", msg)

    fun printLog(msg: String) = Log.i(javaClass.simpleName, msg)
}

enum class APIType {
    NEWS, ATTRACTION
}
