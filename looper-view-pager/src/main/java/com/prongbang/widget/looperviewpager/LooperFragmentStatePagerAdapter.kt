package com.prongbang.widget.looperviewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import com.prongbang.ui.looperviewpager.BuildConfig

/**
 * Created by prongbang on 2/12/2018 AD.
 */
abstract class LooperFragmentStatePagerAdapter<T>(fm: FragmentManager, private val pager: ViewPager) : FragmentStatePagerAdapter(fm), ViewPager.OnPageChangeListener {

    companion object {
        private var TAG = LooperFragmentStatePagerAdapter::class.java.simpleName
    }

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
            0 -> {
                pager.setCurrentItem(content.size, false)
                if (BuildConfig.DEBUG) Log.d(TAG, "Resetting to last page.")
            }
            content.size + 1 -> {
                pager.setCurrentItem(1, false)
                if (BuildConfig.DEBUG) Log.i(TAG, "Resetting to first page.")
            }
        }
    }

    override fun onPageScrollStateChanged(arg0: Int) {}

    override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
}