package com.flaviokreis.mvvmstarwars

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.flaviokreis.datasource.films.FilmRepository
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val repository: FilmRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StarWarsApp(repository)
        }
    }
}