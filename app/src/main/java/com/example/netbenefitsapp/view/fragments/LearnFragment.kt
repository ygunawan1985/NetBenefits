package com.example.netbenefitsapp.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.model.Article
import com.example.netbenefitsapp.model.Video
import com.example.netbenefitsapp.view.adapters.LearningAdapter
import com.example.netbenefitsapp.view.adapters.VideoAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LearnFragment : Fragment() {

    private var articles : ArrayList<Article> = ArrayList()
    private var videos : ArrayList<Video> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            articles = it.getParcelableArrayList(ARG_PARAM1)!!
            videos = it.getParcelableArrayList(ARG_PARAM2)!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learn, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvArticles : RecyclerView = view.findViewById(R.id.rvArticles)
        rvArticles.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        val learningAdapter = LearningAdapter(articles)
        rvArticles.adapter = learningAdapter

        val rvVideos : RecyclerView = view.findViewById(R.id.rvVideos)
        rvVideos.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        val videoAdapter = VideoAdapter(videos)
        rvVideos.adapter = videoAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: ArrayList<Article>, param2: ArrayList<Video>) =
            LearnFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM1, param1)
                    putParcelableArrayList(ARG_PARAM2, param2)
                }
            }
    }
}
