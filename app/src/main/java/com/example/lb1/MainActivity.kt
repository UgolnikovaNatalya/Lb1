
package com.example.lb1

import android.graphics.Color
import android.icu.text.ListFormatter.Width
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.print.PrintAttributes.Margins
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.marginEnd
import androidx.core.view.marginRight
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lb1.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var allList: List<StudTab>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var usList: List<StudTab>
        allList = listOf()

        val resView : RecyclerView = binding.recycler
        resView.layoutManager = LinearLayoutManager(this)

        var db = MainDb.getDb(this)

        //заполнение
        binding.btnFill.setOnClickListener {
            usList = listOf<StudTab>(
                StudTab(null, "Петр Иванов", 58, 187, 23),
                StudTab(null, "Андрей Расторгуев", 78, 150, 21),
                StudTab(null, "Николай Афельев", 90, 172, 27),
                StudTab(null, "Наталья Угольникова", 68, 170, 31),
                StudTab(null, "Светлана Богатырева", 66, 203, 32),
                StudTab(null, "Иван Петров", 72, 183, 25),
                StudTab(null, "Анна Степанова", 90, 210, 18)
            )
            usList.forEach {
                Thread {
                    db.getDao().insertStudent(it)
                    GlobalScope.launch {
                        allList = MainDb.getDb(this@MainActivity).getDao().getAllItem()
                    }
                }.start()
            }
        }

        //показ
        binding.btnShow.setOnClickListener {
            GlobalScope.launch {
                allList = MainDb.getDb(this@MainActivity).getDao().getAllItem()
            }
            resView.adapter = MyRecyclerAdapter(allList)
        }

        //удаление
        binding.btnClear.setOnClickListener {
            Thread {
                    db.getDao().clearTable()

                GlobalScope.launch {
                    allList = MainDb.getDb(this@MainActivity).getDao().getAllItem()
                }
                }.start()
            resView.adapter = MyRecyclerAdapter(allList)

        }
    }

}
