package com.solvo.hoam.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.solvo.hoam.databinding.FragmentFavoriteBinding
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by uvays on 24/01/2021.
 */

@Parcelize
data class FavoriteKey(private val placeholder: Int = 0) : DefaultFragmentKey() {
    override fun instantiateFragment(): Fragment = FavoriteFragment.newInstance()
}

class FavoriteFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment = FavoriteFragment()
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}