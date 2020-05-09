package com.prongbang.looperviewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val galleries = ArrayList<Gallery>()
		galleries.add(Gallery(1, "Gallery 1", "", android.R.color.holo_green_light))
		galleries.add(Gallery(2, "Gallery 2", "", android.R.color.holo_red_light))
		galleries.add(Gallery(3, "Gallery 3", "", android.R.color.holo_blue_light))
		galleries.add(Gallery(4, "Gallery 4", "", android.R.color.holo_orange_light))
		galleries.add(Gallery(5, "Gallery 5", "", android.R.color.holo_purple))

		val galleryPagerAdapter = GalleryPagerAdapter(supportFragmentManager, vpGallery).apply {
			setItems(galleries)
		}

		vpGallery?.apply {
			adapter = galleryPagerAdapter
			addOnPageChangeListener(galleryPagerAdapter)
			setCurrentItem(1, false)
		}

		val pageSize = galleries.size
		counter.text = ("1/$pageSize")

		vpIndicator?.apply {
			setupWithViewPager(vpGallery, galleries.size)
			addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
				override fun onPageScrollStateChanged(state: Int) {}

				override fun onPageScrolled(position: Int, positionOffset: Float,
				                            positionOffsetPixels: Int) {
				}

				override fun onPageSelected(position: Int) {
					counter.text = ("$position/$pageSize")
				}
			})
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		vpIndicator?.cleared()
	}

}
