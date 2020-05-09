package com.prongbang.widget.looperviewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager

/**
 * Created by prongbang on 2/12/2018 AD.
 */
abstract class LooperFragmentStatePagerAdapter<T>(
		fm: FragmentManager,
		private val pager: ViewPager
) : FragmentStatePagerAdapter(fm), ViewPager.OnPageChangeListener {

	protected var content = ArrayList<T>()

	abstract fun getItem(oldPosition: Int, newPosition: Int): Fragment

	fun setItems(items: ArrayList<T>) {
		this.content = items
		notifyDataSetChanged()
	}

	override fun getItem(position: Int): Fragment {

		var index = position - 1
		if (position == 0) {
			index = content.size - 1
		} else if (position == content.size + 1) {
			index = 0
		}

		return getItem(position, index)
	}

	override fun getCount(): Int {
		return content.size + 2
	}

	override fun onPageSelected(position: Int) {
		when (position) {
			0 -> pager.setCurrentItem(content.size, false)
			content.size + 1 -> pager.setCurrentItem(1, false)
		}
	}

	override fun onPageScrollStateChanged(arg0: Int) {}

	override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
}