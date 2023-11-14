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

enum class Language {
    ZH_TW, ZH_CN, EN, JA, KO, ES, ID, TH, VI;

    companion object {
        fun getSafety(strValue: String): Language {
            return entries.find { it.toString() == strValue.lowercase() } ?: ZH_TW
        }
    }

    override fun toString() = name.lowercase().replace('_', '-')
}
