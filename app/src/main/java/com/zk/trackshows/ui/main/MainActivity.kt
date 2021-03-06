package com.zk.trackshows.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.lifecycleScope
import com.zk.trackshows.AppScreens
import com.zk.trackshows.common.InfoLogger.logMessage
import com.zk.trackshows.model.Show
import com.zk.trackshows.repository.network.api.TvShowsService
import com.zk.trackshows.ui.details.DetailActivity
import com.zk.trackshows.ui.theme.TrackShowsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

sealed class AppHomeInteractionEvents {
    data class NavigateToDetailScreen(val show: Show) : AppHomeInteractionEvents()
}

sealed class MainScreenInteractionEvents {
    data class NavigateTo(val route: String) : MainScreenInteractionEvents()
    data class AddToMyWatchlist(val show: Show) : MainScreenInteractionEvents()
    data class RemoveFromMyWatchlist(val show: Show) : MainScreenInteractionEvents()
}

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    @ExperimentalCoroutinesApi
    @FlowPreview
    @VisibleForTesting
    private val viewModel: MainViewModel by viewModels()

    @Inject lateinit var service: TvShowsService

    @InternalCoroutinesApi
    @FlowPreview
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TrackShowsTheme {
                MainScreen(viewModel = viewModel, service = service)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.navigationEvent.collect { screen ->
                when (screen) {
                    is AppScreens.Details -> {
                        startActivity(
                            DetailActivity.newIntent(this@MainActivity, screen.show)
                        )
                    }
                    is AppScreens.Search -> logMessage("navigationEvent search screen")
                    is AppScreens.MainScreen -> logMessage("No navigationEvent")
                }
            }
        }
    }
}
