package com.mak.navigationarchitecturedemo.ui.splash

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mak.navigationarchitecturedemo.R
import com.mak.navigationarchitecturedemo.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            if (onBoardingFinished() && onSigninFinished() && onPermissionFinished()) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            } else if (onBoardingFinished() && !onSigninFinished()) {
                findNavController().navigate(R.id.action_splashFragment_to_signinFragment)
            } else if (onBoardingFinished() && onSigninFinished() && !onPermissionFinished()) {
                findNavController().navigate(R.id.action_splashFragment_to_storageFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }
        }, 3000)
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

    private fun onSigninFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onSignin", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

    private fun onPermissionFinished(): Boolean {
        val sharedPref =
            requireActivity().getSharedPreferences("onPermission", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}