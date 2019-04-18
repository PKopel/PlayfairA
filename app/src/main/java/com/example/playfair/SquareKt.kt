package com.example.playfair

import java.io.Serializable
import java.lang.StringBuilder

class SquareKt(keyWord: String): Serializable {
    private val key = HashMap<Char, Coords>()

    init {
        val temp = keyWord.toLowerCase().toCharArray()
        var i = 0
        var j = 0
        for (c in temp) {
            if('a'..'z' contains c || '0'..'9' contains c)
            if (!key.containsKey(c)) {
                key.putChecked(c, Coords(j, i))
                if (i != 5) i++
                else {
                    i = 0; j++
                }
            }
        }
        for (c: Char in '0'..'9') {
            if (!key.containsKey(c)) {
                key.putChecked(c, Coords(j, i))
                if (i != 5) i++
                else {
                    i = 0; j++
                }
            }
        }
        for (c: Char in 'a'..'z') {
            if (!key.containsKey(c)) {
                key.putChecked(c, Coords(j, i))
                if (i != 5) i++
                else {
                    i = 0; j++
                }
            }
        }
    }

    fun code(decoded: String): String {
        val temp = ArrayList<Char>()
        var coded = ""
        for (c in decoded.toLowerCase().toCharArray()) {
            when {
                temp.size > 0 && c == temp.last() -> {
                    temp.add('x')
                    temp.add(c)
                }
                '0'..'9' contains c -> temp.add(c)
                'a'..'z' contains c -> temp.add(c)
            }
        }
        if (temp.size % 2 != 0) temp.add('x')
        run {
            for (i in temp.indices step 2) {
                val (c1, r1) = key[temp[i]]!!
                val (c2, r2) = key[temp[i + 1]]!!
                temp.removeAt(i)
                temp.removeAt(i)
                when {
                    r1 == r2 -> {
                        temp.add(i, key.getKey(Coords((c1 + 1) % 6, r1))!!)
                        temp.add(i + 1, key.getKey(Coords((c2 + 1) % 6, r2))!!)
                    }
                    c1 == c2 -> {
                        temp.add(i, key.getKey(Coords(c1, (r1 + 1) % 6))!!)
                        temp.add(i + 1, key.getKey(Coords(c2, (r2 + 1) % 6))!!)
                    }
                    else -> {
                        temp.add(i, key.getKey(Coords(c1, r2))!!)
                        temp.add(i + 1, key.getKey(Coords(c2, r1))!!)
                    }
                }
            }
        }
        for (c in temp) {
            coded += Character.toLowerCase(c)
        }
        return coded
    }

    fun decode(coded: String): String {
        val temp = ArrayList<Char>()
        var decoded = ""
        for (c in coded.toLowerCase().toCharArray())
            temp.add(c)
        run {
            for (i in temp.indices step 2) {
                val (c1, r1) = key[temp[i]]!!
                val (c2, r2) = key[temp[i + 1]]!!
                temp.removeAt(i)
                temp.removeAt(i)
                when {
                    r1 == r2 -> {
                        temp.add(i, key.getKey(Coords((c1 + 5) % 6, r1))!!)
                        temp.add(i + 1, key.getKey(Coords((c2 + 5) % 6, r2))!!)
                    }
                    c1 == c2 -> {
                        temp.add(i, key.getKey(Coords(c1, (r1 + 5) % 6))!!)
                        temp.add(i + 1, key.getKey(Coords(c2, (r2 + 5) % 6))!!)
                    }
                    else -> {
                        temp.add(i, key.getKey(Coords(c1, r2))!!)
                        temp.add(i + 1, key.getKey(Coords(c2, r1))!!)
                    }
                }
            }
        }
        for (i in temp.indices) {
            if (temp[i] == 'x' && (i > 0 && i < temp.size - 1 && temp[i - 1] == temp[i + 1] || i == temp.size - 1))
            else
                decoded += temp[i]
        }
        return decoded
    }

    fun printMatrix(): String{
        val table = Array(6) { CharArray(6) }
        for((c,r) in key.values)
            table[c][r]=key.getKey(Coords(c,r))!!
        val builder = StringBuilder()
        for(a in table){
            for(c in a)
                builder.append("$c, ")
            builder.append("\n")
        }
        return builder.toString()
    }
}