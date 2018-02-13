package com.prongbang.looperviewpager

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView


/**
 * Created by mdev on 2/13/2018 AD.
 */
class LoopingViewPagerAdapter(private val context: Context, private val data: List<Gallery>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (`object` as View) == view
    }

    override fun getCount(): Int {

        return data.size + 2
    }

    fun getRealCount(): Int {
        return data.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        val modelPosition = mapPagerPositionToModelPosition(position)
        val mLayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val itemView = LayoutInflater.from(context).inflate(R.layout.item_gallery, container, false)
        val itemView = mLayoutInflater.inflate(R.layout.item_gallery, container, false)

        val tvMsg = itemView.findViewById<TextView>(R.id.tvMsg)

        val d = data[modelPosition]
        tvMsg.text = d.name

        container.addView(itemView);

        return container
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

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(mFragments!!.get(position))
        (container as ViewPager).removeView(`object` as View)
    }
}