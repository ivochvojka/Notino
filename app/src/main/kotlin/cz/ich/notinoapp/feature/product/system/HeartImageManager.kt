package cz.ich.notinoapp.feature.product.system

import android.graphics.drawable.Drawable
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnticipateInterpolator
import android.widget.ImageView
import cz.ich.notinoapp.feature.product.di.ProductModule
import cz.ich.notinoapp.feature.product.model.Product
import javax.inject.Inject
import javax.inject.Singleton

private const val ANIMATION_DURATION_MILLIS = 250L
private const val FULL_SCALE = 1.0f
private const val HALF_SCALE = 0.5f

private val ANTICIPATE_INTERPOLATOR = AnticipateInterpolator(2.8f)
private val ACCELERATE_DECELERATE_INTERPOLATOR = AccelerateDecelerateInterpolator()

/**
 * Manager providing operations with heart image.
 */
@Singleton
class HeartImageManager @Inject constructor(
    @ProductModule.EnabledHeartDrawable val enabledHeartDrawable: Drawable,
    @ProductModule.DisabledHeartDrawable val disabledHeartDrawable: Drawable,
) {

    /**
     * Animate [imgView] by shrink and expand animations.
     */
    fun animateHeart(imgView: ImageView, product: Product) {
        createShrinkAnimation(imgView, product.isHeartEnabled)
    }

    /**
     * Animate [imgView] by shrink and expand animations.
     */
    fun setupHeartImage(imgView: ImageView, product: Product) {
        updateHeartImage(imgView, product.isHeartEnabled)
    }

    private fun createShrinkAnimation(imgView: ImageView, enabled: Boolean) =
        imgView.animate()
            .scaleX(HALF_SCALE)
            .scaleY(HALF_SCALE)
            .setDuration(ANIMATION_DURATION_MILLIS)
            .setInterpolator(ANTICIPATE_INTERPOLATOR)
            .withEndAction {
                updateHeartImage(imgView, enabled)
                createExpandAnimation(imgView).start()
            }

    private fun createExpandAnimation(imgView: ImageView) =
        imgView.animate()
            .scaleX(FULL_SCALE)
            .scaleY(FULL_SCALE)
            .setDuration(ANIMATION_DURATION_MILLIS)
            .setInterpolator(ACCELERATE_DECELERATE_INTERPOLATOR)

    private fun updateHeartImage(imgView: ImageView, enabled: Boolean) {
        val imgDrawable = if (enabled) {
            enabledHeartDrawable
        } else {
            disabledHeartDrawable
        }
        imgView.setImageDrawable(imgDrawable)
    }
}