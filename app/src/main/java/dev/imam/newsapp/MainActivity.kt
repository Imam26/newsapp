package dev.imam.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.newsContainer, NewsFragment.newInstance(Data.news), "NewsFragment")
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val iLeftMenu: Fragment? = supportFragmentManager.findFragmentById(R.id.newsContainer)
        val iContent: Fragment? = supportFragmentManager.findFragmentById(R.id.detailContainer)

        if(iLeftMenu is ILeftMenu && iContent is IContent<*>) {
            iLeftMenu.changeItemStyleAfterClicked(Data.news.indexOf(iContent.data))
        } else if (iLeftMenu is ILeftMenu){
            iLeftMenu.changeItemStyleAfterContentIsEmpty()
        }
    }
}