package com.example.playfair

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Encrypt : AppCompatActivity() {
    private var matrix: SquareKt? = null
    private val message = "najpierw podaj klucz"

    fun onClickCreate(view: View) {
        val keyword = findViewById<EditText>(R.id.keyword).text.toString()
        if (!keyword.isBlank()) matrix = SquareKt(keyword)
    }

    fun onClickCode(view: View) {
        val decoded = findViewById<EditText>(R.id.decoded)
        val coded = findViewById<EditText>(R.id.coded)
        if (matrix != null)
            coded.text = SpannableStringBuilder(matrix?.code(decoded?.text.toString()))
        else Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun onClickDecode(view: View) {
        val decoded = findViewById<EditText>(R.id.decoded)
        val coded = findViewById<EditText>(R.id.coded)
        if (matrix != null)
            decoded.text = SpannableStringBuilder(matrix?.decode(coded?.text.toString()))
        else Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun onClickShowMatrix(view: View) {
        val structure = findViewById<TextView>(R.id.structure)
        if (matrix != null)
            structure.text = matrix?.printMatrix()
        else Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        matrix = savedInstanceState?.get("matrix") as SquareKt?
        setContentView(R.layout.activity_main)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putSerializable("matrix", matrix)
    }

}
