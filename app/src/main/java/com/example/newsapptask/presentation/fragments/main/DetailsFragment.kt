package com.example.newsapptask.presentation.fragments.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.example.newsapptask.common.NewsIds
import com.example.newsapptask.common.utils.BaseFragment
import com.example.newsapptask.common.utils.navigateSafely
import com.example.newsapptask.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment :BaseFragment<FragmentDetailsBinding>(){

    private val args:DetailsFragmentArgs by navArgs()
    @Inject lateinit var glide:RequestManager
    override fun getViewBinding(): FragmentDetailsBinding =  FragmentDetailsBinding.inflate(layoutInflater)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item=args.item
        glide.load(item.thumbnailStandard).into(binding.imageHolder)
        binding.titleValueTv.text=item.slugName
        binding.publishedByValueTv.text=item.source
        item.desFacet?.forEach {
            binding.ratingValueTv.text= "${binding.ratingValueTv.text} $it -"
        }
        binding.summaryValueTv.text=item.abstract?:"Unknown"
        binding.dateValueTv.text = item.publishedDate.substringBefore("T")

        binding.imageHolder.setOnClickListener {
            item.thumbnailStandard?.let {
                val bundle = bundleOf("imageUrl" to it )
                findNavController().navigateSafely(NewsIds.action_detailsFragment_to_imageViewerFragment,bundle)
            }
        }


    }
    override fun subscribeToObservables() {

    }
}