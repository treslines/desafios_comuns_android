package com.progdeelite.dca.mvp.base_alert

import androidx.annotation.StringRes
import com.progdeelite.dca.mvp.base_contract.NavigationContract

enum class AlertSize {
    SmallScreen, FullScreen
}

enum class AlertIcon {
    Error, Warning, Success, Info
}

data class AlertContent(
    @StringRes val title: Int? = null,
    @StringRes val message: Int? = null,
    @StringRes val subTitle: Int? = null,
    @StringRes val description: Int? = null
)

data class AlertAction(
    val positive: () -> Unit = {},
    val negative: () -> Unit = {},
    val dismiss: () -> Unit = {},
    val cancel: () -> Unit = {}
)

sealed class AlertType(
    open val size: AlertSize,
    open val icon: AlertIcon,
    open val content: AlertContent,
    open val action: AlertAction
) {
    data class FullScreenInfo(override val content: AlertContent, override val action: AlertAction) :
        AlertType(AlertSize.FullScreen, AlertIcon.Info, content, action)

    data class FullScreenWarning(override val content: AlertContent, override val action: AlertAction) :
        AlertType(AlertSize.FullScreen, AlertIcon.Warning, content, action)

    data class FullScreenSuccess(override val content: AlertContent, override val action: AlertAction) :
        AlertType(AlertSize.FullScreen, AlertIcon.Success, content, action)

    data class FullScreenError(override val content: AlertContent, override val action: AlertAction) :
        AlertType(AlertSize.FullScreen, AlertIcon.Error, content, action)

    data class SmallScreenInfo(override val content: AlertContent, override val action: AlertAction) :
        AlertType(AlertSize.SmallScreen, AlertIcon.Info, content, action)

    data class SmallScreenWarning(override val content: AlertContent, override val action: AlertAction) :
        AlertType(AlertSize.SmallScreen, AlertIcon.Warning, content, action)

    data class SmallScreenSuccess(override val content: AlertContent, override val action: AlertAction) :
        AlertType(AlertSize.SmallScreen, AlertIcon.Success, content, action)

    data class SmallScreenError(override val content: AlertContent, override val action: AlertAction) :
        AlertType(AlertSize.SmallScreen, AlertIcon.Error, content, action)
}