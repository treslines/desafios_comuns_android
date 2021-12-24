package com.progdeelite.dca.filter

import java.util.*

// 1) Porque no modelo?
//      - one point of change (Unico ponto de mudança), se classe mudar, so altero aqui
//      - remover view, logica permanace re-usável
//      - evitar o maximo possivel reter lógica em views
//      - testes são muito mais fáceis e rápidos

// 2) Lógica de para um fulltext search
// 3) Lógica de OR match (apenas um dos filtros)
// 4) Lógica de AND match (todos os filtros)
// 5) Lógica de filtros para listas (recyclerViews)
data class Alarm(

    val id: Int,
    val actionType: String,
    val active: Boolean,
    val alarmCode: String,
    val alarmType: String,
    val deactivationTime: String,
    val occurringTime: String,
    val priority: Int,
    val state: String,
) {

    // fulltext search
    fun anyMatch(searchText: String): Boolean {
        val key = searchText.lowercase(Locale.getDefault())
        when {
            actionType.lower().contains(key) or
            alarmCode.lower().contains(key) or
            alarmType.lower().contains(key) or
            occurringTime.lower().contains(key) or
            priority.toString().lower().contains(key) or
            state.lower().contains(key) -> return true
        }
        return false
    }

    fun typeMatch(searchText: String): Boolean = hasMatch(searchText, alarmType)
    fun statusMatch(searchText: String): Boolean = hasMatch(searchText, state)
    fun priorityMatch(searchText: String): Boolean = hasMatch(searchText, priority.toString())


    fun timeMatch(searchTime: Date): Boolean {
        val occTime = ModelMapper.getEnglishDateFromString(occurringTime)
        if(occTime != null) {
            return when {
                occTime.after(searchTime) -> true
                else -> false
            }
        }
        return false
    }

    fun timeMatch(searchText: String): Boolean {
        val searchTime = ModelMapper.getEnglishDateFromString(searchText)
        val occTime = ModelMapper.getEnglishDateFromString(occurringTime)
        if(occTime != null) {
            return when {
                occTime.after(searchTime) -> true
                else -> false
            }
        }
        return false
    }

    fun and(searchText:String, toFilterFrom: MutableList<Alarm>, filter: FilterType): MutableList<Alarm> {
        return when(filter){
            FilterType.PRIORITY -> toFilterFrom.filter { a -> a.priorityMatch(searchText) }.toMutableList()
            FilterType.STATUS -> toFilterFrom.filter { a -> a.statusMatch(searchText) }.toMutableList()
            FilterType.TYPE -> toFilterFrom.filter { a -> a.typeMatch(searchText) }.toMutableList()
            FilterType.TIME -> return toFilterFrom.filter { a -> a.timeMatch(searchText) }.toMutableList()
        }
    }

    fun and(searchText:String, toFilterFrom: MutableList<Alarm>, vararg filters: FilterType): MutableList<Alarm> {
        var filtered = mutableListOf<Alarm>()
        filtered.addAll(toFilterFrom)
        filters.forEach { filter ->
            filtered = when(filter){
                FilterType.PRIORITY -> filtered.filter { a -> a.priorityMatch(searchText) }.toMutableList()
                FilterType.STATUS -> filtered.filter { a -> a.statusMatch(searchText) }.toMutableList()
                FilterType.TYPE -> filtered.filter { a -> a.typeMatch(searchText) }.toMutableList()
                FilterType.TIME -> filtered.filter { a -> a.timeMatch(searchText) }.toMutableList()
            }
        }
        return filtered
    }

    enum class FilterType {
        PRIORITY, TYPE, STATUS, TIME
    }

    private fun hasMatch(searchText:String, property:String): Boolean {
        val key = searchText.lower()
        return when {
            property.lower().contains(key) -> true
            else -> false
        }
    }

    private fun String.lower(): String {
        return this.lowercase(Locale.getDefault())
    }
}