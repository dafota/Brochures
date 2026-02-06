package cab.bonial.brochures.data.api

import retrofit2.http.GET

interface BrochureApiService {
    @GET("shelf.json")
    suspend fun getBrochures(): List<String>
}
