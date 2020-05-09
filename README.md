# Looper View Pager
The Simple Looper View Pager Android Library

![screenshot gif](https://github.com/prongbang/LooperViewPager/blob/master/screenshots/screenshots.gif?raw=true)

## Download
> build.gradle
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

### Support Library

```gradle
implementation 'com.github.prongbang:looperviewpager:1.0.2'
```

### AndroidX

```gradle
implementation 'com.github.prongbang:looperviewpager:2.0.0'
```

## How to use
> MainActivity.kt
```kotlin
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
    vpIndicator.cleared()
}
```

> GalleryPagerAdapter.kt
```kotlin
class GalleryPagerAdapter(fm: FragmentManager, viewPager: ViewPager) : LooperFragmentStatePagerAdapter<Gallery>(fm, viewPager) {

    override fun getItem(oldPosition: Int, newPosition: Int): Fragment {
        return GalleryFragment.newInstance(content[newPosition])
    }
}
```

> Gallery.kt
```kotlin
@Parcelize
data class Gallery(
        var id: Int,
        var name: String,
        var image: String
) : Parcelable
```

> GalleryFragment.kt
```kotlin
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
```

> activity_main.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
	xmlns:app="http://schemas.android.com/apk/res-auto"
	xmlns:tools="http://schemas.android.com/tools"
	android:layout_width="match_parent"
	android:layout_height="match_parent"
	android:orientation="vertical"
	tools:context="com.prongbang.looperviewpager.MainActivity">

	<androidx.viewpager.widget.ViewPager
		android:id="@+id/vpGallery"
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		android:overScrollMode="never"
		app:layout_behavior="@string/appbar_scrolling_view_behavior"
		app:layout_constraintBottom_toBottomOf="parent"
		app:layout_constraintEnd_toEndOf="parent"
		app:layout_constraintStart_toStartOf="parent"
		app:layout_constraintTop_toTopOf="parent" />

	<com.prongbang.widget.looperviewpager.LooperViewPagerIndicator
		android:id="@+id/vpIndicator"
		android:layout_width="match_parent"
		android:layout_height="26dp"
		android:gravity="center_horizontal"
		android:orientation="horizontal"
		app:dotIndicator="@drawable/dot_indicator"
		app:dotPaddingEnd="10dp"
		app:dotPaddingStart="10dp"
		app:layout_constraintBottom_toBottomOf="parent"
		app:layout_constraintEnd_toEndOf="parent"
		app:layout_constraintStart_toStartOf="parent" />

	<TextView
		android:id="@+id/counter"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:gravity="center_horizontal"
		android:textAppearance="@style/TextAppearance.AppCompat.Large"
		app:layout_constraintEnd_toEndOf="parent"
		app:layout_constraintStart_toStartOf="parent"
		app:layout_constraintTop_toTopOf="parent"
		tools:text="1/5" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### Thank you
