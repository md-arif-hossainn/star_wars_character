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
import com.arif.starwarscharacter.databinding.FragmentStarShipBinding
import com.arif.starwarscharacter.models.starship.StarshipResult
import com.arif.starwarscharacter.paging.LoaderAdapter
import com.arif.starwarscharacter.paging.starship.StarshipPagingAdapter
import com.google.gson.Gson


class StarShipFragment : Fragment() {

    private lateinit var binding: FragmentStarShipBinding
    lateinit var starWarViewModel: StarWarViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: StarshipPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStarShipBinding.inflate(inflater,container,false)
        binding.myToolbar.title = "Starship List"

        recyclerView = binding.starShipListRV
        val retrofitModule = RetrofitModule()
        val retrofit = retrofitModule.getRetrofit()
        val quoteAPI = retrofitModule.getStarWarApi(retrofit)
        val starWarRepository = StarWarRepository(quoteAPI)
        starWarViewModel = StarWarViewModel(starWarRepository)

        adapter = StarshipPagingAdapter(::onReceiveItemFromAdapter)

        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        starWarViewModel.starShipList.observe(requireActivity(), Observer {
            adapter.submitData(lifecycle, it)
        })
        return binding.root
    }

    private fun onReceiveItemFromAdapter(item: StarshipResult) {
        val bundle = Bundle().apply {
            putString("starship_data",Gson().toJson(item))
//            putString("info1",item.name.toString())
//            putString("info2",item.model.toString())
//            putString("info3",item.manufacturer.toString())
//            putString("info4",item.costInCredits.toString())
//            putString("info5",item.length.toString())
//            putString("info6",item.maxAtmospheringSpeed.toString())
//            putString("info7",item.crew.toString())
//            putString("info8",item.passengers.toString())
//            putString("info9",item.consumables.toString())
//            putString("info10",item.hyperdriveRating.toString())

        }
        findNavController().navigate(R.id.action_starShipFragment_to_characterDetailsFragment,bundle)
    }

}





