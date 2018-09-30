package com.adamwozniewski.kotlin_app

import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import com.adamwozniewski.kotlin_app.models.FoodIngridient
import com.github.florent37.materialviewpager.header.HeaderDesign
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val ingidientArray by lazy {
        val jsonStream = assets.open("base.json")
        val size = jsonStream.available();
        val buffer = ByteArray(size)
        jsonStream.read(buffer)
        jsonStream.close()
        val jsonString = String(buffer, Charsets.UTF_8)
        Gson().fromJson<List<FoodIngridient>>(jsonString, FOOD_ING_TYPE)
    } // leniwe ładowanie, w momecnie pierwszego użycia
    companion object {
        private val FOOD_ING_TYPE = object: TypeToken<List<FoodIngridient>>(){}.type
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.initPager()
    }

    private fun initPager() {
        materialpageridentificator.viewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getCount(): Int = ingidientArray.size
            override fun getItem(p0: Int): Fragment = Fragment()
            override fun getPageTitle(position: Int): CharSequence? = ingidientArray[position].name
        }// to jet te ID


        materialpageridentificator.setMaterialViewPagerListener { page: Int ->
            HeaderDesign.fromColorResAndDrawable(
                    R.color.black,
                    this.ingidientArray[page].drawable
            )
        }
        materialpageridentificator.viewPager.offscreenPageLimit = (materialpageridentificator.viewPager.adapter as FragmentStatePagerAdapter).count
        materialpageridentificator.pagerTitleStrip.setViewPager(materialpageridentificator.viewPager)
    }
}
