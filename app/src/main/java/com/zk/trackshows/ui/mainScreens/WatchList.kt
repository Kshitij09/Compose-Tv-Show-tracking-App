package com.zk.trackshows.ui.mainScreens

import androidx.compose.animation.*
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.zk.trackshows.components.ShowCard
import com.zk.trackshows.ui.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@Composable

fun WatchList(viewModel: MainViewModel) {
    AnimatedVisibility(
            visible = true,
            enter = slideInVertically(
                    // Start the slide from 40 (pixels) above where the content is supposed to go, to
                    // produce a parallax effect
                    initialOffsetY = { -40 }
            ) + expandVertically(
                    expandFrom = Alignment.Top
            ) + fadeIn(initialAlpha = 0.3f),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        // Content that needs to appear/disappear goes here:
        WatchListContent(viewModel)
    }
}

@ExperimentalCoroutinesApi
@FlowPreview
@Composable
private fun WatchListContent(viewModel: MainViewModel) {
    val shows = viewModel.shows
    // ScrollableColumn is a composable that adds the ability to scroll through the
    // child views
    ScrollableColumn {
        // Column is a composable that places its children in a vertical sequence. You
        // can think of it similar to a LinearLayout with the vertical orientation.
        Column() {
            shows?.forEach { show ->
                // Card composable is a predefined composable that is meant to represent
                // the card surface as specified by the Material Design specification. We
                // also configure it to have rounded corners and apply a modifier.
                ShowCard(
                        show, modifier = Modifier.fillMaxWidth()
                        .clickable(onClick = {
                            viewModel.tapShowEvent(show.id)
                        })
                )
            }
        }
    }
}
