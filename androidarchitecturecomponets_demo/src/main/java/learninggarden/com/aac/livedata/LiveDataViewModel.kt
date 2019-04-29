package learninggarden.com.aac.livedata

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View

/**
 *  @date 2019/3/14   3:39 PM
 *  @author weishukai
 *  @describe
 */

class LiveDataViewModel : ViewModel() {

    private var result: MutableLiveData<Int>? = null

    private lateinit var application: Application

    fun LiveDataViewModel(application: Application) {
        this.application = application
    }

    fun getResult(): MutableLiveData<Int> {
        if (result == null) {
            result = MutableLiveData()
        }
        return result!!
    }

    fun onAddClick(view: View) {
        var value = getResult().value
        if (value != null) {
            ++value
        } else {
            value = 0
        }
        getResult().value = value
    }
}