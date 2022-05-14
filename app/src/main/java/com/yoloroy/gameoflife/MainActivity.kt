package com.yoloroy.gameoflife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.presentation.components.Navigation
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme
import com.yoloroy.gameoflife.presentation.util.Screen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

@ExperimentalMaterialApi
@AndroidEntryPoint
@ExperimentalTime
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLoginFlow = flow {
            emit(Resource.Loading())
            delay(1.seconds)
            emit(Resource.Success(true))
        }

        setContent {
            val navController = rememberNavController()
            val isLogin by isLoginFlow.collectAsState(initial = Resource.Loading())

            GameOfLifeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    when (isLogin) {
                        Resource.Success(true) -> {
                            val startDestination = if (isLogin.data!!)
                                Screen.DreamsScreen.route else
                                Screen.LoginScreen.route

                            Navigation(navController, startDestination)
                        }
                        is Resource.Loading -> {
                            Box(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(
                                    text = "GOAL",
                                    style = MaterialTheme.typography.h3,
                                    color = MaterialTheme.colors.primary,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .padding(top = 16.dp)
                                        .wrapContentWidth()
                                        .wrapContentHeight()
                                        .align(Alignment.Center)
                                )
                            }
                        }
                        else -> TODO()
                    }
                }
            }
        }
    }
}
