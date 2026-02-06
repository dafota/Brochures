package cab.bonial.brochures.data.api

import cab.bonial.brochures.data.model.ShelfResponse
import retrofit2.http.GET

interface ShelfApiService {
    @GET("shelf.json")
    suspend fun getShelf(): ShelfResponse
}
