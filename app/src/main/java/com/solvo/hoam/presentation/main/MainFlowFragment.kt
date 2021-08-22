package com.solvo.hoam.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import com.google.android.material.tabs.TabLayout
import com.solvo.hoam.App
import com.solvo.hoam.R
import com.solvo.hoam.databinding.FragmentMainFlowBinding
import com.solvo.hoam.presentation.about.AboutKey
import com.solvo.hoam.presentation.core.navigation.FragmentStackHost
import com.solvo.hoam.presentation.core.navigation.FragmentStackHostFragment
import com.solvo.hoam.presentation.favorite.FavoriteKey
import com.solvo.hoam.presentation.productlist.ProductListKey
import com.zhuinden.simplestack.ServiceBinder
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentKey
import com.zhuinden.simplestackextensions.fragments.KeyedFragment
import com.zhuinden.simplestackextensions.services.DefaultServiceProvider
import com.zhuinden.simplestackextensions.servicesktx.add
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
import kotlinx.android.parcel.Parcelize

/**
 * Created by uvays on 08/01/2021.
 */

@Parcelize
data class MainFlowKey(
    private val placeholder: Int = 0
) : DefaultFragmentKey(), DefaultServiceProvider.HasServices {

    override fun instantiateFragment(): Fragment = MainFlowFragment.newInstance()

    override fun getScopeTag(): String = javaClass.name

    override fun bindServices(serviceBinder: ServiceBinder) {
        with(serviceBinder) {
            add(FragmentStackHost(ProductListKey()), MainNavigation.AD.stackId)
            add(FragmentStackHost(FavoriteKey()), MainNavigation.FAVORITE.stackId)
            add(FragmentStackHost(AboutKey()), MainNavigation.ABOUT.stackId)
        }
    }
}

class MainFlowFragment : KeyedFragment() {

    companion object {
        private const val STATE_SELECTED_INDEX = "selectedIndex"

        fun newInstance(): Fragment = MainFlowFragment()
    }

    private var selectedIndex = 0

    private lateinit var firstFragment: FragmentStackHostFragment
    private lateinit var secondFragment: FragmentStackHostFragment
    private lateinit var thirdFragment: FragmentStackHostFragment

    private val fragments: Array<Fragment>
        get() = arrayOf(
            firstFragment,
            secondFragment,
            thirdFragment
        )

    private var _binding: FragmentMainFlowBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.getComponent().inject(this)

        if (savedInstanceState == null) {
            firstFragment = FragmentStackHostFragment.newInstance(MainNavigation.AD.stackId)
            secondFragment = FragmentStackHostFragment.newInstance(MainNavigation.FAVORITE.stackId)
            thirdFragment = FragmentStackHostFragment.newInstance(MainNavigation.ABOUT.stackId)

            childFragmentManager
                .beginTransaction()
                .add(R.id.viewRoot, firstFragment, MainNavigation.AD.stackId)
                .add(R.id.viewRoot, secondFragment, MainNavigation.FAVORITE.stackId)
                .add(R.id.viewRoot, thirdFragment, MainNavigation.ABOUT.stackId)
                .selectFragment(selectedIndex)
                .commit()
        } else {
            selectedIndex = savedInstanceState.getInt(STATE_SELECTED_INDEX, 0)

            firstFragment = childFragmentManager.findFragmentByTag(MainNavigation.AD.stackId)
                    as FragmentStackHostFragment

            secondFragment = childFragmentManager.findFragmentByTag(MainNavigation.FAVORITE.stackId)
                    as FragmentStackHostFragment

            thirdFragment = childFragmentManager.findFragmentByTag(MainNavigation.ABOUT.stackId)
                    as FragmentStackHostFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainFlowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewRoot.setOnApplyWindowInsetsListener { v, insets ->
            var consumed = false

            (v as ViewGroup).children.forEach { child ->
                val childResult = child.dispatchApplyWindowInsets(insets)
                if (childResult.isConsumed) {
                    consumed = true
                }
            }

            if (consumed) insets.consumeSystemWindowInsets() else insets
        }

        binding.tabLayout.applySystemWindowInsetsToPadding(bottom = true)
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {}
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabSelected(tab: TabLayout.Tab) {
                selectedIndex = MainNavigation.values()[tab.position].ordinal

                childFragmentManager
                    .beginTransaction()
                    .selectFragment(selectedIndex)
                    .commit()
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_SELECTED_INDEX, selectedIndex)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun FragmentTransaction.selectFragment(selectedIndex: Int): FragmentTransaction {
        fragments.forEachIndexed { index, fragment ->
            if (index == selectedIndex) {
                show(fragment)
            } else {
                hide(fragment)
            }
        }

        setMaxLifecycle(firstFragment, Lifecycle.State.STARTED)
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)

        return this
    }
}