package com.onion.weatherreportonion.ui.profile.wardrobe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.onion.weatherreportonion.R
import com.onion.weatherreportonion.databinding.FragmentMyWardrobeBinding
import com.onion.weatherreportonion.model.ClothesModel
import com.onion.weatherreportonion.model.UserModel
import com.onion.weatherreportonion.ui.adapters.FriendsFeedAdapter
import com.onion.weatherreportonion.ui.adapters.WardrobeAdapter

class MyWardrobeFragment: Fragment() {

    private lateinit var binding: FragmentMyWardrobeBinding
    private lateinit var adapter: WardrobeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_wardrobe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMyWardrobeBinding.bind(view)
        adapter = WardrobeAdapter()

        initElements()
    }

    private fun initElements() {
        binding.addThingFAB.setOnClickListener {
            addFragment(R.id.fragment_container, AddThingFragment())
        }
        binding.filtersLayout.setOnClickListener {
            addFragment(R.id.fragment_container, FiltersFragment())
        }
        binding.searchView.setOnClickListener { }

        binding.itemsWardrobeRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.itemsWardrobeRecycler.adapter = adapter
        adapter.setData(generateFriends())
    }

    private fun addFragment(container: Int, fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .addToBackStack(null)
            .commit()
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