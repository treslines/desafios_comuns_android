package com.progdeelite.dca.searchview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.progdeelite.dca.databinding.AlarmListItemBinding
import com.progdeelite.dca.design.getPriorityColor
import com.progdeelite.dca.filter.Alarm
import com.progdeelite.dca.filter.ModelMapper

class AlarmListAdapter(
    private val onAlarmInteraction: OnAlarmInteraction,
) : ListAdapter<Alarm, AlarmListAdapter.ViewHolder>(AlarmDiffCallback()) {

    interface OnAlarmInteraction {
        fun onAlarmClicked(alarmClicked: Alarm)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AlarmListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class ViewHolder(private val binding: AlarmListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(alarmAtPos: Alarm) {
            with(binding) {
                layoutParent.setOnClickListener { onAlarmInteraction.onAlarmClicked(alarmAtPos) }
                type.background = getPriorityColor(border.context, alarmAtPos.priority)
                type.text = alarmAtPos.alarmType
                alarmCode.text = alarmAtPos.alarmCode
                time.text = ModelMapper.getTimeComponentFromDateTime(alarmAtPos.occurringTime)
                aktivStatus.text = ModelMapper.getZustandMapping(alarmAtPos.active)
                texts.text = alarmAtPos.deactivationTime
            }
        }
    }

    private class AlarmDiffCallback : DiffUtil.ItemCallback<Alarm>() {

        override fun areItemsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
            return oldItem == newItem
        }
    }
}