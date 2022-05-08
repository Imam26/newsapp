package dev.imam.newsapp

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val NEWS_ARRAY_KEY = "news"

class NewsFragment: Fragment(R.layout.fragment_news), ILeftMenu {
    private var newsViewList: List<TextView> = listOf()
    private var data: Array<News> = arrayOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        data = arguments?.getSerializable(NEWS_ARRAY_KEY) as Array<News>

        initTextViewList(view)

        for((index, item) in data.withIndex()){
            newsViewList[index].setOnClickListener {
                val fragment: Fragment? = parentFragmentManager.findFragmentById(R.id.detailContainer)
                if(fragment is IContent<*> && fragment.data == item) {
                    return@setOnClickListener
                }
                replaceDetailFragment(item)
                changeItemStyleAfterClicked(index)
            }
        }

        view.findViewById<Button>(R.id.next).setOnClickListener {
            onClickNavButtons(1){ index -> index != -1 && index < data.size - 1}
        }

        view.findViewById<Button>(R.id.prev).setOnClickListener {
            onClickNavButtons(-1){index -> index > 0}
        }
    }

    override fun changeItemStyleAfterClicked(itemIndex: Int)   {
        if(itemIndex < 0 || itemIndex >= newsViewList.size) return
        val clickedItem = newsViewList[itemIndex]
        clickedItem.setTypeface(null, Typeface.BOLD)
        for((index, item) in newsViewList.withIndex()){
            if(index != itemIndex ){
                item.setTypeface(null, Typeface.NORMAL);
            }
        }
    }

    override fun changeItemStyleAfterContentIsEmpty(){
        for(item in newsViewList){
            item.setTypeface(null, Typeface.NORMAL);
        }
    }

    private fun onClickNavButtons(step:Int, predicate: (Int) -> Boolean){
        val fragment: Fragment? = parentFragmentManager.findFragmentById(R.id.detailContainer)
        if(fragment is IContent<*>) {
            val index = data.indexOf(fragment.data)
            if(predicate(index)){
                replaceDetailFragment(data[index+step])
                changeItemStyleAfterClicked(index+step)
            }
        }
    }

    private fun replaceDetailFragment(news: News) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.detailContainer, DetailFragment.newInstance(news), "DetailFragment{${news.id}")
            .addToBackStack(null)
            .commit()
    }

    private fun initTextViewList(view: View) {
        val itemsId = arrayOf(R.id.first, R.id.second, R.id.third, R.id.forth, R.id.fifth, R.id.sixth)
        for ((index, item) in data.withIndex()){
            val textView = view.findViewById<TextView>(itemsId[index])
            textView.text = item.header
            newsViewList += textView

        }
    }

    companion object {
        fun newInstance(newsArray: Array<News>) = NewsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(NEWS_ARRAY_KEY, newsArray)
            }
        }
    }
}