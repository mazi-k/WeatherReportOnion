package com.onion.weatherreportonion.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onion.weatherreportonion.R
import com.onion.weatherreportonion.model.ClothesModel

class WardrobeForChoiceAdapter: RecyclerView.Adapter<WardrobeForChoiceAdapter.RecyclerItemViewHolder>() {

    private var data: MutableList<ClothesModel> = mutableListOf()

    fun setData(data: MutableList<ClothesModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_of_wardrobe_for_choice, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind (data: ClothesModel) {
            val itemName = itemView.findViewById<TextView>(R.id.itemName)
            val seasonName = itemView.findViewById<TextView>(R.id.seasonName)
            val temperature = itemView.findViewById<TextView>(R.id.temperature)
            val categoryName = itemView.findViewById<TextView>(R.id.categoryName)
            val styleName = itemView.findViewById<TextView>(R.id.styleName)
            val checkBoxOn = itemView.findViewById<ImageView>(R.id.checkbox_on)
            val checkBoxOff = itemView.findViewById<ImageView>(R.id.checkbox_off)

            itemName.text = data.description
            seasonName.text = data.season
            temperature.text = data.temperature.toString()
            categoryName.text = data.category
            styleName.text = data.style

            checkBoxOff.setOnClickListener {
                checkBoxOn.visibility = View.VISIBLE
                checkBoxOff.visibility = View.GONE
            }

            checkBoxOn.setOnClickListener {
                checkBoxOn.visibility = View.GONE
                checkBoxOff.visibility = View.VISIBLE
            }
        }
    }

}