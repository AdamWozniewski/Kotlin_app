package com.adamwozniewski.kotlin_app.models

import android.graphics.drawable.Drawable
import android.support.v4.content.res.ResourcesCompat
import com.adamwozniewski.kotlin_app.App
import com.adamwozniewski.kotlin_app.R
import java.io.Serializable


/**
 * Model danych dla składnika pokarmowego
 */
data class FoodIngridient(
        val id: Int,
        val name: String,
        val desc: String,
        val danger: String,
        val foodExample: String,
        private val dailyMale: String,
        private val dailyFem: String,
        private val unit: IntakeDoseUnit,
        val imgName: String
): Serializable {
    val intake
        get() = """${"\u2642"} $dailyMale ${unit.getLocalName()}
            | ${"\u2640"} $dailyFem ${unit.getLocalName()}""".trimMargin()
    val drawable: Drawable? // moze niue byc zasobu obrazka
        get() {
            val apc = App.appContext
            val id = apc.resources.getIdentifier(imgName, DRAWABLE_TYPE_DEF, apc.packageName)
            return ResourcesCompat.getDrawable(apc.resources, id, null)
        }
    companion object {
        const val DRAWABLE_TYPE_DEF = "drawable"
    }
}

enum class IntakeDoseUnit {
    GRAM() {
        override fun getLocalName() = App.appContext.getString(R.string.gram)
    },
    MGRAM() {
        override fun getLocalName() = App.appContext.getString(R.string.miligram)
    },
    UGRAM() {
        override fun getLocalName() = App.appContext.getString(R.string.mikrogram)
    },
    IU() {
        override fun getLocalName() = App.appContext.getString(R.string.unifiedunit)
    },
    UNKNOWN() {
        override fun getLocalName() = App.appContext.getString(R.string.unifiedunit)
    };

    abstract fun getLocalName(): String?
}