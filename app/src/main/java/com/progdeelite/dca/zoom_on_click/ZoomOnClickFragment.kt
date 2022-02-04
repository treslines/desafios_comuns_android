package com.progdeelite.dca.zoom_on_click

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentZoomOnClickBinding
import java.io.ByteArrayOutputStream


class ZoomOnClickFragment : Fragment(R.layout.fragment_zoom_on_click) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentZoomOnClickBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getImageFromDb()
    }

    private fun getImageFromDb() {
        var bitmapdata: ByteArray? = null //registro.getComprovante()
        if (bitmapdata == null) {
            val defaultImage = BitmapFactory.decodeResource(resources, R.drawable.default_picture)
            val bos = ByteArrayOutputStream()
            defaultImage.compress(Bitmap.CompressFormat.PNG, 100, bos)

            // registro.setComprovante(bos.toByteArray()) SALVAR NO BANCO
            // bitmapdata = registro.getComprovante() // OBTER DO BANCO
            bitmapdata = bos.toByteArray()
        }
        val bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata!!.size)
        binding.dbImage.setImageBitmap(bitmap)
        binding.dbImage.setOnClickListener { zoomImageFromThumb(binding.dbImage, bitmap, binding.root) }
    }

    private var mCurrentAnimator: Animator? = null
    private val mShortAnimationDuration = 0
    private fun zoomImageFromThumb(thumbView: View, bitmap: Bitmap, rootView: ViewGroup) {
        // If there's an animation in progress, cancel it immediately and proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator?.cancel()
        }

        // Load the high-resolution "zoomed-in" image.
        val expandedImageView = rootView.findViewById<View>(R.id.expanded_image) as ImageView
        expandedImageView.setImageBitmap(bitmap)
        val metrics = resources.displayMetrics
        val displayWidth = metrics.widthPixels
        val layoutParams = expandedImageView.layoutParams
        layoutParams.width = displayWidth
        layoutParams.height = displayWidth
        expandedImageView.layoutParams = layoutParams

        // Calculate the starting and ending bounds for the zoomed-in image. This step
        // involves lots of math. Yay, math.
        val startBounds = Rect()
        val finalBounds = Rect()
        val globalOffset = Point()

        // The start bounds are the global visible rectangle of the thumbnail, and the
        // final bounds are the global visible rectangle of the container view. Also
        // set the container view's offset as the origin for the bounds, since that's
        // the origin for the positioning animation properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds)
        rootView.getGlobalVisibleRect(finalBounds, globalOffset)
        startBounds.offset(-globalOffset.x, -globalOffset.y)
        finalBounds.offset(-globalOffset.x, -globalOffset.y)

        // Adjust the start bounds to be the same aspect ratio as the final bounds using the
        // "center crop" technique. This prevents undesirable stretching during the animation.
        // Also calculate the start scaling factor (the end scaling factor is always 1.0).
        val startScale: Float
        if (finalBounds.width().toFloat() / finalBounds.height()
            > startBounds.width().toFloat() / startBounds.height()
        ) {
            // Extend start bounds horizontally
            startScale = startBounds.height().toFloat() / finalBounds.height()
            val startWidth = startScale * finalBounds.width()
            val deltaWidth = (startWidth - startBounds.width()) / 2
            startBounds.left -= deltaWidth.toInt()
            startBounds.right += deltaWidth.toInt()
        } else {
            // Extend start bounds vertically
            startScale = startBounds.width().toFloat() / finalBounds.width()
            val startHeight = startScale * finalBounds.height()
            val deltaHeight = (startHeight - startBounds.height()) / 2
            startBounds.top -= deltaHeight.toInt()
            startBounds.bottom += deltaHeight.toInt()
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation begins,
        // it will position the zoomed-in view in the place of the thumbnail.
        thumbView.alpha = 0f
        expandedImageView.visibility = View.VISIBLE

        // Set the pivot point for SCALE_X and SCALE_Y transformations to the top-left corner of
        // the zoomed-in view (the default is the center of the view).
        expandedImageView.pivotX = 0f
        expandedImageView.pivotY = 0f

        // Construct and run the parallel animation of the four translation and scale properties
        // (X, Y, SCALE_X, and SCALE_Y).
        val set = AnimatorSet()
        set
            .play(
                ObjectAnimator.ofFloat(
                    expandedImageView, View.X, startBounds.left.toFloat(),
                    finalBounds.left.toFloat()
                )
            )
            .with(
                ObjectAnimator.ofFloat(
                    expandedImageView, View.Y, startBounds.top.toFloat(),
                    finalBounds.top.toFloat()
                )
            )
            .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
            .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f))
        set.duration = mShortAnimationDuration.toLong()
        set.interpolator = DecelerateInterpolator()
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                mCurrentAnimator = null
            }

            override fun onAnimationCancel(animation: Animator) {
                mCurrentAnimator = null
            }
        })
        set.start()
        mCurrentAnimator = set

        // Upon clicking the zoomed-in image, it should zoom back down to the original bounds
        // and show the thumbnail instead of the expanded image.
        expandedImageView.setOnClickListener {
            if (mCurrentAnimator != null) {
                mCurrentAnimator?.cancel()
            }

            // Animate the four positioning/sizing properties in parallel, back to their
            // original values.
            val positioningAnimSet = AnimatorSet()
            positioningAnimSet
                .play(
                    ObjectAnimator.ofFloat(
                        expandedImageView,
                        View.X,
                        startBounds.left.toFloat()
                    )
                )
                .with(
                    ObjectAnimator.ofFloat(
                        expandedImageView,
                        View.Y,
                        startBounds.top.toFloat()
                    )
                )
                .with(
                    ObjectAnimator
                        .ofFloat(
                            expandedImageView,
                            View.SCALE_X,
                            startScale
                        )
                )
                .with(
                    ObjectAnimator
                        .ofFloat(
                            expandedImageView,
                            View.SCALE_Y,
                            startScale
                        )
                )
            positioningAnimSet.duration = mShortAnimationDuration.toLong()
            positioningAnimSet.interpolator = DecelerateInterpolator()
            positioningAnimSet.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    thumbView.alpha = 1f
                    expandedImageView.visibility = View.GONE
                    mCurrentAnimator = null
                }

                override fun onAnimationCancel(animation: Animator) {
                    thumbView.alpha = 1f
                    expandedImageView.visibility = View.GONE
                    mCurrentAnimator = null
                }
            })
            positioningAnimSet.start()
            mCurrentAnimator = positioningAnimSet
        }
    }
}