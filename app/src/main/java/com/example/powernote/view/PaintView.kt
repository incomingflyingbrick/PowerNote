package com.example.powernote.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import com.example.powernote.FingerPath


class PaintView:View {

    constructor(context: Context?) : super(context){
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        initView()
    }


    var BRUSH_SIZE = 5f
    val DEFAULT_COLOR: Int = Color.RED
    val DEFAULT_BG_COLOR: Int = Color.WHITE

    private val TOUCH_TOLERANCE = 4f
    private var mX = 0f
    private var mY = 0f
    private var mPath: Path? = null
    private val mPaint: Paint = Paint()
    private val paths = mutableListOf<FingerPath>()
    private var currentColor = 0

    private var mBackGroundColor = Color.WHITE

    private var strokeWidth = 0f
    private var emboss = false
    private var blur = false
    private lateinit var mBlur: MaskFilter
    private lateinit var mBitmap: Bitmap
    private lateinit var mCanvas: Canvas
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
        mBlur = BlurMaskFilter(5f, BlurMaskFilter.Blur.NORMAL)
    }

    fun init(metrics: DisplayMetrics){
        val height = metrics.heightPixels
        val width = metrics.widthPixels

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        mCanvas = Canvas(mBitmap)

        currentColor = DEFAULT_COLOR
        strokeWidth = BRUSH_SIZE
    }

    fun normal() {
        emboss = false
        blur = false
    }

    fun emboss() {
        emboss = true
        blur = false
    }

    fun blur() {
        emboss = false
        blur = true
    }

    fun clear() {
        mBackGroundColor = DEFAULT_BG_COLOR
        paths.clear()
        normal()
        invalidate()
    }

    fun changeStrokeSize(size:Float){
        strokeWidth = size
    }

    fun changeColor(color: Int){
        currentColor = color
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            it.save()
            mCanvas.drawColor(mBackGroundColor)
            for(item in paths){
                mPaint.color = item.color
                mPaint.strokeWidth = item.strokeWidth
                mPaint.maskFilter = null
                if(item.blur){
                    mPaint.maskFilter = mBlur
                }
                mCanvas.drawPath(item.path,mPaint)
            }
            it.drawBitmap(mBitmap,0f,0f,mBitmapPaint)
            it.restore()
        }
    }

    private fun touchStart(x: Float, y: Float) {

        mPath = Path()
        mPath?.let {
            val fp = FingerPath(currentColor, emboss, blur, strokeWidth,it)
            paths.add(fp)
            it.reset()
            it.moveTo(x, y)
            mX = x
            mY = y
        }

    }

    private fun touchMove(x: Float, y: Float) {
        val dx = Math.abs(x - mX)
        val dy = Math.abs(y - mY)
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath!!.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2)
            mX = x
            mY = y
        }
    }

    private fun touchUp() {
        mPath?.let {
            it.lineTo(mX,mY)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchStart(x, y)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                touchMove(x, y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                touchUp()
                invalidate()
            }
        }
        return true
    }

}