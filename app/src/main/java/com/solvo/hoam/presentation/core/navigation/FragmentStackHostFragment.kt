package com.solvo.hoam.presentation.core.navigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.solvo.hoam.R
import com.zhuinden.simplestack.SimpleStateChanger
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentStateChanger
import com.zhuinden.simplestackextensions.fragmentsktx.lookup
import timber.log.Timber

class FragmentStackHostFragment :
        Fragment(R.layout.fragment_stack_host),
        SimpleStateChanger.NavigationHandler {

    companion object {
        private const val ARG_STACK_HOST_ID = "ARG_STACK_HOST_ID"

        fun newInstance(stackHostId: String): FragmentStackHostFragment {
            val bundle = Bundle()
            bundle.putString(ARG_STACK_HOST_ID, stackHostId)

            val fragment = FragmentStackHostFragment()
            fragment.arguments = bundle

            return fragment
        }
    }

    private lateinit var stateChanger: DefaultFragmentStateChanger

    private val stackHost by lazy {
        lookup<FragmentStackHost>(requireArguments().getString(ARG_STACK_HOST_ID)!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        stateChanger = FadeAnimationFragmentStateChanger(childFragmentManager, R.id.viewRoot)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated")

        stackHost.backstack.setStateChanger(SimpleStateChanger(this))

        stackHost.backstack.reattachStateChanger()
        stackHost.isActiveForBack = true
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Timber.d("onHiddenChanged $hidden")

        if (hidden) {
            stackHost.isActiveForBack = false
            stackHost.backstack.detachStateChanger()
        } else {
            stackHost.backstack.reattachStateChanger()
            stackHost.isActiveForBack = true
        }
    }

//    override fun onResume() {
//        super.onResume()
//        Timber.d("onResume")
//
//        stackHost.backstack.reattachStateChanger()
//
//        stackHost.isActiveForBack = true
//    }
//
//    override fun onPause() {
//        Timber.d("onPause")
//
//        stackHost.isActiveForBack = false
//
//        stackHost.backstack.detachStateChanger()
//
//        super.onPause()
//    }

    override fun onDestroyView() {
        super.onDestroyView()

        stackHost.isActiveForBack = false
        stackHost.backstack.detachStateChanger()

        stackHost.backstack.executePendingStateChange()
    }

    override fun onNavigationEvent(stateChange: StateChange) {
        stateChanger.handleStateChange(stateChange)
    }
}