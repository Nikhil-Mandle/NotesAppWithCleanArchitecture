package com.nikhilproject.notesappcleanarchitecture.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nikhilproject.notesappcleanarchitecture.domain.model.Note
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {

    val uiState by viewModel.uiState.collectAsState()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val editNote = remember { mutableStateOf(Note(-1, "", "")) }

    val isEdit = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        modifier = Modifier
            .safeContentPadding()
            .navigationBarsPadding(),
        sheetContent = {
            Form(note = editNote.value) { title, desc ->
                if (isEdit.value) {
                    val note = Note(editNote.value.id, title, desc)
                    viewModel.update(note)
                } else {
                    val note = Note(id = 0, title = title, desc = desc)
                    viewModel.insert(note)
                }
                scope.launch {
                    sheetState.hide()
                }
            }
        }, sheetState = sheetState,
        sheetShape = MaterialTheme.shapes.large,
        sheetElevation = 4.dp
    ) {
        Scaffold(
            modifier = Modifier
                .navigationBarsPadding(),
            topBar = {
                TopAppBar(title = {
                    Text(text = "Notes App")
                }, actions = {
                    IconButton(onClick = {
                        scope.launch {
                            sheetState.show()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                    }
                })
            }) {
            if (uiState.data.isEmpty()) {
                Box(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(text = "Nothing Found")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                ) {
                    items(uiState.data) {
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    isEdit.value = true
                                    editNote.value = it
                                    scope.launch {
                                        sheetState.show()
                                    }
                                }) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = it.title,
                                        style = MaterialTheme.typography.headlineSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Red
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = it.desc,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Cyan
                                    )
                                }

                                IconButton(onClick = {
                                    viewModel.delete(it)
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "delete"
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }


    }
}

@Composable
fun Form(note: Note, onClick: (String, String) -> Unit) {
    val title = remember { mutableStateOf("") }
    val desc = remember { mutableStateOf("") }

    LaunchedEffect(key1 = note.id != -1) {
        title.value = note.title
        desc.value = note.desc
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = title.value, onValueChange = {
            title.value = it
        }, singleLine = true, placeholder = {
            Text(text = "Title", color = Color.Black)
        },
            textStyle = TextStyle(color = Color.Black)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = desc.value, onValueChange = {
            desc.value = it
        }, singleLine = true, placeholder = {
            Text(text = "Description", color = Color.Black)
        },
            textStyle = TextStyle(color = Color.Black)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            colors = ButtonColors(
                containerColor = Color.Green,
                contentColor = Color.Blue,
                disabledContainerColor = Color.Red,
                disabledContentColor = Color.Blue
            ),
            onClick = {
                onClick.invoke(title.value, desc.value)
            }) {
            Text(text = "Save", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}