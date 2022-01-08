package com.progdeelite.dca.restorable_listeners

sealed interface RestoreableAction {

    data class Positive(val type: ActionType) : RestoreableAction
    data class Negative(val type: ActionType) : RestoreableAction
    data class Dismiss(val type: ActionType) : RestoreableAction

    enum class ActionType {
        OPEN_SETTINGS,
        OPEN_PLAYSTORE,
        DISMISS,
        NONE;

        companion object {
            fun getType(name: String): ActionType {
                return values().firstOrNull { v -> v.name.lowercase() == name.lowercase() } ?: NONE
            }
        }
    }
}