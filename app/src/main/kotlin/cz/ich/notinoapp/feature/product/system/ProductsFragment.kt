package cz.ich.notinoapp.feature.product.system

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import cz.ich.notinoapp.R
import cz.ich.notinoapp.databinding.FragmentProductsBinding
import cz.ich.notinoapp.feature.product.model.Product
import cz.ich.notinoapp.feature.product.presentation.ProductsViewModel
import cz.ich.notinoapp.main.domain.Result
import cz.ich.notinoapp.main.system.ErrorDialogFragment
import cz.ich.notinoapp.main.system.GridSpacingItemDecoration
import cz.ich.notinoapp.main.system.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val COLUMNS_PORTRAIT = 2
private const val COLUMNS_LANDSCAPE = 4

@AndroidEntryPoint
class ProductsFragment : Fragment(R.layout.fragment_products), ProductsAdapter.ClickListener {

    private val binding by viewBinding(
        FragmentProductsBinding::bind
    )

    @Inject
    lateinit var adapter: ProductsAdapter

    private val viewModel: ProductsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeData()
    }

    override fun onProductClicked(product: Product) {
        viewModel.onHeartClicked(product)
    }

    private fun setupRecyclerView() {
        val columns = getColumnCount()
        binding.recProducts.adapter = adapter
        binding.recProducts.layoutManager = GridLayoutManager(context, columns)
        binding.recProducts.addItemDecoration(
            GridSpacingItemDecoration(
                columns,
                requireContext().resources.getDimension(R.dimen.grid_spacing).toInt()
            )
        )
        adapter.setOnClickListener(this)
    }

    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.products.collect {
                    when (it) {
                        is Result.Running -> showData(null)
                        is Result.Success -> showData(it.data)
                        is Result.Error -> showErrorDialog()
                    }
                }
            }
        }
    }

    private fun showData(data: List<Product>?) {
        if (data.isNullOrEmpty()) {
            binding.progressBar.visibility = View.VISIBLE
            binding.recProducts.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.recProducts.visibility = View.VISIBLE
            adapter.submitList(data)
        }
    }


    private fun getColumnCount(): Int {
        val orientation = resources.configuration.orientation
        return if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            COLUMNS_LANDSCAPE
        } else {
            COLUMNS_PORTRAIT
        }
    }

    private fun showErrorDialog() {
        val fragment = childFragmentManager.findFragmentByTag(ErrorDialogFragment.TAG)
        if (fragment == null) {
            ErrorDialogFragment().show(childFragmentManager, ErrorDialogFragment.TAG)
        }
    }

}