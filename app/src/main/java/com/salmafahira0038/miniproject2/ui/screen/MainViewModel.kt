package com.salmafahira0038.miniproject2.ui.screen

import androidx.lifecycle.ViewModel
import com.salmafahira0038.miniproject2.model.Film

class MainViewModel : ViewModel(){
    val data = listOf(
        Film(
            1,
            "Avatar",
            "Film dengan pendapatan box office tertinggi sepanjang masa.",
            "2009"
        ),
        Film(
            2,
            "Avengers: Endgame",
            "Film superhero yang juga sangat populer.",
            "2019"
        ),
        Film(
            3,
            "Avatar: The Way of Water ",
            "Sekuel dari Avatar yang juga menjadi box office hit.",
            "2022"
        ),
        Film(
            4,
            "Titanic",
            "Film romantis klasik yang sangat populer.",
            "1997"
        ),
        Film(
            5,
            "Star Wars: The Force Awakens",
            "Film dari seri Star Wars yang menjadi sukses besar.",
            "2015"
        ),
        Film(
            6,
            "Jurassic World",
            "Film petualangan dinosaurus yang sukses besar",
            "2015"
        ),
        Film(
            7,
            "The Lion King",
            "Remake Disney yang laris manis secara global.",
            "2019"
        ),
        Film(
            8,
            "Furious 7",
            "Aksi balapan penuh emosi dan penghormatan untuk Paul Walker.",
            "2015"
        ),
        Film(
            9,
            " Frozen II ",
            "Sekuel animasi populer dengan lagu-lagu hits.",
            "2019"
        ),
        Film(
            10,
            "Avengers: Infinity War",
            "Pertarungan epik para pahlawan Marvel melawan Thanos.",
            "2018"
        ),
        )
}