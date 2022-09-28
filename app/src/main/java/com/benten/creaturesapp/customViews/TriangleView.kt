package com.benten.creaturesapp.customViews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.benten.creaturesapp.R

class TriangleView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private val path = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.BLACK
    }
    private val pointA = PointF(0f, 0f)
    private val pointB = PointF(0f, 0f)
    private val pointC = PointF(0f, 0f)
    private var facingUp: Boolean
     var isActived: Boolean = false

    init {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.TriangleView)
        facingUp = typedArray.getBoolean(R.styleable.TriangleView_facingUp, false)
        typedArray.recycle()
    }

    fun setActive(active: Boolean) {
        isActived = active
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (isActived) {
            paint.color = Color.BLUE
        } else {
            paint.color = Color.BLACK
        }
        pointA.x = 0f
        pointA.y = if (facingUp) {
            measuredHeight.toFloat()
        } else {
            0f
        }

        pointB.x = measuredWidth / 2f
        pointB.y = if (facingUp) {
            0f
        } else {
            measuredHeight.toFloat()
        }

        pointC.x = measuredWidth.toFloat()

        pointC.y = if (facingUp) {
            measuredHeight.toFloat()
        } else {
            0f
        }

        if (facingUp) {
            path.moveTo(pointA.x, pointA.y)
        }
        path.lineTo(pointB.x, pointB.y)
        path.lineTo(pointC.x, pointC.y)
        path.close()

        canvas?.drawPath(path, paint)
    }



}