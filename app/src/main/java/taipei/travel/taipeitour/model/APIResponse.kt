package taipei.travel.taipeitour.model

data class APIResponse<T>(
        val total: Int,
        val data: T
)
