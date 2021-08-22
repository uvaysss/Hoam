package com.solvo.hoam.presentation.about

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.solvo.hoam.BuildConfig
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by uvays on 24/01/2021.
 */

@Parcelize
data class AboutKey(private val placeholder: Int = 0) : DefaultFragmentKey() {
    override fun instantiateFragment(): Fragment = AboutFragment.newInstance()
}

class AboutFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment = AboutFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            screen()
        }
    }

    @Preview
    @Composable
    fun screen() {
        MaterialTheme {
            ProvideWindowInsets {
                Surface(color = Color(0xFF444a62)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .statusBarsPadding(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            text = "Version ${BuildConfig.VERSION_NAME}",
                            color = Color(0xFFE0E0E0)
                        )
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun title() {
        var expanded by remember { mutableStateOf(false) }

        Column(Modifier.clickable { expanded = !expanded }) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                text = "Version ${BuildConfig.VERSION_NAME}",
                color = Color(0xFFE0E0E0)
            )

            if (expanded) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    text = "Version ${BuildConfig.VERSION_NAME}",
                    color = Color(0xFFE0E0E0)
                )
            }
        }
    }
}