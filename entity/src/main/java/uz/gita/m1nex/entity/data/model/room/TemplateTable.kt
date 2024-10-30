package uz.gita.m1nex.entity.data.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class TemplateTable(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val templateName: String,
    val owner: String,
    val pan: String,
)