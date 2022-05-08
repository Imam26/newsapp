package dev.imam.newsapp

import android.view.View

interface ILeftMenu {
    fun changeItemStyleAfterClicked(itemIndex: Int)
    fun changeItemStyleAfterContentIsEmpty()
}