package com.progdeelite.dca.settings_recycleview

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.progdeelite.dca.R
import com.progdeelite.dca.util_extension.inflate

sealed class SettingsViewItem {
    data class Route(
        @DrawableRes val iconMain: Int = -1,
        @StringRes val title: Int = -1,
        @StringRes val subtitle: Int = -1
    ) : SettingsViewItem()

    data class Map(
        @DrawableRes val iconMain: Int = -1,
        @StringRes val title: Int = -1,
        @StringRes val subtitle: Int = -1
    ) : SettingsViewItem()

    data class Linha(
        @DrawableRes val iconMain: Int = -1,
        @StringRes val title: Int = -1,
        @StringRes val subtitle: Int = -1
    ) : SettingsViewItem()

    data class Parada(
        @DrawableRes val iconMain: Int = -1,
        @StringRes val title: Int = -1,
        @StringRes val subtitle: Int = -1
    ) : SettingsViewItem()
}

@Suppress("UNCHECKED_CAST")
class SettingsAdapter(private val listener: ClickListener) : BaseAdapter<SettingsViewItem>() {

    interface ClickListener {
        fun onListItemClicked(item: SettingsViewItem)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<SettingsViewItem> {
        val itemView = parent.inflate(viewType)
        return when (viewType) {
            R.layout.card_paradas -> ParadaViewHolder(itemView, listener)
            R.layout.card_linhas -> LinhaViewHolder(itemView, listener)
            R.layout.card_map -> MapViewHolder(itemView, listener)
            R.layout.card_route -> RouteViewHolder(itemView, listener)
            else -> throw IllegalStateException("unknown view type: $viewType")
        } as BaseViewHolder<SettingsViewItem>
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is SettingsViewItem.Parada -> R.layout.card_paradas
            is SettingsViewItem.Linha -> R.layout.card_linhas
            is SettingsViewItem.Map -> R.layout.card_map
            is SettingsViewItem.Route -> R.layout.card_route
        }
    }

    override fun getDiffUtilCallback(
        oldItems: List<SettingsViewItem>,
        newItems: List<SettingsViewItem>
    ) =
        SettingsDiffUtilCallback(oldItems, newItems)

    inner class LinhaViewHolder(view: View, private val listener: ClickListener) :
        BaseAdapter.BaseViewHolder<SettingsViewItem.Linha>(view) {
        override fun bindView(item: SettingsViewItem.Linha) {
            with(itemView) {
                findViewById<TextView>(R.id.cardTitle).setText(item.title)
                findViewById<TextView>(R.id.cardSubtitle).setText(item.subtitle)
                findViewById<ImageView>(R.id.cardIconMain).setImageResource(item.iconMain)
                findViewById<ImageView>(R.id.cardImgArrow).setOnClickListener {
                    Toast.makeText(context,"faça o que desejar aqui...", Toast.LENGTH_SHORT).show()
                }
                setOnClickListener { listener.onListItemClicked(item) }
            }
        }
    }

    inner class ParadaViewHolder(view: View, private val listener: ClickListener) :
        BaseAdapter.BaseViewHolder<SettingsViewItem.Parada>(view) {
        override fun bindView(item: SettingsViewItem.Parada) {
            with(itemView) {
                findViewById<TextView>(R.id.cardTitle).setText(item.title)
                findViewById<TextView>(R.id.cardSubtitle).setText(item.subtitle)
                findViewById<ImageView>(R.id.cardIconMain).setImageResource(item.iconMain)
                findViewById<ImageView>(R.id.cardImgArrow).setOnClickListener {
                    Toast.makeText(context,"faça o que desejar aqui...", Toast.LENGTH_SHORT).show()
                }
                setOnClickListener { listener.onListItemClicked(item) }
            }
        }
    }

    inner class RouteViewHolder(view: View, private val listener: ClickListener) :
        BaseAdapter.BaseViewHolder<SettingsViewItem.Route>(view) {
        override fun bindView(item: SettingsViewItem.Route) {
            with(itemView) {
                findViewById<TextView>(R.id.cardTitle).setText(item.title)
                findViewById<TextView>(R.id.cardSubtitle).setText(item.subtitle)
                findViewById<ImageView>(R.id.cardIconMain).setImageResource(item.iconMain)
                findViewById<ImageView>(R.id.cardImgArrow).setOnClickListener {
                    Toast.makeText(context,"faça o que desejar aqui...", Toast.LENGTH_SHORT).show()
                }
                setOnClickListener { listener.onListItemClicked(item) }
            }
        }
    }

    inner class MapViewHolder(view: View, private val listener: ClickListener) :
        BaseAdapter.BaseViewHolder<SettingsViewItem.Map>(view) {
        override fun bindView(item: SettingsViewItem.Map) {
            with(itemView) {
                findViewById<TextView>(R.id.cardTitle).setText(item.title)
                findViewById<TextView>(R.id.cardSubtitle).setText(item.subtitle)
                findViewById<ImageView>(R.id.cardIconMain).setImageResource(item.iconMain)
                findViewById<ImageView>(R.id.cardImgArrow).setOnClickListener {
                    Toast.makeText(context,"faça o que desejar aqui...", Toast.LENGTH_SHORT).show()
                }
                setOnClickListener { listener.onListItemClicked(item) }
            }
        }
    }

    inner class SettingsDiffUtilCallback(
        private val oldItems: List<SettingsViewItem>,
        private val newItems: List<SettingsViewItem>
    ) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldItems.size
        override fun getNewListSize(): Int = newItems.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]

        // We use a == b as this translates to a?.equals(b) ?: (b === null), which means that we check for structural equality
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<T>>() {

    protected val items: MutableList<T> = mutableListOf()

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) =
        holder.bindView(items[position])

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    open fun setItems(newItems: List<T>) {
        getDiffUtilCallback(items.toList(), newItems)
            .also { items.clear() }
            .also { items.addAll(newItems) }
            ?.let { DiffUtil.calculateDiff(it).dispatchUpdatesTo(this) }
            ?: notifyDataSetChanged()
    }

    open fun getDiffUtilCallback(oldItems: List<T>, newItems: List<T>): DiffUtil.Callback? = null

    abstract class BaseViewHolder<V>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bindView(item: V)
    }

}