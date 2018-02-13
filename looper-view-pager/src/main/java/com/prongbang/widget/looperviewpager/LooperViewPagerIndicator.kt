package com.prongbang.widget.looperviewpager

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.prongbang.ui.looperviewpager.R

/**
 * Created by prongbang on 2/12/2018 AD.
 */
class LooperViewPagerIndicator(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs), ViewPager.OnPageChangeListener {

    private val BIG_SCALE = 1.0f
    private val SMALL_SCALE = 0.7f
    private val DIFF_SCALE = BIG_SCALE - SMALL_SCALE
    private val mContext: Context = context
    private var mViewPager: ViewPager? = null
    private var mPageCount: Int = 0
    private var mInitialPage = 0

    private var dotPaddingEnd = 10
    private var dotPaddingStart = 10
    private var mDotIndicatorResId = R.drawable.dot_indicator

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.LooperViewPagerIndicator, 0, 0)
        mDotIndicatorResId = a.getResourceId(R.styleable.LooperViewPagerIndicator_dotIndicator, mDotIndicatorResId)
        dotPaddingEnd = a.getDimensionPixelSize(R.styleable.LooperViewPagerIndicator_dotPaddingEnd, dotPaddingEnd)
        dotPaddingStart = a.getDimensionPixelSize(R.styleable.LooperViewPagerIndicator_dotPaddingStart, dotPaddingStart)
        a.recycle()
    }

    fun setupWithViewPager(viewPager: ViewPager, pageCount:Int) {
        this.mViewPager = viewPager
        this.mPageCount = pageCount
        initialIndicator()
        setIndicatorAsSelected(mInitialPage)
    }

    fun setPageCount(pageCount: Int) {
        mPageCount = pageCount
    }

    fun setInitialPage(page: Int) {
        mInitialPage = page
    }

    fun setDrawable(drawable: Int) {
        mDotIndicatorResId = drawable
    }

    fun setIndicatorAsSelected(index: Int) {
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view.isSelected = i == index
        }
    }

    fun cleared() {
        mViewPager?.clearOnPageChangeListeners()
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        // val index = position % mPageCount
        val index = mapPagerPositionToModelPosition(position)
        setIndicatorAsSelected(index)
    }

    fun mapPagerPositionToModelPosition(pagerPosition: Int): Int {
        // Put last page model to the first position.
        if (pagerPosition == 0) {
            return mPageCount - 1
        }
        // Put first page model to the last position.
        if (pagerPosition == mPageCount + 1) {
            return 0
        }
        return pagerPosition - 1;
    }

    private fun initialIndicator() {

        if (mPageCount <= 0) { return }

        mViewPager?.addOnPageChangeListener(this)

        removeAllViews()

        for (i in 0 until mPageCount) {
            val view = View(mContext)
            val dimen = dotPaddingEnd
            val margin = dotPaddingStart
            val lp = LinearLayout.LayoutParams(dimen, dimen)
            lp.setMargins(if (i == 0) 0 else margin, 0, 0, 0)
            view.layoutParams = lp
            view.setBackgroundResource(mDotIndicatorResId)
            view.isSelected = i == 0
            addView(view)
        }
    }
}