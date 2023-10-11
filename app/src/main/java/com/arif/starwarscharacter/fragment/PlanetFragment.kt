package com.arif.starwarscharacter.fragment

import RetrofitModule
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arif.paging_android.repository.StarWarRepository
import com.arif.paging_android.viewmodel.StarWarViewModel
import com.arif.starwarscharacter.R
import com.arif.starwarscharacter.databinding.FragmentPlanetBinding
import com.arif.starwarscharacter.models.planet.PlanetResult
import com.arif.starwarscharacter.paging.LoaderAdapter
import com.arif.starwarscharacter.paging.planet.PlanetPagingAdapter
import com.google.gson.Gson


class PlanetFragment : Fragment() {
    private lateinit var binding: FragmentPlanetBinding
    lateinit var starWarViewModel: StarWarViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: PlanetPagingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlanetBinding.inflate(inflater,container,false)

        binding.myToolbar.title = "Planet List"

        recyclerView = binding.planetListRV
        val retrofitModule = RetrofitModule()
        val retrofit = retrofitModule.getRetrofit()
        val quoteAPI = retrofitModule.getStarWarApi(retrofit)
        val starWarRepository = StarWarRepository(quoteAPI)
        starWarViewModel = StarWarViewModel(starWarRepository)

        adapter = PlanetPagingAdapter(::onReceiveItemFromAdapter)

        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        starWarViewModel.planetList.observe(requireActivity(), Observer {
            adapter.submitData(lifecycle, it)
        })
        return binding.root
    }

    private fun onReceiveItemFromAdapter(item: PlanetResult) {
        val bundle = Bundle().apply {
            putString("planet_data", Gson().toJson(item))
//            putString("planet1",item.name.toString())
//            putString("planet2",item.rotationPeriod.toString())
//            putString("planet3",item.orbitalPeriod.toString())
//            putString("planet4",item.diameter.toString())
//            putString("planet5",item.gravity.toString())
//            putString("planet6",item.terrain.toString())
//            putString("planet7",item.surfaceWater.toString())
//            putString("planet8",item.population.toString())

        }
        findNavController().navigate(R.id.action_planetFragment_to_characterDetailsFragment,bundle)
    }

}
