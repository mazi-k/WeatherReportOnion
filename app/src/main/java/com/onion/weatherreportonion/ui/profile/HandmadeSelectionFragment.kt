package com.onion.weatherreportonion.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.onion.weatherreportonion.R
import com.onion.weatherreportonion.databinding.FragmentHandmadeSelectionBinding
import com.onion.weatherreportonion.model.ClothesModel
import com.onion.weatherreportonion.ui.adapters.WardrobeForChoiceAdapter

class HandmadeSelectionFragment: Fragment() {

    private lateinit var binding: FragmentHandmadeSelectionBinding
    private lateinit var adapter: WardrobeForChoiceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_handmade_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHandmadeSelectionBinding.bind(view)
        adapter = WardrobeForChoiceAdapter()
        initElements()
    }


    private fun initElements() {
        binding.doneButton.setOnClickListener {
            Toast.makeText(requireContext(), "Набор опубликован", Toast.LENGTH_LONG).show()
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, MyProfileFragment())
                .commit()
        }


        binding.itemsWardrobeRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.itemsWardrobeRecycler.adapter = adapter
        adapter.setData(generateFriends())
    }

    private fun generateFriends(): MutableList<ClothesModel> {
        return mutableListOf(
            ClothesModel("cиние джинсы", "Любой", -10, "Низ", "Повседневный"),
            ClothesModel("черная футболка", "Лето", 15, "Верх", "Повседневный"),
            ClothesModel("клетчатая рубашка", "Любой", 10, "Верх", "Повседневный"),
            ClothesModel("серая куртка", "Зима", -10, "Верхняя одежда", "Повседневный"),
            ClothesModel("зимние кроссовки", "Зима", -10, "Обувь", "Повседневный"),
            ClothesModel("шапка Nike", "Зима", -10, "Аксессуары", "Повседневный"),
        )
    }
}