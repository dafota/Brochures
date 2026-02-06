package cab.bonial.brochures.data.api

import cab.bonial.brochures.data.model.ShelfResponse
import retrofit2.http.GET

interface BrochureApiService {
    @GET("shelf.json")
    suspend fun getBrochures(): ShelfResponse
}
