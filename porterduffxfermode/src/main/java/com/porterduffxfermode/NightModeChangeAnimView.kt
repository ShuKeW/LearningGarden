package com.porterduffxfermode

import android.animation.Animator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.graphics.*
import android.os.Build
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator

/**
 *  @date 2019/1/22   9:59 AM
 *  @author weishukai
 *  @describe
 */
class NightModeChangeAnimView : View {
    private val TAG = "NightModeChangeAnimView"
    val VIEW_ID = 1000
    //    private var windowManager:WindowManager? = null
    private var rootView: ViewGroup? = null
    private var startX: Float = 0.0f
    private var startY: Float = 0.0f
    private var startRadius: Float = 0.0f
    private var endRadius: Float = 0.0f
    private var currentRadius: Float = 0.0f
    private var onClickViewRect: Rect? = null

    private var paint: Paint? = null
    private var xfermode: Xfermode? = null
    private val startColor = Color.parseColor("#FF000000")
    private val endColor = Color.parseColor("#00000000")

    var srcBackground: Bitmap? = null

    private var animRipple: ValueAnimator? = null
    private var animUpdateListener: RippleAnimUpdateListener? = null
    private var animListener: RippleAnimListener? = null
    private var duration: Long = 2000

    constructor(context: Context) : super(context)

    private constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun init(activity: Activity, startView: View) {
//        windowManager = activity.getSystemService(Activity.WINDOW_SERVICE) as WindowManager?
        rootView = activity.window.decorView as ViewGroup?
        onClickViewRect = Rect()
        getGlobalVisibleRectOfDecorView(startView, onClickViewRect!!)
        val viewWidthHalf = startView.width / 2
        val viewHeightHalf = startView.height / 2
        startX = (onClickViewRect!!.left + viewWidthHalf).toFloat()
        startY = (onClickViewRect!!.top + viewHeightHalf).toFloat()
        startRadius = Math.max(viewWidthHalf, viewHeightHalf).toFloat()
        this.endRadius = getMaxRadius()
        paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        Log.e(TAG, "init：$startX--$startY--$startRadius--$endRadius")
    }

    private fun getGlobalVisibleRectOfDecorView(view: View, rect: Rect) {
        view.getGlobalVisibleRect(rect)
//        Log.e(TAG, "getGlobalVisibleRect：$rect")
        view.getDrawingRect(rect)
//        Log.e(TAG, "getDrawingRect：$rect")
        rootView?.offsetDescendantRectToMyCoords(view, rect)
//        Log.e(TAG, "offset：$rect")
    }

    private fun getMaxRadius(): Float {
        val leftTop = RectF(0.0f, 0.0f, startX + startRadius, startY + startRadius)
        val rightTop = RectF(leftTop.right, 0.0f, rootView!!.right.toFloat(), leftTop.bottom)
        val leftBottom = RectF(0.0f, leftTop.bottom, leftTop.right, rootView!!.bottom.toFloat())
        val rightBottom = RectF(leftTop.right, leftTop.bottom, rootView!!.right.toFloat(), leftBottom.bottom)
//        Log.e(TAG, "getMaxRadius：$leftTop--$rightTop--$leftBottom--$rightBottom")

        val leftTopDiagonal =
            Math.sqrt(Math.pow(leftTop.width().toDouble(), 2.0) + Math.pow(leftTop.height().toDouble(), 2.0))
        val rightTopDiagonal =
            Math.sqrt(Math.pow(rightTop.width().toDouble(), 2.0) + Math.pow(rightTop.height().toDouble(), 2.0))
        val leftBottomDiagonal =
            Math.sqrt(Math.pow(leftBottom.width().toDouble(), 2.0) + Math.pow(leftBottom.height().toDouble(), 2.0))
        val rightBottomDiagonal =
            Math.sqrt(Math.pow(rightBottom.width().toDouble(), 2.0) + Math.pow(rightBottom.height().toDouble(), 2.0))
        return Math.max(Math.max(leftTopDiagonal, rightTopDiagonal), Math.max(leftBottomDiagonal, rightBottomDiagonal))
            .toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        Log.e(TAG, "onDraw  ->  $currentRadius")
        if (currentRadius > 0 || onClickViewRect != null) {

            paint?.style = Paint.Style.FILL
            paint?.color = Color.CYAN
            paint?.shader = null
            var saveCount = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                canvas.saveLayer(0.0f, 0.0f, width.toFloat(), height.toFloat(), paint)
            } else {
                canvas.saveLayer(0.0f, 0.0f, width.toFloat(), height.toFloat(), paint, Canvas.ALL_SAVE_FLAG)
            }
//            val saveCount =
//                    canvas.saveLayer(RectF(0.0f, 0.0f, width.toFloat(), height.toFloat()), paint, Canvas.ALL_SAVE_FLAG)
            if (srcBackground?.isRecycled == false) {
                canvas.drawBitmap(srcBackground, 0.0f, 0.0f, paint)
            }

            if (currentRadius > 0) {
                paint?.color = Color.BLACK
                paint?.xfermode = xfermode
                canvas.drawCircle(startX, startY, currentRadius, paint)
                paint?.style = Paint.Style.STROKE
                paint?.strokeWidth = 16.0f
                paint?.shader =
                        RadialGradient(
                            startX,
                            startY,
                            currentRadius,
                            startColor,
                            endColor,
                            Shader.TileMode.MIRROR
                        )

                canvas.drawArc(
                    RectF(
                        startY - currentRadius,
                        startY - currentRadius,
                        startX + currentRadius,
                        startY + currentRadius
                    ),
                    0.0f, 360.0f, true, paint!!
                )
                paint?.xfermode = null
            }else if(onClickViewRect != null){
                paint?.color = Color.BLACK
                paint?.xfermode = xfermode
                canvas.drawRect(onClickViewRect,paint)
                paint?.xfermode = null
            }
            canvas.restoreToCount(saveCount)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        Log.e(TAG, "onTouchEvent")
        return true
    }

