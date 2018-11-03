package com.android.exerciseapp.presentation.podcastlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.exerciseapp.R
import com.android.exerciseapp.databinding.FragmentPodcastListBinding
import com.android.exerciseapp.di.androidx.AndroidXInjection
import com.android.exerciseapp.presentation.ViewModelFactory
import com.android.exerciseapp.presentation.common.Result
import com.android.exerciseapp.presentation.podcastlist.PodcastListFragmentDirections.actionPodcastListFragmentToPlayingFragement
import com.android.exerciseapp.util.recyclerview.setLinearDivider
import javax.inject.Inject

/**
 * Created by JasonYang.
 */
class PodcastListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: PodcastListViewModel by lazy {
        ViewModelProviders
            .of(this, viewModelFactory)
            .get(PodcastListViewModel::class.java)
    }

    private lateinit var viewDataBinding: FragmentPodcastListBinding
    private lateinit var listAdapter: PodcastsAdapter

    override fun onAttach(context: Context?) {
        AndroidXInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentPodcastListBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListView()
    }

    private fun setupListView() {
        viewDataBinding.placeList.apply {
            listAdapter = PodcastsAdapter()
            adapter = listAdapter
            listAdapter.onItemClick = { path ->
                if (path is String) {
                    val navDirections = actionPodcastListFragmentToPlayingFragement(path)
                    view?.run { Navigation.findNavController(this).navigate(navDirections) }
                }
            }

            val lm = LinearLayoutManager(context)
            layoutManager = lm
            setLinearDivider(R.drawable.shape_divider_1dp_line, lm)
        }

        viewModel.podcasts.observe(this@PodcastListFragment, Observer { result ->
            if (result is Result.Success) listAdapter.items = result.data
        })
    }

    override fun onStart() {
        super.onStart()

        viewModel.getAssetFileList()
    }

}