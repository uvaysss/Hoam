package com.solvo.hoam.presentation.core.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.solvo.hoam.R
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentStateChanger

/**
 * Created by uvays on 14/12/2020.
 */

class FadeAnimationFragmentStateChanger(
    fragmentManager: FragmentManager,
    @IdRes containerId: Int
) : DefaultFragmentStateChanger(fragmentManager, containerId) {

    override fun onForwardNavigation(
        fragmentTransaction: FragmentTransaction,
        stateChange: StateChange
    ) {
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.slide_out
        )
    }

    override fun onBackwardNavigation(
        fragmentTransaction: FragmentTransaction,
        stateChange: StateChange
    ) {
        fragmentTransaction.setCustomAnimations(
            R.anim.fade_in,
            R.anim.slide_out,
            R.anim.fade_out,
            R.anim.fade_out
        )
    }

    override fun onReplaceNavigation(
        fragmentTransaction: FragmentTransaction,
        stateChange: StateChange
    ) {
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
    }

    override fun isNotShowing(fragment: Fragment): Boolean = fragment.isHidden

    override fun startShowing(fragmentTransaction: FragmentTransaction, fragment: Fragment) {
        fragmentTransaction.show(fragment)
    }

    override fun stopShowing(fragmentTransaction: FragmentTransaction, fragment: Fragment) {
        fragmentTransaction.hide(fragment)
    }
}