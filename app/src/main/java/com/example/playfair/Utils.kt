package com.example.playfair

typealias Coords = Pair<Int, Int>

fun <K, V> HashMap<K, V>.putChecked(key: K, v: V): V? {
    return if (!this.containsValue(v))
        this.put(key, v)
    else
        null

}

fun <K, V> HashMap<K, V>.getKey(v: V): K? {
    if (this.containsValue(v)) {
        for ((key, find) in this) {
            if (v == find) {
                return key
            }
        }
    }
    return null
}

infix operator fun <T : Comparable<T>> ClosedRange<T>.contains(n: T): Boolean {
    return this.start <= n && this.endInclusive >= n
}

fun justChecking()=println("checked")

