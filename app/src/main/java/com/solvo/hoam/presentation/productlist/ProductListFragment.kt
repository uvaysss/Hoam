package com.solvo.hoam.presentation.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.distinctUntilChanged
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.solvo.hoam.App
import com.solvo.hoam.R
import com.solvo.hoam.data.network.ProductResponse
import com.solvo.hoam.databinding.FragmentProductListBinding
import com.solvo.hoam.databinding.ListItemBinding
import com.solvo.hoam.presentation.core.AppViewModelFactory
import com.solvo.hoam.presentation.core.navigation.FragmentStackHost
import com.solvo.hoam.presentation.main.MainNavigation
import com.solvo.hoam.presentation.product.ProductKey
import com.solvo.hoam.presentation.ui.helper.AdHelper
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentKey
import com.zhuinden.simplestackextensions.fragments.KeyedFragment
import com.zhuinden.simplestackextensions.fragmentsktx.backstack
import com.zhuinden.simplestackextensions.fragmentsktx.lookup
import dev.chrisbanes.insetter.applySystemWindowInsetsToMargin
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

/**
 * Created by uvays on 24/01/2021.
 */

@Parcelize
data class ProductListKey(private val placeholder: Int = 0) : DefaultFragmentKey() {
    override fun instantiateFragment(): Fragment = ProductListFragment.newInstance()
}

class ProductListFragment : KeyedFragment() {

    companion object {
        fun newInstance(): Fragment = ProductListFragment()
    }

    @Inject lateinit var viewModelFactory: AppViewModelFactory
    private lateinit var viewModel: ProductListViewModel

    private lateinit var adapter: AsyncListDifferDelegationAdapter<ProductResponse>

    private val localStack by lazy {
        lookup<FragmentStackHost>(MainNavigation.AD.stackId).backstack
    }

    private var _binding: FragmentProductListBinding? = null
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
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.applySystemWindowInsetsToPadding(top = true)
        binding.progressBar.applySystemWindowInsetsToMargin(top = true)

        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent)
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false

            viewModel.onStart()
        }

        adapter = AsyncListDifferDelegationAdapter(
            object : DiffUtil.ItemCallback<ProductResponse>() {
                override fun areItemsTheSame(
                    oldItem: ProductResponse,
                    newItem: ProductResponse
                ): Boolean = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: ProductResponse,
                    newItem: ProductResponse
                ): Boolean = oldItem == newItem
            },
            createItemDelegate {  item ->
                backstack.goTo(ProductKey(item.id))
            }
        )

        binding.recyclerView.adapter = adapter
        binding.recyclerView.itemAnimator = null

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ProductListViewModel::class.java)

        bindViewModel()

        viewModel.onStart()
    }

    private fun createItemDelegate(onClick: (ProductResponse) -> Unit) =
        adapterDelegateViewBinding<ProductResponse, ProductResponse, ListItemBinding>(
            { inflater, parent -> ListItemBinding.inflate(inflater, parent, false) }
        ) {
            binding.root.setOnClickListener {
                onClick(item)
            }

            bind {
                binding.tvTitle.text = item.title

                binding.tvLocation.text = item.cityId.toString()
                binding.tvLocation.setCompoundDrawables(
                    AdHelper.getSupportDrawable(R.drawable.ic_location, context),
                    null,
                    null,
                    null
                )

                binding.tvCategory.text = item.categoryId.toString()
                binding.tvCategory.setCompoundDrawables(
                    AdHelper.getSupportDrawable(R.drawable.ic_category, context),
                    null,
                    null,
                    null
                )

                binding.tvPrice.text = AdHelper.getPrice(item.price ?: 0)
                binding.tvCreatedDate.text = AdHelper.getAdCreatedDate(item.createdAt, false)

                if (item.images.isEmpty()) {
                    binding.adImage.setImageDrawable(
                        AdHelper.getSupportDrawable(
                            R.drawable.ic_placeholder,
                            context
                        )
                    )
                } else {
                    Glide.with(context)
                        .load(AdHelper.getImageUrl(item.images.firstOrNull()?.small ?: ""))
                        .placeholder(
                            AdHelper.getSupportDrawable(
                                R.drawable.ic_placeholder,
                                context
                            )
                        )
                        .centerCrop()
                        .into(binding.adImage)
                }
            }
        }

    private fun bindViewModel() {
        viewModel.loadingLiveData.distinctUntilChanged()
            .observe(viewLifecycleOwner) { raw ->
                val value = raw ?: return@observe

                binding.progressBar.isVisible = value
            }

        viewModel.contentLiveData.distinctUntilChanged()
            .observe(viewLifecycleOwner) { raw ->
                val value = raw ?: return@observe

                binding.recyclerView.isVisible = value
            }

        viewModel.itemLiveData.distinctUntilChanged()
            .observe(viewLifecycleOwner) { raw ->
                val value = raw ?: return@observe

                adapter.items = value
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}