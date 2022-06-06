package com.l3azh.customlabel

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CustomLabel(context:Context):View(context) {

    private val DEFAULT_WIDTH:Int = 300
    private val DEFAULT_HEIGHT:Int = 58
    private val DEFAULT_TEXTSIZE:Float = 24f

    private var shapeBackgroundPaint:Paint? = null
    private var textPaint:Paint? = null
    private var textColor:Int? = null
    private var shapeColor:Int? = null

    constructor(context: Context, attributeSet: AttributeSet):this(context){
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomLabel)
        textColor = typedArray.getColor(R.styleable.CustomLabel_textColor, Color.BLACK)
        shapeColor = typedArray.getColor(R.styleable.CustomLabel_shapeColor, Color.GRAY)

        shapeBackgroundPaint = Paint()
        shapeBackgroundPaint!!.color = shapeColor!!
        textPaint = Paint()
        textPaint!!.color = textColor!!
        textPaint!!.textSize = DEFAULT_TEXTSIZE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width = 0
        var height = 0
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        width = when(widthMode){
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> DEFAULT_WIDTH.coerceAtMost(widthSize)
            else -> DEFAULT_WIDTH
        }

        height = when(heightMode){
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> DEFAULT_HEIGHT.coerceAtMost(heightSize)
            else -> DEFAULT_HEIGHT
        }

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawLine(5.toFloat(),5.toFloat(),width.toFloat(),5.toFloat(), shapeBackgroundPaint!!)
        canvas.save()
        canvas.drawText("Helloworld", 20.toFloat(), 20.toFloat(), textPaint!!)
        canvas.save()
        val bound = RectF(0f,0f,10f,10f)
        canvas.drawArc(bound,180f,90f,true, shapeBackgroundPaint!!)
        canvas.save()
    }

}