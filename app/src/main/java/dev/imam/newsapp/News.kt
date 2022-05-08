package dev.imam.newsapp

import java.io.Serializable

data class News (
    val id:Int,
    val header:String,
    val author:String,
    val detail:String
): Serializable

object Data {
    val news:Array<News> = arrayOf(
        News(1, "First News Header", "Author 1", "badbadgad"),
        News(2, "Second News Header", "Author 2", "sfdfsdgs"),
        News(3, "Third News Header", "Author 3", "badbadsgsdgad"),
        News(4, "Forth News Header", "Author 4", "sgsdgsgd"),
        News(5, "Fifth News Header", "Author 5", "2423tfdbsdv"),
        News(6, "Sixth News Header", "Author 6", "y53gfgdfbdf"),
    )
}