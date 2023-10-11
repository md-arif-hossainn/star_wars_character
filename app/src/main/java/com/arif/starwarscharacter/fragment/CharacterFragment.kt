package com.arif.starwarscharacter.fragment

import com.arif.starwarscharacter.paging.character.CharacterPagingAdapter
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
import com.arif.starwarscharacter.databinding.FragmentCharacterBinding
import com.arif.starwarscharacter.models.character.CharacterResult
import com.arif.starwarscharacter.paging.LoaderAdapter
import com.google.gson.Gson


class CharacterFragment : Fragment() {
    private lateinit var binding: FragmentCharacterBinding
    lateinit var starWarViewModel: StarWarViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CharacterPagingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterBinding.inflate(inflater,container,false)
        binding.myToolbar.title = "Character List"
        recyclerView = binding.characterListRV

        val retrofitModule = RetrofitModule()
        val retrofit = retrofitModule.getRetrofit()
        val starWarApi = retrofitModule.getStarWarApi(retrofit)
        val starWarRepository = StarWarRepository(starWarApi)
        starWarViewModel = StarWarViewModel(starWarRepository)

        adapter = CharacterPagingAdapter(::onReceiveItemFromAdapter)

        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        starWarViewModel.characterList.observe(requireActivity(), Observer {
            adapter.submitData(lifecycle, it)
        })
        return binding.root
    }



    private fun onReceiveItemFromAdapter(item: CharacterResult) {
        val bundle = Bundle().apply {
            putString("character_data",Gson().toJson(item))
//            putString("info1",item.name.toString())
//            putString("info2",item.height.toString())
//            putString("info3",item.mass.toString())
//            putString("info4",item.hairColor.toString())
//            putString("info5",item.skinColor.toString())
//            putString("info6",item.birthYear.toString())
//            putString("info7",item.gender.toString())

        }
        findNavController().navigate(R.id.action_characterFragment_to_characterDetailsFragment,bundle)
    }

}