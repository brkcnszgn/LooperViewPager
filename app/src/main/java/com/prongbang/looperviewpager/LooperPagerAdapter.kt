package com.prongbang.looperviewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import java.util.*
import android.view.ViewGroup


/**
 * Created by mdev on 2/12/2018 AD.
 */
class LooperPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var MAXFRAGMENTS = 0
    private var mFragments: ArrayList<Fragment>? = null

    fun setItems(fragments: ArrayList<Fragment>) {
//        val f = ArrayList<Fragment>()
//        f.add(fragments[fragments.size - 1])
//        f.addAll(fragments)
//        f.add(fragments[0])
//        this.mFragments = f
        mFragments = fragments
//        mFragments?.add(0, fragments[fragments.size - 1])
//        mFragments?.add(fragments.size, fragments[0])

        MAXFRAGMENTS = mFragments!!.size

        for (f2 in mFragments!!)
            Log.i("fragments", f2.arguments?.getString("message"))
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        val realCount = getRealCount()

//        var realPosition = (position - 1) % realCount
//        Log.i(LooperPagerAdapter::class.java.simpleName, "realPosition:$realPosition")
//        if (realPosition < 0)
//            realPosition += realCount
//
//        return mFragments!![realPosition]

        val index = mapPagerPositionToModelPosition(position)

        Log.i(LooperPagerAdapter::class.java.simpleName, "index:$index")

        val fragment = mFragments!![index]

        return fragment
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val virtualPosition = position % MAXFRAGMENTS
        val virtualPosition = mapPagerPositionToModelPosition(position)
        Log.i(LooperPagerAdapter::class.java.simpleName, "position:${virtualPosition}")
        return super.instantiateItem(container, (virtualPosition))
    }

//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        val virtualPosition = position % MAXFRAGMENTS
//        val virtualPosition = mapPagerPositionToModelPosition(position)
//        super.destroyItem(container, virtualPosition, `object`)
//    }

    override fun getCount(): Int {

        return if (mFragments == null) 0 else mFragments!!.size
    }

    fun getRealCount(): Int {
        return if (mFragments == null) 0 else mFragments!!.size
    }

    fun mapPagerPositionToModelPosition(pagerPosition: Int): Int {
        // Put last page model to the first position.
        if (pagerPosition == 0) {
            return getRealCount() - 1
        }
        // Put first page model to the last position.
        if (pagerPosition == getRealCount() + 1) {
            return 0
        }
        return pagerPosition - 1
    }

}