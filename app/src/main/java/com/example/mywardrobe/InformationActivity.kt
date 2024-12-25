package com.example.mywardrobe


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InformationActivity : AppCompatActivity() {
    lateinit var mainLL: LinearLayout
    lateinit var nameTV: TextView
    lateinit var descriptionTV: TextView
    lateinit var imageIV: ImageView
    lateinit var mainTB: Toolbar
    lateinit var backBTN: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_information)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLL)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        nameTV = findViewById(R.id.nameTV)
        mainLL = findViewById(R.id.mainLL)
        descriptionTV = findViewById(R.id.descriptionTV)
        mainTB = findViewById(R.id.mainTB)
        imageIV = findViewById(R.id.imageIV)
        backBTN = findViewById(R.id.backBTN)
        setSupportActionBar(mainTB)
        title = "Мой гардероб"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mainTB.setNavigationOnClickListener {
            onBackPressed()
        }
        var item: WardrobeItem? = null
        if (intent.hasExtra("item")) {
            item = intent.getSerializableExtra("item") as WardrobeItem
        }
        if (item != null) {
            nameTV.text = item.name
            descriptionTV.text = item.description
            var itemImage = item.imageResId
            imageIV.setImageResource(itemImage)
        }
        mainLL.setOnLongClickListener {
            val dialog = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.update_dialog, null)
            dialog.setView(dialogView)
            val editName = dialogView.findViewById<EditText>(R.id.nameET)
            val editDescription = dialogView.findViewById<EditText>(R.id.descriptionET)
            editName.setText(nameTV.text)
            editDescription.setText(descriptionTV.text)
            dialog.setTitle("Обновить запись")
            dialog.setMessage("Введите данные ниже:")
            dialog.setPositiveButton("Обновить") { _, _ ->
                nameTV.text = editName.text.toString()
                descriptionTV.text = editDescription.text.toString()
            }

            dialog.setNegativeButton("Отмена", null)
            dialog.create().show()
            false
        }
        backBTN.setOnClickListener {
            val intent=Intent(this,WardrobeActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.exitApp -> {
                finishAffinity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
