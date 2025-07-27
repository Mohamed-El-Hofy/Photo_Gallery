package com.more9810.photogallery.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.more9810.photogallery.databinding.FragmentHomePhotoBinding
import com.more9810.photogallery.presentation.adapters.PhotoRecyclerAdapter
import com.more9810.photogallery.presentation.viewModel.PexelsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePhotoFragment : Fragment() {
    private var _binding: FragmentHomePhotoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PexelsViewModel by viewModels()
    private lateinit var adapter: PhotoRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomePhotoBinding.inflate(inflater, container, false)

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        binding.rvHom.layoutManager = layoutManager

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        adapter = PhotoRecyclerAdapter(emptyList())
        binding.rvHom.adapter = adapter

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}