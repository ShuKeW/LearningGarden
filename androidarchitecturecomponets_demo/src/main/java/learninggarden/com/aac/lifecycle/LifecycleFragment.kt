package learninggarden.com.aac.lifecycle

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import learninggarden.com.aac.R

/**
 *  @date 2019/3/11   4:03 PM
 *  @author weishukai
 *  @describe
 */
class LifecycleFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(LifecycleFragmentLifecycleObserver())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lifecycle, container, false)
    }
}