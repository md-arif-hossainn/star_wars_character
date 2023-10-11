package com.arif.starwarscharacter.details_page

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arif.starwarscharacter.databinding.FragmentDetailsBinding
import com.arif.starwarscharacter.models.character.CharacterResult
import com.arif.starwarscharacter.models.planet.PlanetResult
import com.arif.starwarscharacter.models.starship.StarshipResult
import com.google.gson.Gson


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater,container,false)

        //character
        val characterName = "<b>Name: </b> "
        val height = "<b>Height: </b> "
        val mass = "<b>Mass: </b> "
        val hairColor = "<b>Hair Color: </b> "
        val skinColor = "<b>Skin Color: </b> "
        val birthYear = "<b>Birth Year: </b> "
        val gender = "<b>Gender: </b> "

        //planet
        val planetName = "<b>Name: </b> "
        val rotationPeriod = "<b>Rotation Period: </b> "
        val diameter = "<b>Diameter: </b> "
        val gravity = "<b>Gravity: </b> "
        val terrain = "<b>Terrain: </b> "
        val surfaceWater = "<b>SurfaceWater: </b> "
        val population = "<b>Population: </b> "
        val orbitalPeriod = "<b>Orbital Period: </b> "

        //starship
        val starshipName = "<b>Name: </b> "
        val model = "<b>Model: </b> "
        val manufacturer = "<b>Manufacturer: </b> "
        val costInCredits = "<b>Cost In Credits: </b> "
        val length = "<b>Length: </b> "
        val maxAtmospheringSpeed = "<b>Max Atmosphering Speed: </b> "
        val crew = "<b>Crew: </b> "
        val passengers = "<b>Passengers: </b> "
        val consumables = "<b>Consumables: </b> "
        val hyperdriveRating = "<b>Hyper Drive Rating: </b> "

        val bundle = arguments
        if (bundle?.containsKey("character_data") == true) {
            val data = bundle.getString("character_data")
            val modelData = Gson().fromJson(data,CharacterResult::class.java)

            if (modelData != null) {

               binding.myToolbar.title = "Character Details"
               binding.TV1.text = Html.fromHtml(characterName + modelData.name, Html.FROM_HTML_MODE_COMPACT)
               binding.TV2.text = Html.fromHtml(height + modelData.height, Html.FROM_HTML_MODE_COMPACT)
               binding.TV3.text = Html.fromHtml(mass + modelData.mass, Html.FROM_HTML_MODE_COMPACT)
               binding.TV4.text = Html.fromHtml(hairColor + modelData.hairColor, Html.FROM_HTML_MODE_COMPACT)
               binding.TV5.text = Html.fromHtml(skinColor + modelData.skinColor, Html.FROM_HTML_MODE_COMPACT)
               binding.TV6.text = Html.fromHtml(birthYear + modelData.birthYear, Html.FROM_HTML_MODE_COMPACT)
               binding.TV7.text = Html.fromHtml(gender + modelData.gender, Html.FROM_HTML_MODE_COMPACT)
            }
        }
        else if (bundle?.containsKey("starship_data") == true) {
            val data = bundle.getString("starship_data")
            val modelData = Gson().fromJson(data,StarshipResult::class.java)

            if (modelData != null) {

                binding.myToolbar.title = "Starship Details"
                binding.TV1.text = Html.fromHtml(planetName + modelData.name, Html.FROM_HTML_MODE_COMPACT)
                binding.TV2.text = Html.fromHtml(model + modelData.model, Html.FROM_HTML_MODE_COMPACT)
                binding.TV3.text = Html.fromHtml(manufacturer + modelData.manufacturer, Html.FROM_HTML_MODE_COMPACT)
                binding.TV4.text = Html.fromHtml(costInCredits + modelData.costInCredits, Html.FROM_HTML_MODE_COMPACT)
                binding.TV5.text = Html.fromHtml(length + modelData.length, Html.FROM_HTML_MODE_COMPACT)
                binding.TV6.text = Html.fromHtml(maxAtmospheringSpeed + modelData.maxAtmospheringSpeed, Html.FROM_HTML_MODE_COMPACT)
                binding.TV7.text = Html.fromHtml(crew + modelData.crew, Html.FROM_HTML_MODE_COMPACT)
            }
        }
        else if(bundle?.containsKey("planet_data") == true) {
            val data = bundle.getString("planet_data")
            val modelData = Gson().fromJson(data,PlanetResult::class.java)

            if (modelData != null) {

                binding.myToolbar.title = "Planet Details"
                binding.TV1.text = Html.fromHtml(starshipName + modelData.name, Html.FROM_HTML_MODE_COMPACT)
                binding.TV2.text = Html.fromHtml(rotationPeriod + modelData.rotationPeriod, Html.FROM_HTML_MODE_COMPACT)
                binding.TV3.text = Html.fromHtml(diameter +modelData.diameter , Html.FROM_HTML_MODE_COMPACT)
                binding.TV4.text = Html.fromHtml(gravity + modelData.gravity, Html.FROM_HTML_MODE_COMPACT)
                binding.TV5.text = Html.fromHtml(terrain + modelData.terrain, Html.FROM_HTML_MODE_COMPACT)
                binding.TV6.text = Html.fromHtml(surfaceWater + modelData.surfaceWater, Html.FROM_HTML_MODE_COMPACT)
                binding.TV7.text = Html.fromHtml(population + modelData.population, Html.FROM_HTML_MODE_COMPACT)
               }
            }

        return binding.root
    }


}