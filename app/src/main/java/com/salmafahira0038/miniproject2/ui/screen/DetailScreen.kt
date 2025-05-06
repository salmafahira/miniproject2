package com.salmafahira0038.miniproject2.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salmafahira0038.miniproject2.R
import com.salmafahira0038.miniproject2.ui.theme.MiniProject2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(){
    var judul by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var tahunRilis by remember { mutableStateOf("") }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.tambah_film))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ){  padding ->
        FormFilm(
            title = judul,
            onTitleChange = {judul = it},
            desc = deskripsi,
            onDescChange = {deskripsi = it},
            year = tahunRilis,
            onYearChange = {tahunRilis = it} ,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormFilm(title: String, onTitleChange: (String) -> Unit,
             desc: String, onDescChange: (String) -> Unit,
             year: String, onYearChange: (String) -> Unit,
             modifier: Modifier) {
    Column (
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        OutlinedTextField(
            value = title,
            onValueChange = {onTitleChange(it)},
            label = { Text(text = stringResource(R.string.judul)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = desc,
            onValueChange = {onDescChange(it)},
            label = { Text(text = stringResource(R.string.deskripsi)) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = year,
            onValueChange = onYearChange,
            label = { Text(text = stringResource(R.string.tahun_rilis)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    MiniProject2Theme {
        DetailScreen()
    }
}