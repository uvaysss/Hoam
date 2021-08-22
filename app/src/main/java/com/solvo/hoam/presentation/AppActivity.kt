package com.solvo.hoam.presentation

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.children
import com.solvo.hoam.R
import com.solvo.hoam.databinding.ActivityAppBinding
import com.solvo.hoam.presentation.core.hideKeyboard
import com.solvo.hoam.presentation.core.navigation.FadeAnimationFragmentStateChanger
import com.solvo.hoam.presentation.launch.LaunchKey
import com.zhuinden.simplestack.History
import com.zhuinden.simplestack.SimpleStateChanger
import com.zhuinden.simplestack.navigator.Navigator
import com.zhuinden.simplestackextensions.services.DefaultServiceProvider

/**
 * Created by uvays on 08/01/2021.
 */

class AppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_App)

        binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewAppRoot.setOnApplyWindowInsetsListener { view, insets ->
            var consumed = false

            (view as ViewGroup).children.forEach { child ->
            val childResult = child.dispatchApplyWindowInsets(insets)
                if (childResult.isConsumed) {
                    consumed = true
                }
            }

            if (consumed) insets.consumeSystemWindowInsets() else insets
        }

        val fragmentStateChanger = FadeAnimationFragmentStateChanger(
                supportFragmentManager,
                R.id.viewAppRoot
        )

        Navigator
                .configure()
                .setScopedServices(DefaultServiceProvider())
                .setStateChanger(SimpleStateChanger {
                    hideKeyboard()
                    fragmentStateChanger.handleStateChange(it)
                })
                .install(this, binding.viewAppRoot, History.of(LaunchKey()))
    }

    override fun onBackPressed() {
        if (!Navigator.onBackPressed(this)) {
            super.onBackPressed()
        }
    }
}