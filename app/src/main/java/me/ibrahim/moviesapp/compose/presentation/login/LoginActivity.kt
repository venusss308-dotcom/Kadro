package me.ibrahim.moviesapp.compose.presentation.login

import android.content.Intent
import androidx.compose.runtime.Composable
import me.ibrahim.moviesapp.compose.core.BaseActivity
import me.ibrahim.moviesapp.compose.presentation.main.MainActivity

class LoginActivity : BaseActivity() {
    @Composable
    override fun InitView() {
        LoginScreen {
            val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }
    }
}