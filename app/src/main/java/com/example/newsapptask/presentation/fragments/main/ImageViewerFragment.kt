package com.example.newsapptask.presentation.fragments.main

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.newsapptask.common.utils.BaseFragment
import com.example.newsapptask.common.utils.snackbar
import com.example.newsapptask.databinding.FragmentImageViewerBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject


@AndroidEntryPoint
class ImageViewerFragment : BaseFragment<FragmentImageViewerBinding>() {


    @Inject
    lateinit var glide: RequestManager
    private val args: ImageViewerFragmentArgs by navArgs()
    private var scaleGestureDetector: ScaleGestureDetector? = null
    override fun getViewBinding(): FragmentImageViewerBinding {
        return FragmentImageViewerBinding.inflate(layoutInflater)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        glide.load(args.imageUrl).into(binding.icImageView)


        actions()

        scaleGestureDetector = ScaleGestureDetector(requireContext(), ScaleListener(binding))
        requireView().setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_MOVE) {
                onTouchEvent(event)
            }
            true
        }

    }

    private fun onTouchEvent(motionEvent: MotionEvent?): Boolean {
        scaleGestureDetector!!.onTouchEvent(motionEvent)
        return true
    }

    private class ScaleListener(val binding: FragmentImageViewerBinding) :
        SimpleOnScaleGestureListener() {
        private var mScaleFactor = 1.0f

        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = 0.1f.coerceAtLeast(mScaleFactor.coerceAtMost(10.0f))
            binding.icImageView.scaleX = mScaleFactor
            binding.icImageView.scaleY = mScaleFactor
            return true
        }
    }

    private fun actions() {
        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.shareIcon.setOnClickListener {
            // for Image send ignore URI error
            val builder: StrictMode.VmPolicy.Builder = StrictMode.VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())
            glide
                .asBitmap()
                .load(args.imageUrl)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        shareBitmap(resource)

                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // this is called when imageView is cleared on lifecycle call or for
                        // some other reason.
                        // if you are referencing the bitmap somewhere else too other than this imageView
                        // clear it here as you can no longer have the bitmap
                    }
                })

        }
    }
    private fun getBitmapFromView(bmp: Bitmap?): Uri? {
        var bmpUri: Uri? = null
        try {
            val file = File(requireContext().externalCacheDir, System.currentTimeMillis().toString() + ".jpg")
            val out = FileOutputStream(file)
            bmp?.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.close()
            bmpUri = Uri.fromFile(file)

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bmpUri
    }

    private fun shareBitmap(bitmap: Bitmap) {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "image/*"
            shareIntent.putExtra(Intent.EXTRA_STREAM, getBitmapFromView(bitmap))
            startActivity(Intent.createChooser(shareIntent, "Share image via.."))
        } catch (e:Exception) {
            snackbar(e.message ?: "")
            Timber.d("shareBitmap: " + e.message)
        }

    }


    override fun subscribeToObservables() {

    }
}