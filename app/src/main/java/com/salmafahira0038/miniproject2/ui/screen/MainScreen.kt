package com.salmafahira0038.miniproject2.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.salmafahira0038.miniproject2.R
import com.salmafahira0038.miniproject2.model.Film
import com.salmafahira0038.miniproject2.navigation.Screen
import com.salmafahira0038.miniproject2.ui.theme.MiniProject2Theme
import com.salmafahira0038.miniproject2.util.SettingsDataStore
import com.salmafahira0038.miniproject2.util.ViewModelFactory
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    val dataStore = remember { SettingsDataStore(context) }
    val layoutFlow by dataStore.layoutFlow.collectAsState(initial = true)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {

                    IconButton(onClick = {
                        navController.navigate(Screen.RecycleBin.route)
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_restore_from_trash_24),
                            contentDescription = stringResource(R.string.sampah),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    IconButton(onClick = {
                        scope.launch {
                            dataStore.saveLayout(!layoutFlow)
                        }
                    }) {
                        Icon(
                            painter = painterResource(
                                if (!layoutFlow) R.drawable.baseline_grid_view_24
                                else R.drawable.baseline_view_list_24
                            ),
                            contentDescription = stringResource(
                                if (layoutFlow) R.string.grid
                                else R.string.list
                            ),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.FormBaru.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.tambah_film),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ){ innerPadding ->
        ScreenContent(showList = layoutFlow, modifier = Modifier.padding(innerPadding), navController = navController, snackbarHostState = snackbarHostState)
    }
}


@Composable
fun ScreenContent(showList: Boolean, modifier: Modifier = Modifier, navController: NavHostController, snackbarHostState: SnackbarHostState) {
    val context = LocalContext.current
    val viewModel: MainViewModel = viewModel(factory = ViewModelFactory(context))
    val detailViewModel: DetailViewModel = viewModel(factory = ViewModelFactory(context))
    val data by viewModel.data.collectAsState()
    val scope = rememberCoroutineScope()
    val openDialog = remember { mutableStateOf(false) }
    val selectedFilm = remember { mutableStateOf<Film?>(null) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(stringResource(R.string.pesan_hapus)) },
            text = { Text(stringResource(R.string.hapus_list)) },
            confirmButton = {
                TextButton(onClick = {
                    selectedFilm.value?.let { film ->
                        detailViewModel.recentlyDeletedList = film
                        detailViewModel.delete(film.id)
                        scope.launch {
                            val result = snackbarHostState.showSnackbar(
                                message = context.getString(R.string.film_dihapus),
                                actionLabel = context.getString(R.string.undo),
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                detailViewModel.restoreDeletedFilm()
                            }
                        }
                    }

                    openDialog.value = false
                }) {
                    Text(stringResource(android.R.string.ok))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    openDialog.value = false
                }) {
                    Text(stringResource(android.R.string.cancel))
                }
            }
        )
    }

    if (data.isEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.list_kosong))
        }
    }
    else {
        if (showList) {
            LazyColumn (
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 84.dp)
            ){
                items(data) { film ->
                    ListItem(
                        film = film,
                        onClick = {
                            navController.navigate(Screen.FormUbah.withId(film.id))
                        },
                        onDelete = {
                            selectedFilm.value = it
                            openDialog.value = true
                        }
                    )
                    HorizontalDivider()
                }
            }
        }
        else {
            LazyVerticalStaggeredGrid(
                modifier = modifier.fillMaxSize(),
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp, 8.dp, 8.dp, 84.dp)
            ) {
                items(data) { film ->
                    GridItem(
                        film = film,
                        onClick = {
                            navController.navigate(Screen.FormUbah.withId(film.id))
                        },
                        onDelete = {
                            selectedFilm.value = it
                            openDialog.value = true
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ListItem(film: Film, onClick: () -> Unit, onDelete: (Film) -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text(
            text = film.judul,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = film.deskripsi,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = film.tahunRilis,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        IconButton(onClick = { onDelete(film)}) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.hapus)
            )
        }
    }
}

@Composable
fun GridItem(film: Film, onClick: () -> Unit, onDelete: (Film) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, DividerDefaults.color)
    ) {
        Column (
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ){
            Text(
                text = film.judul,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = film.deskripsi,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = film.tahunRilis,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                IconButton(onClick = { onDelete(film)}) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = stringResource(id = R.string.hapus)
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    MiniProject2Theme {
        MainScreen(rememberNavController())
    }
}