package learninggarden.com.aac.viewmodel

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import learninggarden.com.aac.R
import learninggarden.com.aac.databinding.ActivityViewmodelBinding

/**
 *  @date 2019/3/14   11:10 AM
 *  @author weishukai
 *  @describe
 */
class ViewModelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding = DataBindingUtil.setContentView<ActivityViewmodelBinding>(this, R.layout.activity_viewmodel)
        val viewModel = ViewModelProvider.NewInstanceFactory().create(MyViewModel::class.java)
        viewModel.result.set(0)
        dataBinding.viewModel = viewModel
    }
}