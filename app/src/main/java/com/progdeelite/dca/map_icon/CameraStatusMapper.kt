package com.progdeelite.dca.map_icon

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.progdeelite.dca.R
import com.progdeelite.dca.map_icon.CameraScanStatus.*

@DrawableRes
fun CameraScanStatus.mapIconResId(): Int? = when (this) {
    ID_MOVE_CLOSER -> R.drawable.ic_camera_aproxime
    ID_MOVE_AWAY -> R.drawable.ic_camera_afaste
    ID_SECOND_PAGE-> R.drawable.ic_camera_rotacione
    ID_HOLD_STILL -> R.drawable.ic_camera_nao_se_mexa
    ID_BAD_POSITION -> R.drawable.ic_camera_reposicione
    else -> null
}

@StringRes
fun CameraScanStatus.mapMessageResId(): Int? = when (this) {
    ID_MOVE_CLOSER -> R.string.camera_aproxime
    ID_MOVE_AWAY -> R.string.camera_afaste
    ID_WAITING -> R.string.camera_aguarde
    ID_SCANNING -> R.string.camera_scaneando
    ID_BAD_POSITION -> R.string.camera_reposicione
    ID_HOLD_STILL -> R.string.camera_nao_se_mexa
    else -> null
}