package com.flaviokreis.mvvmstarwars

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.flaviokreis.datasource.films.FilmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val repository: FilmRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getFilms()
            }

            result.collect { films ->
                findViewById<TextView>(R.id.text_view).text = films.joinToString { it.title }
            }
        }
    }
}