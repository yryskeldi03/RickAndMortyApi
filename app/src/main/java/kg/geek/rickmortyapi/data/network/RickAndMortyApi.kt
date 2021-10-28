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

    // CharacterFragment
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Response<MainResult<Character>>

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

    // DetailsFragment
    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): Response<Character>

    @GET("episode/{id}")
    suspend fun getEpisodeById(
        @Path("id") id: Int
    ): Response<Episode>

    @GET("location/{id}")
    suspend fun getLocationById(
        @Path("id") page: Int
    ): Response<Location>

    // LocationsFragment
    @GET("location")
    suspend fun getLocations(
        @Query("page") page: Int
    ): Response<MainResult<Location>>

    @GET("location/")
    suspend fun getLocationsByNameLocFragment(
        @Query("name") name: String
    ): Response<MainResult<Location>>

    // EpisodeFragment
    @GET("episode")
    suspend fun getEpisodes(
        @Query("page") page: Int
    ): Response<MainResult<Episode>>

    @GET("episode/")
    suspend fun getEpisodesByNameEpFragment(
        @Query("name") name: String
    ): Response<MainResult<Episode>>

    // SearchFragment
    @GET("episode/")
    suspend fun getEpisodesByName(
        @Query("name") name: String
    ): Response<MainResult<Character>>

    @GET("location/")
    suspend fun getLocationsByName(
        @Query("name") name: String
    ): Response<MainResult<Character>>

    @GET("character/")
    suspend fun getCharactersByName(
        @Query("name") name: String
    ): Response<MainResult<Character>>
}