package com.rapid.compose.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Serializable
data class MessageBean(
    @SerialName("category") val category: Int = 0,
    @SerialName("date") val date: Long = 0L,
    @SerialName("fromUser") val fromUser: String? = "",
    @SerialName("fromUserId") val fromUserId: Int = 0,
    @SerialName("fromUserNick") val fromUserNick: String? = "",
    @SerialName("fullLink") val fullLink: String? = "",
    @SerialName("id") val id: Int = 0,
    @SerialName("isRead") val readFlag: Int = 0,
    @SerialName("link") val link: String? = "",
    @SerialName("message") val message: String? = "",
    @SerialName("niceDate") val niceDate: String? = "",
    @SerialName("tag") val tag: String? = "",
    @SerialName("title") val title: String? = "",
    @SerialName("type") val type: Int = 0,
    @SerialName("userId") val userId: Int = 0
) {
    val isRead: Boolean
        get() = readFlag == 1

    val displayTime: String
        get() = niceDate?.takeIf { it.isNotBlank() }
            ?: date.takeIf { it > 0 }
                ?.let { DATE_FORMAT.get()?.format(Date(it)) }
            ?: ""

    val effectiveLink: String
        get() = link?.trim()?.takeIf { it.isNotEmpty() }
            ?: fullLink?.trim()?.takeIf { it.isNotEmpty() }
            ?: ""

    companion object {
        private val DATE_FORMAT: ThreadLocal<SimpleDateFormat> =
            ThreadLocal.withInitial { SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()) }
    }
}
