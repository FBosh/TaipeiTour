package taipei.travel.taipeitour

class Constants {
    companion object {
        const val BASE_URL = "www.travel.taipei/open-api"
    }
}

enum class FragTransType {
    ADD,
    REPLACE,
    REMOVE,
    ATTACH,
    DETACH,
    HIDE,
    SHOW
}

enum class Language(val title: String, val description: String) {
    ZH_TW("臺北旅遊", "繁體中文"),
    ZH_CN("台北旅游", "简体中文"),
    EN("Taipei travel", "English"),
    JA("台北旅行", "日本語"),
    KO("타이페이 여행", "한국어"),
    ES("viajes a taipei", "Español"),
    ID("perjalanan Taipei", "Bahasa Indonesia"),
    TH("เที่ยวไทเป", "ไทย"),
    VI("du lịch Đài Bắc", "Tiếng Việt");

    companion object {
        fun getByDescriptionSafely(strValue: String): Language {
            return entries.find { it.description == strValue } ?: ZH_TW
        }
    }

    override fun toString() = name.lowercase().replace('_', '-')
}
