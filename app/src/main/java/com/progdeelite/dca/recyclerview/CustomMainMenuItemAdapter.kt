package com.progdeelite.dca.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.progdeelite.dca.databinding.CustomMenuItemBinding
import timber.log.Timber

class CustomMainMenuItemAdapter(
    context: Context,
    itemList: MutableList<Pair<String,String>>,
    listener: View.OnClickListener
): BaseAdapter<CustomMenuItemBinding>() {

    private val clickListener = listener
    private val layoutInflater = LayoutInflater.from(context)

    init {
        val customMainMenuItems = mutableListOf<CustomMenuItemBinding>()
        itemList.forEach {
            val a = CustomMenuItemBinding.inflate(layoutInflater)
            a.mainMenuItemTitle.text = it.first
            a.mainMenuItemButton.text = it.second
            customMainMenuItems.add(a)
        }
        setItems(customMainMenuItems)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CustomMenuItemBinding> {
        return CustomMainMenuItemViewHolder(
            getCustomMenuItemBinding(parent),
            getOnItemClickListener()
        )
    }

    private fun getOnItemClickListener() = object : OnItemClickListener<CustomMenuItemBinding> {
        override fun onItemClicked(item: CustomMenuItemBinding) {
            // VIDEO DO TIMBER TA MUITO MANEIRO -> OLHA NA SEQUÊNCIA
            Timber.d(item.mainMenuItemTitle.text.toString())
            clickListener.onClick(item.root)
        }
    }

    override fun getDiffUtilCallback(
        oldItems: List<CustomMenuItemBinding>,
        newItems: List<CustomMenuItemBinding>
    ): DiffUtil.Callback {
        return object: DiffUtil.Callback() {
            override fun getOldListSize() = oldItems.size
            override fun getNewListSize()= newItems.size

            override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
                return oldItems[oldPos].mainMenuItemTitle.text == newItems[newPos].mainMenuItemTitle.text
            }

            override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
               return oldItems[oldPos].mainMenuItemTitle.text == newItems[newPos].mainMenuItemTitle.text
            }
        }
    }

    private fun getCustomMenuItemBinding(parent: ViewGroup): CustomMenuItemBinding {
        // CustomMenuItemBinding.inflate((parent.context as AppCompatActivity).layoutInflater)
        // CustomMenuItemBinding.inflate(LayoutInflater.from(parent.context))
        return CustomMenuItemBinding.inflate(layoutInflater)
    }

}