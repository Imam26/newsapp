package dev.imam.newsapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val NEWS_KEY = "data"

class DetailFragment: Fragment(R.layout.fragment_detail), IContent<News?> {
    override var data:News? = null

    companion object{
        fun newInstance(news: News) = DetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable(NEWS_KEY, news)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = arguments?.getSerializable(NEWS_KEY) as News

        view.findViewById<TextView>(R.id.header).text = data?.header
        view.findViewById<TextView>(R.id.author).text = data?.author
        view.findViewById<TextView>(R.id.detail).text = data?.detail
    }
}