    fun updateBackground() {
        if (srcBackground?.isRecycled == false) {
            srcBackground?.recycle()
        }
        rootView?.let {
            //            it.isDrawingCacheEnabled = true
//            it.buildDrawingCache()
//            srcBackground = it.drawingCache
//            it.destroyDrawingCache()
//            it.isDrawingCacheEnabled = false

            it.measure(
                MeasureSpec.makeMeasureSpec(it.layoutParams.width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(it.layoutParams.height, MeasureSpec.EXACTLY)
            )
            val bitmap = Bitmap.createBitmap(it.width, it.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            it.draw(canvas)
            srcBackground = bitmap
        }
    }

    fun attachToRootView() {
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        rootView?.addView(this)
//        val wLayoutParams = WindowManager.LayoutParams()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            wLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
//        } else {
//            wLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE
//        }
//        wLayoutParams.format = PixelFormat.RGBA_8888
//        wLayoutParams.width = rootView?.width?:0
//        wLayoutParams.height = rootView?.height?:0
//        windowManager?.addView(this,wLayoutParams)
        invalidate()
    }

    fun startAnim() {
        releaseRippleAnim()
        animRipple = ValueAnimator.ofFloat(startRadius, endRadius)
        animRipple?.let {
            it.duration = duration
            it.interpolator = DecelerateInterpolator()
            animUpdateListener = RippleAnimUpdateListener()
            animListener = RippleAnimListener()
            it.addUpdateListener(animUpdateListener)
            it.addListener(animListener)
            it.start()
        }
    }

    private fun detachFromRootView() {
        Log.e(TAG, "detachFromRootView")
//        if(windowManager != null){
//            windowManager?.removeView(this)
//            windowManager = null
//        }
        if (rootView != null) {
            rootView?.removeView(this)
            rootView = null
        }
        if (srcBackground != null) {
            if (srcBackground?.isRecycled == false) {
                srcBackground?.recycle()
            }
            srcBackground = null
        }
        if (paint != null) {
            paint!!.xfermode = null
            paint = null
        }
    }

    private fun releaseRippleAnim() {
        Log.e(TAG, "releaseRippleAnim")
        currentRadius = 0.0f
        onClickViewRect = null
        animRipple?.let {
            it.removeUpdateListener(animUpdateListener)
            it.removeListener(animListener)
            animUpdateListener = null
            animListener = null
            it.end()
            it.cancel()
        }
        animRipple = null
    }

    inner class RippleAnimUpdateListener : ValueAnimator.AnimatorUpdateListener {
        override fun onAnimationUpdate(animation: ValueAnimator?) {
            animation?.let {
                currentRadius = it.animatedValue as Float
//                Log.e(TAG, "onAnimationUpdate  $currentRadius")
//                postInvalidate()
                invalidate()
            }
        }

    }

    inner class RippleAnimListener : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
        }

        override fun onAnimationEnd(animation: Animator?) {
            Log.e(TAG, "onAnimationEnd")
            releaseRippleAnim()
            detachFromRootView()
        }

        override fun onAnimationCancel(animation: Animator?) {
            Log.e(TAG, "onAnimationCancel")
        }

        override fun onAnimationStart(animation: Animator?) {
            Log.e(TAG, "onAnimationStart")
        }
    }
}