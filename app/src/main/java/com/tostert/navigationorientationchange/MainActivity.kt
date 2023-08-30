package com.tostert.navigationorientationchange

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tostert.navigationorientationchange.screenA.ScreenA
import com.tostert.navigationorientationchange.screenB.ScreenB
import com.tostert.navigationorientationchange.screenB.ScreenBViewModel
import dagger.hilt.android.AndroidEntryPoint

private object Screens {
    const val screenA = "screenA"
    const val screenB = "screenB"
}

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            navController.addOnDestinationChangedListener(DestinationChangedListenerForOrientation())
            NavHost(
                navController = navController,
                startDestination = Screens.screenA,
            ) {
                composable(route = Screens.screenA) {
                    ScreenA {
                        navController.navigate(Screens.screenB)
                    }
                }
                composable(
                    route = Screens.screenB,
                ) {
                    val viewModel = hiltViewModel<ScreenBViewModel>()
                    ScreenB(
                        viewModel = viewModel,
                        onBack = navController::navigateUp,
                    )
                }
            }
        }
    }

    inner class DestinationChangedListenerForOrientation : NavController.OnDestinationChangedListener {
        @SuppressLint("SourceLockedOrientationActivity")
        override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
            requestedOrientation = if (destination.route == Screens.screenB) {
                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            } else {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }

    }
}
