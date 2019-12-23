package com.achek.hackernews.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.achek.hackernews.R
import com.achek.hackernews.presentation.view.splash.SplashScreen
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = object: SupportAppNavigator(this, R.id.main_container) {
        override fun applyCommands(commands: Array<out Command>?) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as NewsApp).componentManager
            .appComponent
            .inject(this)
        navigator.applyCommands(arrayOf(Replace(SplashScreen())))
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}
