package uz.gita.m1nex.entity.data.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transfers_table")
data class LastTransfersTable(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val owner: String,
    val pan: String,
)