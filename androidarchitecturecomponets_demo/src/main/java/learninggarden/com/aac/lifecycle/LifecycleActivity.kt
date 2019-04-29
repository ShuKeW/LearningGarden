package learninggarden.com.aac.lifecycle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import learninggarden.com.aac.R

class LifecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
        lifecycle.addObserver(MainActivityLifecycleObserver())
    }
}
