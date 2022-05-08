package dev.imam.newsapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val NEWS_KEY = "data"
private const val DETAIL_ID = "id"

class DetailFragment:Fragment(R.layout.fragment_detail) {
    var newsId:String? = null
    companion object{
        fun newInstance(news: News):DetailFragment {
            val args = Bundle().apply {
                putSerializable(NEWS_KEY, news)
                putString(DETAIL_ID, news.id)
            }
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val news = arguments?.getSerializable(NEWS_KEY) as News
        newsId = arguments?.getString(DETAIL_ID)

        view.findViewById<TextView>(R.id.header).text = news.header
        view.findViewById<TextView>(R.id.author).text = news.author
        view.findViewById<TextView>(R.id.detail).text = news.detail
    }
}