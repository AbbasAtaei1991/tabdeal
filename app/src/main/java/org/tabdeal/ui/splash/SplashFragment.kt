package org.tabdeal.ui.splash

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.BuildCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import org.tabdeal.BuildConfig
import org.tabdeal.R
import org.tabdeal.data.model.Status
import org.tabdeal.data.model.VersionDetail
import org.tabdeal.databinding.FragmentSplashBinding
import org.tabdeal.utils.DialogListener
import org.tabdeal.utils.UpdateDialog
import timber.log.Timber

@AndroidEntryPoint
class SplashFragment : Fragment(), DialogListener {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkForUpdate()
    }

    private fun openMainFragment() {
        lifecycleScope.launchWhenCreated {
            delay(2000)
            findNavController().navigate(R.id.mainFragment)
        }
    }

    private fun checkForUpdate() {
        viewModel.getUserProfile().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    val appVersion = BuildConfig.VERSION_NAME
                    val lastVersion = it.data!!.allVersions!![1].version
                    if (appVersion != lastVersion) {
                        var isForce = false
                        if (appVersion.take(1) != lastVersion!!.take(1)) isForce = true
                        val dialog = UpdateDialog.newInstance(isForce)
                        dialog.isCancelable = false
                        dialog.show(childFragmentManager, dialog.tag)
                    } else {
                        openMainFragment()
                    }
                }
                Status.ERROR -> {
                    binding.animEmpty.visibility = View.INVISIBLE
                }
                Status.LOADING -> {
                    binding.animEmpty.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onConfirm() {
        binding.animEmpty.cancelAnimation()
        val packageId = BuildConfig.APPLICATION_ID
        val i = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageId"))
        try {
            startActivity(i)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCancel() {
        binding.animEmpty.cancelAnimation()
        findNavController().navigate(R.id.mainFragment)
    }

    override fun onDismiss() {

    }
}