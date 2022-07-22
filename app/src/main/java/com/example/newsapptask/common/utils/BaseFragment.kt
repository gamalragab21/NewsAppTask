package com.example.newsapptask.common.utils

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.newsapptask.data.cache.ComplexPreferences
import timber.log.Timber
import javax.inject.Inject


abstract  class BaseFragment<Binding:ViewBinding>: Fragment() {
    private var _binding: Binding? = null
    val binding get() = _binding!!
    @Inject
    lateinit var complexPreferences: ComplexPreferences
     lateinit var uiCommunicationListener: UICommunicationListener


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = getViewBinding()
        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            uiCommunicationListener = context as UICommunicationListener
        } catch (e: ClassCastException) {
            Timber.e("onAttach:  $context must implement UICommunicationListener")
        }
    }

    override fun onStop() {
        super.onStop()
        uiCommunicationListener.showProgressBar(false)
    }

    abstract fun getViewBinding(): Binding

    abstract fun subscribeToObservables()
}