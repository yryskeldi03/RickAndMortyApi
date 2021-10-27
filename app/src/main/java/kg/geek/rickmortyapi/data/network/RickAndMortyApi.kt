package kg.geek.rickmortyapi.data.network

import kg.geek.rickmortyapi.data.models.Character
import kg.geek.rickmortyapi.data.models.Episode
import kg.geek.rickmortyapi.data.models.Location
import kg.geek.rickmortyapi.data.models.MainResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Response<MainResult<Character>>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): Response<Character>

    @GET("character")
    suspend fun getCharacterByStatus(
        @Query("status") status: String,
        @Query("page") page: Int
    ): Response<MainResult<Character>>

    @GET("character")
    suspend fun getCharacterByGender(
        @Query("gender") gender: String,
        @Query("page") page: Int
    ): Response<MainResult<Character>>


    @GET("character")
    suspend fun getCharacterByStatusAndGender(
        @Query("status") status: String,
        @Query("gender") gender: String,
        @Query("page") page: Int
    ): Response<MainResult<Character>>

    @GET("character/")
    suspend fun getFilteredCharacters(
        @Query("name") name: String
    ): Response<MainResult<Character>>

    @GET("location")
    suspend fun getLocations(
        @Query("page") page: Int
    ): Response<MainResult<Location>>

    @GET("location/{id}")
    suspend fun getLocationById(
        @Path("id") page: Int
    ): Response<Location>

    @GET("location/")
    suspend fun getFilteredLocations(
        @Query("name") name: String
    ): Response<MainResult<Character>>

    @GET("episode")
    suspend fun getEpisodes(
        @Query("page") page: Int
    ): Response<MainResult<Episode>>

    @GET("episode/{id}")
    suspend fun getEpisodeById(
        @Path("id") id: Int
    ): Response<Episode>

    @GET("episode/")
    suspend fun getFilteredEpisodes(
        @Query("name") name: String
    ): Response<MainResult<Character>>

}