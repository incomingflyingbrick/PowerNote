package com.example.powernote.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.powernote.FingerPath


class PaintView:View {

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)


    var BRUSH_SIZE = 20
    val DEFAULT_COLOR: Int = Color.RED
    val DEFAULT_BG_COLOR: Int = Color.WHITE
    private val TOUCH_TOLERANCE = 4f
    private var mX = 0f
    private var mY = 0f
    private val mPath: Path? = null
    private val mPaint: Paint = Paint()
    private val paths: ArrayList<FingerPath> = ArrayList()
    private val currentColor = 0
    private val backgroundColor = DEFAULT_BG_COLOR
    private val strokeWidth = 0
    private val emboss = false
    private val blur = false
    private val mEmboss: MaskFilter? = null
    private val mBlur: MaskFilter? = null
    private val mBitmap: Bitmap? = null
    private val mCanvas: Canvas? = null
    private val mBitmapPaint: Paint = Paint(Paint.DITHER_FLAG)


    private fun initView(){
        mPaint.isAntiAlias = true
        mPaint.isDither = true
        mPaint.color = DEFAULT_COLOR
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeJoin = Paint.Join.ROUND
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.xfermode = null
        mPaint.alpha = 0xff
    }

}