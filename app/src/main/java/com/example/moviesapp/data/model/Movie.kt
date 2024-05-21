package com.example.moviesapp.data.model

data class Movie(
    val id: Int,
    val name: String,
    val description: String,
    val runningTime: String,
    val thumbnail: String,
    val comments: MutableList<String> = mutableListOf()
)

object MockData {
    val movies = listOf(
        Movie(
            id = 1,
            name = "Inception",
            description = "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
            runningTime = "148 minutes",
            thumbnail = "https://link-to-thumbnail1.jpg"
        ),
        Movie(
            id = 2,
            name = "The Matrix",
            description = "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
            runningTime = "136 minutes",
            thumbnail = "https://link-to-thumbnail2.jpg"
        ),
        Movie(
            id = 3,
            name = "The Matrix2",
            description = "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
            runningTime = "136 minutes",
            thumbnail = "https://link-to-thumbnail2.jpg"
        ),
        Movie(
            id = 4,
            name = "The Matrix3",
            description = "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
            runningTime = "136 minutes",
            thumbnail = "https://link-to-thumbnail2.jpg"
        ),
        Movie(
            id = 5,
            name = "The Matrix4",
            description = "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
            runningTime = "136 minutes",
            thumbnail = "https://link-to-thumbnail2.jpg"
        )
    )
}