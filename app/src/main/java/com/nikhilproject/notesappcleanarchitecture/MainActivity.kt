package com.nikhilproject.notesappcleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikhilproject.notesappcleanarchitecture.presentation.MainScreen
import com.nikhilproject.notesappcleanarchitecture.presentation.MainViewModel
import com.nikhilproject.notesappcleanarchitecture.ui.theme.NotesAppCleanArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppCleanArchitectureTheme {

                val viewModel = hiltViewModel<MainViewModel>()
                MainScreen(viewModel = viewModel)

            }
        }
    }
}

