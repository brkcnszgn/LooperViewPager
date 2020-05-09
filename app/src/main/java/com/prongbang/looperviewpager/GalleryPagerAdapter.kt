package com.prongbang.looperviewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.prongbang.widget.looperviewpager.LooperFragmentStatePagerAdapter

class GalleryPagerAdapter(fm: FragmentManager, viewPager: ViewPager) :
		LooperFragmentStatePagerAdapter<Gallery>(fm, viewPager) {

	override fun getItem(oldPosition: Int, newPosition: Int): Fragment {
		return GalleryFragment.newInstance(content[newPosition])
	}
}
