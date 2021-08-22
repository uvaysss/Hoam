package com.solvo.hoam.presentation.product

import android.annotation.SuppressLint
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
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.solvo.hoam.App
import com.solvo.hoam.R
import com.solvo.hoam.data.network.ProductImageResponse
import com.solvo.hoam.databinding.FragmentProductBinding
import com.solvo.hoam.databinding.ItemProductImageBinding
import com.solvo.hoam.presentation.core.AppViewModelFactory
import com.solvo.hoam.presentation.ui.helper.AdHelper
import com.stfalcon.imageviewer.StfalconImageViewer
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentKey
import com.zhuinden.simplestackextensions.fragments.KeyedFragment
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

/**
 * Created by uvays on 24/01/2021.
 */

@Parcelize
data class ProductKey(val id: Long) : DefaultFragmentKey() {
    override fun instantiateFragment(): Fragment = ProductFragment.newInstance()
}

class ProductFragment : KeyedFragment() {

    companion object {
        fun newInstance(): ProductFragment = ProductFragment()
    }

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private lateinit var viewModel: ProductViewModel

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private lateinit var imagesAdapter: AsyncListDifferDelegationAdapter<ProductImageResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.getComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.collapsingToolbarLayout.applySystemWindowInsetsToPadding(consume = false)
        binding.toolbar.applySystemWindowInsetsToPadding(top = true)

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false

            viewModel.onRefresh()
        }

        imagesAdapter = AsyncListDifferDelegationAdapter(
            object : DiffUtil.ItemCallback<ProductImageResponse>() {
                override fun areItemsTheSame(
                    oldItem: ProductImageResponse,
                    newItem: ProductImageResponse
                ): Boolean = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: ProductImageResponse,
                    newItem: ProductImageResponse
                ): Boolean = oldItem == newItem
            },
            createImageAdapterDelegate { item, startPosition ->
                val images = imagesAdapter.items.toTypedArray()

                StfalconImageViewer
                    .Builder(requireActivity(), images) { imageView, image ->
                        Glide
                            .with(this)
                            .load(AdHelper.getImageUrl(image.big))
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(imageView)
                    }
                    .withImageChangeListener { position ->
                        binding.viewPagerImages.setCurrentItem(position, false)
                    }
                    .withStartPosition(startPosition)
                    .withBackgroundColorResource(R.color.black)
                    .withHiddenStatusBar(false)
                    .allowZooming(true)
                    .allowSwipeToDismiss(true)
                    .show()
            }
        )

        binding.viewPagerImages.adapter = imagesAdapter

        binding.scrollingPagerIndicator.attachToPager(binding.viewPagerImages)


        viewModel = ViewModelProvider(this, viewModelFactory).get(ProductViewModel::class.java)

        bindViewModel()

        viewModel.onStart(getKey<ProductKey>())
    }

    private fun createImageAdapterDelegate(onClick: (ProductImageResponse, Int) -> Unit) =
        adapterDelegateViewBinding<ProductImageResponse, ProductImageResponse, ItemProductImageBinding>(
            { inflater, parent -> ItemProductImageBinding.inflate(inflater, parent, false) }
        ) {
            binding.imageView.setOnClickListener {
                onClick(item, bindingAdapterPosition)
            }

            bind {
                Glide
                    .with(itemView)
                    .load(AdHelper.getImageUrl(item.big))
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.imageView)
            }
        }

    private fun bindViewModel() {
        viewModel.loadingLiveData.distinctUntilChanged()
            .observe(viewLifecycleOwner) { raw ->
                val value = raw ?: return@observe

                binding.progressBar.isVisible = value
            }

        viewModel.refreshEnabledLiveData.distinctUntilChanged()
            .observe(viewLifecycleOwner) { raw ->
                val value = raw ?: return@observe

                binding.swipeRefreshLayout.isEnabled = value
            }

        viewModel.contentLiveData.distinctUntilChanged()
            .observe(viewLifecycleOwner) { raw ->
                val value = raw ?: return@observe

                binding.headerView.isVisible = value
                binding.viewContent.isVisible = value
            }

        viewModel.dataLiveData.distinctUntilChanged()
            .observe(viewLifecycleOwner) { raw ->
                val value = raw ?: return@observe

                binding.textViewTitle.text = value.title
                binding.textViewPrice.text = AdHelper.getPrice(value.price ?: 0)
                binding.textViewUsername.text = value.author_name
                binding.textViewCategory.text = value.categoryId.toString()
                binding.textViewLocation.text = value.cityId.toString()
                binding.textViewPhone.text = value.phone
                binding.textViewCreatedDate.text = AdHelper.getAdCreatedDate(value.createdAt, false)
                binding.textViewDescription.text = value.text
                binding.textViewViews.text = AdHelper.getViews(value.views, resources)

                imagesAdapter.items = value.images
                imagesAdapter.notifyDataSetChanged()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}