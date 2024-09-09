package uz.gita.m1nex.core

fun String.isValidPassword(): Boolean {
    if (this.isEmpty()) {
        return false
    }

    if (length < 8) {
        return false
    }

    if (this.contains(" ")) {
        return false
    }
    return true
}