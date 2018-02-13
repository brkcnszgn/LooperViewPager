package com.prongbang.looperviewpager


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


/**
 * A simple [Fragment] subclass.
 */
class GalleryFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance(gallery: Gallery): GalleryFragment {
            val fragment = GalleryFragment()
            val bundle = Bundle()
            bundle.putParcelable(Gallery::class.java.simpleName, gallery)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_gallery, container, false)

        if (arguments != null) {
            val gallery = arguments!!.getParcelable<Gallery>(Gallery::class.java.simpleName)

            val tvMessage = v.findViewById<TextView>(R.id.tvMessage)
            tvMessage.text = gallery.name
        }

        return v
    }

}// Required empty public constructor
