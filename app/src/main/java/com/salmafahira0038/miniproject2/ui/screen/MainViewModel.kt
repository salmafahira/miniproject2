package com.salmafahira0038.miniproject2.ui.screen

import androidx.lifecycle.ViewModel
import com.salmafahira0038.miniproject2.model.Film

class MainViewModel : ViewModel(){
    val data = listOf(
        Film(
            1,
            "Black Widow",
            "Black Widow menceritakan Natasha Romanoff yang " +
                    "menghadapi masa lalunya dan berhadapan dengan organisasi " +
                    "Black Widow, sambil berusaha menghentikan ancaman global.",
            "9 Juli 2021"
        ),
        Film(
            2,
            "Shang-Chi and the Legend of the Ten Rings",
            "Ketika Shang-Chi ditarik ke dalam organisasi " +
                    "rahasia Sepuluh Cincin, ia dipaksa untuk menghadapi" +
                    " masa lalu yang ditinggalkannya.",
            "3 September 2021"
        ),
        Film(
            3,
            "Ethernals",
            "Film pahlawan super Amerika Serikat tahun 2021" +
                    "yang didasarkan dari karakter Marvel Comics bernama sama",
            "5 November 2021"
        ),
        Film(
            4,
            "Spider-Man: No Way Home",
            "Spider-Man: No Way Home mengisahkan kekacauan multiverse " +
                    "akibat upaya Peter Parker menghapus ingatan publik tentang identitasnya.",
            "15 Desember 2021"
        ),
        Film(
            5,
            "Avengers: Endgame",
            "Avengers: Endgame mengisahkan para Avengers berusaha " +
                    "membalikkan kehancuran akibat ulah Thanos dan menyelamatkan semesta.",
            "26 April 2019"
        ),
        Film(
            6,
            "Captain Marvel",
            "Captain Marvel mengisahkan pilot Carol Danvers yang mendapat" +
                    " kekuatan super dan terlibat dalam perang antar galaksi.",
            "6 Maret 2019"
        ),
        Film(
            7,
            "The Marvels",
            "The Marvels mengisahkan tiga pahlawan wanita yang kekuatannya" +
                    " saling terhubung dan harus bekerja sama menyelamatkan alam semesta.",
            "12 Oktober 2023"
        ),
        Film(
            8,
            "Captain America: Brave New World",
            "Captain America: Brave New World mengisahkan Sam Wilson menghadapi" +
                    " ancaman global besar dan konspirasi yang melibatkan teknologi berbahaya," +
                    " sambil berjuang menjaga nilai-nilai kepahlawan sebagai Captain America.",
            "14 Februari 2025"
        ),

        )
}