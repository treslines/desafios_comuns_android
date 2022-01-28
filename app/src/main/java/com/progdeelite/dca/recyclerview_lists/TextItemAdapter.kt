package com.progdeelite.dca.recyclerview_lists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.progdeelite.dca.databinding.TextListItemBinding

class TextItemAdapter(
    private val items: List<String>,
    val clickListener: (position: Int, value: String, view: View) -> Unit
) : RecyclerView.Adapter<TextItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(TextListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.onItemBind(items[position], position)
    }

    inner class ViewHolder(private val binding: TextListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onItemBind(text: String, position: Int) {
            binding.title.text = text
            binding.title.setOnClickListener {
                clickListener(position, text, it)
            }
        }
    }
}