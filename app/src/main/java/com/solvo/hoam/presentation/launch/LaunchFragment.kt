package com.solvo.hoam.presentation.launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.distinctUntilChanged
import com.solvo.hoam.App
import com.solvo.hoam.R
import com.solvo.hoam.databinding.FragmentLaunchBinding
import com.solvo.hoam.presentation.core.AppViewModelFactory
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentKey
import com.zhuinden.simplestackextensions.fragments.KeyedFragment
import com.zhuinden.simplestackextensions.fragmentsktx.backstack
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

/**
 * Created by uvays on 30/01/2021.
 */

@Parcelize
data class LaunchKey(private val placeholder: Int = 0) : DefaultFragmentKey() {
    override fun instantiateFragment(): Fragment = LaunchFragment.newInstance()
}

class LaunchFragment : KeyedFragment(R.layout.fragment_launch) {

    companion object {
        fun newInstance(): Fragment = LaunchFragment()
    }

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private lateinit var viewModel: LaunchViewModel

    private var _binding: FragmentLaunchBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.getComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaunchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false

            viewModel.onRefresh()
        }

        viewModel = ViewModelProvider(this, viewModelFactory).get(LaunchViewModel::class.java)

        bindViewModel()

        viewModel.onStart(backstack)
    }

    private fun bindViewModel() {
        viewModel.refreshEnabledLiveData.distinctUntilChanged()
            .observe(viewLifecycleOwner) { raw ->
                val value = raw ?: return@observe

                binding.swipeRefreshLayout.isEnabled = value
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}