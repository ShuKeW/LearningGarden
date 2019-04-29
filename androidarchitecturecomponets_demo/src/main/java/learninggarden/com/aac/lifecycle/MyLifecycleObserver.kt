package learninggarden.com.aac.lifecycle

import android.arch.lifecycle.*
import android.util.Log

/**
 *  @date 2019/3/11   4:06 PM
 *  @author weishukai
 *  @describe
 */
const val TAG = "LifecycleObserver"

class MainActivityLifecycleObserver : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.e(TAG, "onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.e(TAG, "onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.e(TAG, "onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.e(TAG, "onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.e(TAG, "onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestory() {
        Log.e(TAG, "onDestory")
    }

}

class LifecycleFragmentLifecycleObserver : GenericLifecycleObserver {
    override fun onStateChanged(source: LifecycleOwner?, event: Lifecycle.Event?) {
        Log.e(TAG, event.toString())
    }

}