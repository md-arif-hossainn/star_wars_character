import com.arif.starwarscharacter.models.character.CharacterList
import com.arif.starwarscharacter.models.planet.PlanetsList
import com.arif.starwarscharacter.models.starship.StarshipList

import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarApi {

    @GET("people")
    suspend fun getCharacter(@Query("page") page: Int): CharacterList

    @GET("starships")
    suspend fun getStarShip(@Query("page") page: Int): StarshipList

    @GET("planets")
    suspend fun getPlanet(@Query("page") page: Int): PlanetsList

}
