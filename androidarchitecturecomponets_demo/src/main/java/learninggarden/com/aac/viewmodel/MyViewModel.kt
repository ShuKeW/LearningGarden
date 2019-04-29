package learninggarden.com.aac.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.view.View

/**
 *  @date 2019/3/14   11:15 AM
 *  @author weishukai
 *  @describe
 */
class MyViewModel : ViewModel() {
    val result: ObservableField<Int> = ObservableField()

//    fun MyViewModel() {}

    fun onAddClick(view: View) {
        var value = result.get()
        if (value != null) {
            ++value
        } else {
            value = 0
        }
        result.set(value)
    }
}