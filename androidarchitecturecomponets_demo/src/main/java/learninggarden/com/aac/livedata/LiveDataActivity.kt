package learninggarden.com.aac.livedata

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import learninggarden.com.aac.R
import learninggarden.com.aac.databinding.ActivityLivedataBinding

/**
 *  @date 2019/3/14   3:37 PM
 *  @author weishukai
 *  @describe
 */
class LiveDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding = DataBindingUtil.setContentView<ActivityLivedataBinding>(this, R.layout.activity_livedata)

        val viewModelProvider =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
        val viewModel = viewModelProvider.get(LiveDataViewModel::class.java)
        viewModel.getResult().observe(this, Observer<Int> {
            dataBinding.text.text = "$it"
        })
        dataBinding.viewModel = viewModel
    }
}