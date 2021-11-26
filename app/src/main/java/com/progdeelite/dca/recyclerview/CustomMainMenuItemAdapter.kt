package com.progdeelite.dca.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.progdeelite.dca.custom_view.CustomMainMenuItem
import com.progdeelite.dca.databinding.CustomMenuItemBinding
import timber.log.Timber

class CustomMainMenuItemAdapter(context: Context, items: MutableList<Pair<String,String>>, listener: View.OnClickListener): BaseAdapter<CustomMainMenuItem>() {

    private val clickListener = listener
    private val layoutInflater = LayoutInflater.from(context)

    init {
        val customMainMenuItems = mutableListOf<CustomMainMenuItem>()
        items.forEach {
            customMainMenuItems.add(CustomMainMenuItem(context).apply {
                mainMenuItemLabelText = it.first
                mainMenuItemButtonText = it.second
            })
        }
        setItems(customMainMenuItems)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CustomMainMenuItem> {
        return CustomMainMenuItemViewHolder(
            getCustomMenuItemBinding(parent),
            getOnItemClickListener()
        )
    }

    private fun getOnItemClickListener() = object : OnItemClickListener<CustomMainMenuItem> {
        override fun onItemClicked(item: CustomMainMenuItem) {
            // VIDEO DO TIMBER TA MUITO MANEIRO -> OLHA NA SEQUÊNCIA
            Timber.d(item.mainMenuItemLabelText)
            clickListener.onClick(item)
        }
    }

    override fun getDiffUtilCallback(
        oldItems: List<CustomMainMenuItem>,
        newItems: List<CustomMainMenuItem>
    ): DiffUtil.Callback {
        return object: DiffUtil.Callback() {
            override fun getOldListSize() = oldItems.size
            override fun getNewListSize()= newItems.size

            override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
                return oldItems[oldPos].mainMenuItemLabelText == newItems[newPos].mainMenuItemLabelText
            }

            override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
               return oldItems[oldPos].mainMenuItemLabelText == newItems[newPos].mainMenuItemLabelText
            }
        }
    }

    private fun getCustomMenuItemBinding(parent: ViewGroup): CustomMenuItemBinding {
        // CustomMenuItemBinding.inflate((parent.context as AppCompatActivity).layoutInflater)
        // CustomMenuItemBinding.inflate(LayoutInflater.from(parent.context))
        return CustomMenuItemBinding.inflate(layoutInflater)
    }

}