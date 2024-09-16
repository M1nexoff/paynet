package uz.gita.m1nex.core.helper

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


abstract class SharedPreference(context: Context, preferences: SharedPreferences? = null) {
    private val pref = preferences ?: context.getSharedPreferences(javaClass.canonicalName, Context.MODE_PRIVATE)
    private val gson = Gson()

    inner class booleans(private val init: Boolean = false) :
        ReadWriteProperty<Any, Boolean> {
        override fun getValue(thisRef: Any, property: KProperty<*>) = pref.getBoolean(property.name, init)
        override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) = pref.edit { putBoolean(property.name, value).apply() }
    }

    inner class ints(private val defValue: Int = -1) : ReadWriteProperty<Any, Int> {
        override fun getValue(thisRef: Any, property: KProperty<*>) = pref.getInt(property.name, defValue)
        override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) = pref.edit { putInt(property.name, value).apply() }
    }

    inner class longs(private val defValue: Long = 0L) : ReadWriteProperty<Any, Long> {
        override fun getValue(thisRef: Any, property: KProperty<*>) = pref.getLong(property.name, defValue)
        override fun setValue(thisRef: Any, property: KProperty<*>, value: Long) = pref.edit { putLong(property.name, value).apply() }
    }

    inner class strings(private val defValue: String = "") : ReadWriteProperty<Any, String> {
        override fun getValue(thisRef: Any, property: KProperty<*>): String = pref.getString(property.name, defValue) ?: ""

        override fun setValue(thisRef: Any, property: KProperty<*>, value: String) = pref.edit { putString(property.name, value).apply() }
    }

    inner class stringsNullable(private val defValue: String? = null) :
        ReadWriteProperty<Any, String?> {
        override fun getValue(thisRef: Any, property: KProperty<*>): String? = pref.getString(property.name, defValue)

        override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) = value?.run { pref.edit { putString(property.name, value).apply() } } ?: Unit
    }

    inner class objects<T>(private val clazz: Class<T>, private val defValue: T) : ReadWriteProperty<Any, T> {
        override fun getValue(thisRef: Any, property: KProperty<*>): T {
            val json = pref.getString(property.name, null)
            return if (json != null) gson.fromJson(json, clazz) else defValue
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
            pref.edit {
                putString(property.name, gson.toJson(value)).apply()
            }
        }
    }

}