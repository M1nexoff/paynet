package uz.gita.m1nex.core.data.model.transfer

import java.io.Serializable

data class TransferData(
    val type: String,
    val from: String,
    val to: String,
    val amount: Long,
    val time: Long
): Serializable