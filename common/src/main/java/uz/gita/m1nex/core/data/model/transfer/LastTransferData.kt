package uz.gita.m1nex.core.data.model.transfer

import java.io.Serializable

data class LastTransferData(
    val id: Long,
    val owner: String,
    val pan: String
): Serializable