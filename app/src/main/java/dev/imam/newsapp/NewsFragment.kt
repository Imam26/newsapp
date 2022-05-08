package dev.imam.newsapp

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class NewsFragment:Fragment(R.layout.fragment_news) {
    private var newsViewList:List<TextView> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNewsViewList(view)

        for((index, item) in Data.news.withIndex()){
            val textView = newsViewList[index]
            textView.setOnClickListener {
                var transaction = parentFragmentManager.beginTransaction()
                    .replace(R.id.detailContainer, DetailFragment.newInstance(item), "DetailFragment{$item.id}")

                val fragment: Fragment? = parentFragmentManager.findFragmentById(R.id.detailContainer)
                if(fragment is DetailFragment && fragment.newsId != item.id) {
                    transaction.addToBackStack(null)
                }

                transaction.commit()
                changeTextStyleAfterItemClicked(it as TextView, view)
            }
        }

        view.findViewById<Button>(R.id.next).setOnClickListener {
            val fragment: Fragment? = parentFragmentManager.findFragmentById(R.id.detailContainer)
            if(fragment is DetailFragment) {
                val index = Data.news.indexOf(Data.news.find { it -> it.id == fragment.newsId })
                if(index < Data.news.size - 1){
                    var transaction = parentFragmentManager.beginTransaction()
                        .replace(
                            R.id.detailContainer,
                            DetailFragment.newInstance(Data.news[index+1]),
                            "DetailFragment{${Data.news[index+1].id}"
                        )
                    transaction.addToBackStack(null)
                    transaction.commit()
                    changeTextStyleAfterItemClicked(newsViewList[index+1], view)
                }
            }
        }

        view.findViewById<Button>(R.id.prev).setOnClickListener {
            val fragment: Fragment? = parentFragmentManager.findFragmentById(R.id.detailContainer)
            if(fragment is DetailFragment) {
                val index = Data.news.indexOf(Data.news.find { it -> it.id == fragment.newsId })
                if(index > 0){
                    var transaction = parentFragmentManager.beginTransaction()
                        .replace(
                            R.id.detailContainer,
                            DetailFragment.newInstance(Data.news[index-1]),
                            "DetailFragment{${Data.news[index-1].id}"
                        )
                    transaction.addToBackStack(null)
                    transaction.commit()
                    changeTextStyleAfterItemClicked(newsViewList[index-1], view)
                }
            }
        }
    }

    private fun initNewsViewList(view:View){
        for (item in Data.news){
            val textView = view.findViewById<TextView>(getViewId(item.id))
            textView.text = item.header
            newsViewList += textView

        }
    }

    private fun getViewId(strId: String) : Int{
        return resources.getIdentifier(strId, "id", context?.packageName)
    }

    private fun changeTextStyleAfterItemClicked(clickedItem:TextView, view:View){
        clickedItem.setTypeface(null, Typeface.BOLD);
        for(item in newsViewList){
            if(item.id != clickedItem.id ){
                item.setTypeface(null, Typeface.NORMAL);
            }
        }
    }
}