package com.progdeelite.dca.searchview

import com.progdeelite.dca.filter.Alarm
import kotlin.random.Random.Default.nextInt

object AlarmMock {
    // VEJA VIDEO DA CRIACÃO DO MODELO E DA LOGICA DE FILTRAGEM QUE TA TOPIZEIRA
    fun getAlarmList(): MutableList<Alarm> {
        val alarmList = mutableListOf<Alarm>()
        for (i in 0 until 100) {
            alarmList.add(
                i, Alarm(
                    id = i,
                    alarmType = getRandomItem(listOf("O", "B")),
                    priority = getRandomItem(listOf(1, 2, 3)),
                    occurringTime = getRandomItem(
                        listOf(
                            "2021-08-09 23:00:28",
                            "2021-08-05 08:25:58",
                            "2021-08-23 14:23:23",
                            "2021-08-07 09:20:31",
                            "2021-13-12 10:49:28",
                            "2021-13-12 11:25:58",
                            "2021-13-12 09:20:31"
                        )
                    ),
                    actionType = "INSERT",
                    alarmCode = "Descrição Alarme",
                    deactivationTime = "8.6.2021 15:33",
                    active = true,
                    state = getRandomItem(
                        listOf(
                            "INACTIVE",
                            "OPERATIONAL",
                            "ACKNOWLEDGED",
                            "FINISHED"
                        )
                    )
                )
            )
        }
        return alarmList.sortedWith(compareBy { it.occurringTime }).asReversed()
            .toMutableList()
    }

    private fun <T> getRandomItem(list: List<T>): T {
        return list[nextInt(0, list.size)]
    }
}