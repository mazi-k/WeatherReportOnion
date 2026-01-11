package com.onion.weatherreportonion.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.onion.weatherreportonion.R
import com.onion.weatherreportonion.databinding.FragmentFriendsFeedBinding
import com.onion.weatherreportonion.model.OnItemClickListener
import com.onion.weatherreportonion.model.UserModel
import com.onion.weatherreportonion.ui.adapters.FriendsFeedAdapter

class FriendsFeedFragment: Fragment() {

    private lateinit var binding: FragmentFriendsFeedBinding

    private lateinit var adapter: FriendsFeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_friends_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFriendsFeedBinding.bind(view)

        val listener: OnItemClickListener = object: OnItemClickListener {
            override fun onUserClick(item: UserModel?) {
                if (item != null) {
                    startFragment()
                }
            }
        }

        adapter = FriendsFeedAdapter(listener)

        initElements()
    }

    private fun initElements() {
        binding.friendsFeedRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.friendsFeedRecyclerView.adapter = adapter
        adapter.setData(generateFriends())
    }

    private fun startFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, UserProfileFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun generateFriends(): MutableList<UserModel> {
        return mutableListOf(
            UserModel("Аня Кирова", "cиние джинсы, черная футболка, клетчатая рубашка, серая куртка, зимние кроссовки"),
            UserModel("Кирилл Ким", "cиние джинсы, черная футболка, клетчатая рубашка, серая куртка, зимние кроссовки"),
            UserModel("Полина Олешева", "cиние джинсы, черная футболка, клетчатая рубашка, серая куртка, зимние кроссовки"),
            UserModel("Олежа Пес", "cиние джинсы, черная футболка, клетчатая рубашка, серая куртка, зимние кроссовки"),
            UserModel("Руслан Сабитов", "cиние джинсы, черная футболка, клетчатая рубашка, серая куртка, зимние кроссовки"),
            UserModel("Катя Игорева", "cиние джинсы, черная футболка, клетчатая рубашка, серая куртка, зимние кроссовки"),
        )
    }
}