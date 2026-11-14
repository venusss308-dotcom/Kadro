package me.ibrahim.moviesapp.compose.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.runtime.Composable
import me.ibrahim.moviesapp.compose.core.BaseActivity
import me.ibrahim.moviesapp.compose.presentation.login.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {

    @Composable
    override fun InitView() {
        SplashScreen {
            val loginIntent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }
    }
}