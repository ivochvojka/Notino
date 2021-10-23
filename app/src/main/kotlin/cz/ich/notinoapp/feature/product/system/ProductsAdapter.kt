package cz.ich.notinoapp.feature.product.system

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import cz.ich.notinoapp.R
import cz.ich.notinoapp.databinding.LayoutProductBinding
import cz.ich.notinoapp.feature.product.model.Product
import cz.ich.notinoapp.main.data.remote.RemoteConfig
import javax.inject.Inject

/**
 * Adapter for displaying list of [Product].
 * @see ProductViewHolder
 */
class ProductsAdapter @Inject constructor(
    private val heartImageManager: HeartImageManager
) :
    ListAdapter<Product, ProductViewHolder>(ProductDiffCallback) {

    private var listener: ClickListener? = null

    fun setOnClickListener(onClickListener: ClickListener) {
        listener = onClickListener;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            LayoutProductBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            heartImageManager,
            listener
        )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(getItem(position))

    // Diff callback for list adapter
    private object ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean = (oldItem == newItem)
    }

    interface ClickListener {
        fun onProductClicked(product: Product)
    }
}

/**
 * Holder for [LayoutProductBinding] view.
 */
class ProductViewHolder(
    private val binding: LayoutProductBinding,
    private val heartImageManager: HeartImageManager,
    private val listener: ProductsAdapter.ClickListener?
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {
        with(binding) {
            txtBrand.text = product.brand
            txtName.text = product.name
            txtAnnotation.text = product.annotation
            ratingBar.rating = product.score.toFloat()
            if (product.score == 0) {
                ratingBar.visibility = View.GONE
            }
            txtPrice.text = root.context.getString(R.string.product_price, product.price)
            Glide.with(imgProduct)
                .load(RemoteConfig.IMAGE_BASE_URL + product.imageUrl)
                .into(imgProduct)
            heartImageManager.setupHeartImage(imgHeart, product)
            imgHeart.setOnClickListener {
                product.isHeartEnabled = !product.isHeartEnabled
                heartImageManager.animateHeart(imgHeart, product)
                listener?.onProductClicked(product)
            }
        }
    }
}
