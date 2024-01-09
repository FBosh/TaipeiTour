package taipei.travel.taipeitour

class Constants {
    companion object {
        const val BASE_URL = "www.travel.taipei/open-api"
    }
}

enum class Language(val localeName: String, val title: String) {
    ZH_TW("繁體中文", "臺北旅遊"),
    ZH_CN("简体中文", "台北旅游"),
    EN("English", "Taipei travel"),
    JA("日本語", "台北旅行"),
    KO("한국어", "타이페이 여행"),
    ES("Español", "viajes a taipei"),
    ID("Bahasa Indonesia", "perjalanan Taipei"),
    TH("ไทย", "เที่ยวไทเป"),
    VI("Tiếng Việt", "du lịch Đài Bắc");

    companion object {
        fun getOrDefault(strLocaleName: String): Language {
            return entries.find { it.localeName == strLocaleName } ?: ZH_TW
        }
    }

    fun toURL() = name.lowercase().replace('_', '-')
}
