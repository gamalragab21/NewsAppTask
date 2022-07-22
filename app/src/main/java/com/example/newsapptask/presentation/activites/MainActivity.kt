package com.example.newsapptask.presentation.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavInflater
import androidx.navigation.fragment.NavHostFragment
import com.example.newsapptask.R
import com.example.newsapptask.common.NewsIds
import com.example.newsapptask.common.utils.UICommunicationListener
import com.example.newsapptask.data.cache.ComplexPreferences
import com.example.newsapptask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),UICommunicationListener{
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var inflater: NavInflater
    private lateinit var graph: NavGraph

    @Inject
    lateinit var complexPreferences: ComplexPreferences
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment =
            supportFragmentManager.findFragmentById(NewsIds.nav_host_fragment) as NavHostFragment
        inflater = navHostFragment.navController.navInflater
        graph = inflater.inflate(R.navigation.navigation_graph)
        visibilityNavElements(navHostFragment.navController)
        navHostFragment.navController.setGraph(graph)
        onNewIntent(intent)
    }

    private fun visibilityNavElements(navController: NavController) {
        graph.startDestination = NewsIds.mainFragment
        navController.addOnDestinationChangedListener { _, destination, _ ->

//            when (destination.id) {
//                NewsIds.loginFragment,
//                NewsIds.registerFragment,
//                -> {
//                    binding.bottomNav.visibility = View.GONE
//                }
//                else -> {
//                    binding.bottomNav.visibility = View.VISIBLE
//                }
//            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }


    override fun showProgressBar(isLoading: Boolean) {
        binding.layoutProgressDialog.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}