package com.prongbang.looperviewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.prongbang.widget.looperviewpager.LooperFragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val galleries = ArrayList<Gallery>()
        galleries.add(Gallery(1, "Gallery 1",""))
        galleries.add(Gallery(2, "Gallery 2",""))
        galleries.add(Gallery(3, "Gallery 3",""))
        galleries.add(Gallery(4, "Gallery 4",""))
        galleries.add(Gallery(5, "Gallery 5",""))

        val adapter = GalleryPagerAdapter(supportFragmentManager, vpGallery)
        adapter.setItems(galleries)
        vpGallery.adapter = adapter
        vpGallery.addOnPageChangeListener(adapter)
        vpGallery.setCurrentItem(1, false)

        vpIndicator.setupWithViewPager(vpGallery, galleries.size)

        val pageSize = galleries.size
        counter.text = "1/$pageSize"
        vpIndicator.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                counter.text = "$position/$pageSize"
                Log.i(TAG, "onPageSelected: $position")
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        vpIndicator.cleared()
    }

    class GalleryPagerAdapter(fm: FragmentManager, viewPager: ViewPager) : LooperFragmentStatePagerAdapter<Gallery>(fm, viewPager) {

        override fun getItem(oldPosition: Int, newPosition: Int): Fragment {

            return GalleryFragment.newInstance(content[newPosition])
        }

    }

}
