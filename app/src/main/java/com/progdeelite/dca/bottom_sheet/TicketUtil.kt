package com.progdeelite.dca.bottom_sheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.progdeelite.dca.R

interface OnItemClickListener<T> {
    /**
     * Returns the item data, that has been clicked.
     */
    fun onItemClicked(item: T)
}

interface OnItemLongClickListener<T> {
    /**
     * Default implementation: Don't consume the long click.
     *
     * @return true if the callback consumed the long click, false otherwise.
     */
    fun onItemLongClicked(item: T, view: View): Boolean = false
}

enum class TicketSelectionType {
    SELECT,
    ENTER_MANUAL,
    ADD
}

data class TicketSelectionElement(
    var iconResourceId: Int,
    var title: String,
    var hasCheckmark: Boolean,
    var type: TicketSelectionType,
)

class TicketSelectionViewHolder(
    private val view: View,
    private val clickListener: OnItemClickListener<TicketSelectionElement>
) :
    RecyclerView.ViewHolder(view) {

    private var elementIcon: ImageView = view.findViewById(R.id.ticket_selection_element_icon)
    private var ticketSelectionTitle: TextView = view.findViewById(R.id.ticket_selection_title)
    private var checkMarkIcon: ImageView = view.findViewById(R.id.ticket_selection_check_mark)

    fun bind(element: TicketSelectionElement) {
        elementIcon.setImageResource(element.iconResourceId)
        ticketSelectionTitle.text = element.title
        checkMarkIcon.isVisible = element.hasCheckmark
        view.setOnClickListener {
            clickListener.onItemClicked(element)
        }
    }
}

class TicketSelectionAdapter(
    private var bottomSheetElements: List<TicketSelectionElement>,
    private val clickListener: OnItemClickListener<TicketSelectionElement>
) :
    RecyclerView.Adapter<TicketSelectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketSelectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ticket_selection_element, parent, false)
        return TicketSelectionViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: TicketSelectionViewHolder, position: Int) {
        holder.bind(bottomSheetElements[position])
    }

    override fun getItemCount() = bottomSheetElements.size
}