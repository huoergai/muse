package com.huoergai.muse

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import com.huoergai.muse.tools.TmdbUtil
import com.huoergai.muse.ui.theme.MuseTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MuseTheme {
                Surface {
                    Box {
                        Text(TmdbUtil.hello())
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        Log.d("Muse", "it's ${BuildConfig.TMDB_KEY}")
    }

    companion object
}