package taipei.travel.taipeitour.model.data

data class APIResponse<T>(
        val total: Int,
        val data: T
)
