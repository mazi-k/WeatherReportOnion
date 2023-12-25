package com.onion.weatherreportonion.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onion.weatherreportonion.R
import com.onion.weatherreportonion.model.OnItemClickListener
import com.onion.weatherreportonion.model.UserModel

class FriendsFeedAdapter(val listener: OnItemClickListener): RecyclerView.Adapter<FriendsFeedAdapter.RecyclerItemViewHolder>() {

    private var data: MutableList<UserModel> = mutableListOf()

    fun setData(data: MutableList<UserModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_friend_feed, parent, false) as View)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind (data: UserModel) {
            val avatar = itemView.findViewById<ImageView>(R.id.avatar)
            val username = itemView.findViewById<TextView>(R.id.username)
            val clothesList = itemView.findViewById<TextView>(R.id.clothesList)

            username.text = data.username
            clothesList.text = data.clothesList
            itemView.setOnClickListener { listener.onUserClick(data) }
        }
    }
}