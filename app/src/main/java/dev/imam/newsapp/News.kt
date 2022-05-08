package dev.imam.newsapp

import java.io.Serializable

data class News (
    val id:String,
    val header:String,
    val author:String,
    val detail:String
): Serializable

object Data {
    val news:Array<News> = arrayOf(
        News("first", "First News Header", "Author", "badbadgad"),
        News("second", "Second News Header", "Author", "sfdfsdgs"),
        News("third", "Third News Header", "Author", "badbadsgsdgad"),
        News("forth", "Forth News Header", "Author", "sgsdgsgd"),
        News("fifth", "Fifth News Header", "Author", "2423tfdbsdv"),
        News("sixth", "Sixth News Header", "Author", "y53gfgdfbdf"),
    )
}