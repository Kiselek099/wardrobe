package com.example.mywardrobe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WardrobeActivity : AppCompatActivity() {
    lateinit var wardrobeRV: RecyclerView
    lateinit var mainTB: Toolbar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_wardrobe)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mainTB = findViewById(R.id.mainTB)
        setSupportActionBar(mainTB)
        title = "Мой гардероб"
        wardrobeRV = findViewById(R.id.wardrobeRV)
        wardrobeRV.layoutManager = LinearLayoutManager(this)

        val items = getWardrobeItems()
        val adapter = WardrobeAdapter(items)
        wardrobeRV.adapter = adapter
        wardrobeRV.setHasFixedSize(true)
        adapter.setOnItemClickListener(object : WardrobeAdapter.OnItemClickListener {
            override fun onItemClick(wardrobeItem: WardrobeItem, position: Int) {
                val intent = Intent(this@WardrobeActivity, InformationActivity::class.java)
                intent.putExtra("item",wardrobeItem)
                startActivity(intent)
            }
        })
    }

    private fun getWardrobeItems(): List<WardrobeItem> {
        return listOf(
            WardrobeItem("Футболка", "Удобная летняя футболка", R.drawable.tshirt),
            WardrobeItem("Джинсы", "Классические джинсы", R.drawable.jeans),
            WardrobeItem("Куртка", "Теплая зимняя куртка", R.drawable.kurtka),
            WardrobeItem("Носки", "Практичные носки", R.drawable.noski),
            WardrobeItem("Туфли", "Удобные туфли", R.drawable.tufli),
            WardrobeItem("Шуба", "Меховая шуба", R.drawable.shooba),
            WardrobeItem("Рубашка", "Классическая рубашка", R.drawable.rubashka),
            WardrobeItem("Платье", "Вечернее платье", R.drawable.plate),
            WardrobeItem("Сапоги", "Осенние сапоги", R.drawable.sapogi),
            WardrobeItem("Костюм", "Классический костюм", R.drawable.kostum),
            WardrobeItem("Шапка", "Теплая шапка", R.drawable.shapka),
            WardrobeItem("Плавки", "Удобные плавки", R.drawable.plavki),
            WardrobeItem("Футболка", "Удобная летняя футболка", R.drawable.tshirt),
            WardrobeItem("Джинсы", "Классические джинсы", R.drawable.jeans),
            WardrobeItem("Футболка", "Удобная летняя футболка", R.drawable.tshirt),
            WardrobeItem("Джинсы", "Классические джинсы", R.drawable.jeans),
            WardrobeItem("Шуба", "Меховая шуба", R.drawable.shooba),
            WardrobeItem("Рубашка", "Классическая рубашка", R.drawable.rubashka),
            WardrobeItem("Платье", "Вечернее платье", R.drawable.plate),
            WardrobeItem("Сапоги", "Осенние сапоги", R.drawable.sapogi)
        )
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