package com.hades.kotlintrainning.view

import android.content.Context
import android.widget.ImageView
import android.graphics.RectF
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import com.hades.kotlintrainning.R


class RectangleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    var radius = 0.0f

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.RectangleImageView, 0, 0)
        radius = a.getDimension(R.styleable.RectangleImageView_roundedRadius, 10f)
        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val v = measuredWidth * 1.5f
        setMeasuredDimension(measuredWidth, v.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        val clipPath = Path()
        val rect = RectF(0f, 0f, this.width.toFloat(), this.height.toFloat())
        clipPath.addRoundRect(rect, radius, radius, Path.Direction.CW)
        canvas.clipPath(clipPath)
        super.onDraw(canvas)
    }
}
