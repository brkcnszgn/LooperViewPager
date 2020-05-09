package com.prongbang.looperviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment() {

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
	                          savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_gallery, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		arguments?.getParcelable<Gallery>(Gallery::class.java.simpleName)
				?.let { gallery ->
					context?.let {
						rootView.setBackgroundColor(ContextCompat.getColor(it, gallery.backgroundColor))
					}
					tvMessage.text = gallery.name
				}
	}

	companion object {
		fun newInstance(gallery: Gallery): GalleryFragment {
			return GalleryFragment().apply {
				arguments = Bundle().apply {
					putParcelable(Gallery::class.java.simpleName, gallery)
				}
			}
		}
	}
}
