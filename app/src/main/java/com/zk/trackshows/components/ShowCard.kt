package com.zk.trackshows.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zk.trackshows.model.Show

@Composable
fun ShowCard(show: Show, modifier: Modifier) {

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        NetworkImageComponentPicasso(
                url = show.poster_path,
                modifier = Modifier
                        .fillMaxHeight()
                        .padding(8.dp)
                        .height(150.dp)
                        .clip(RoundedCornerShape(10.dp))
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                    text = show.name,
                    fontSize = 15.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(0.dp, 4.dp, 0.dp, 0.dp)
            )
            Text(
                    text = show.original_name,
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    //modifier = Modifier.padding(4.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(
                        color = MaterialTheme.colors.primary,
                        backgroundColor = MaterialTheme.colors.secondary,
                        progress = 0.3f,
                        modifier = Modifier.height(12.dp)
                )
                Text(
                        maxLines = 1,
                        text = "66/73",
                        fontSize = 10.sp,
                        modifier = Modifier.padding(4.dp)
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                        elevation = 5.dp,
                        onClick = {}) {
                    Text(text = "Episode Info")
                }
                Text(
                        text = "7 left",
                        fontSize = 10.sp,
                        modifier = Modifier.padding(4.dp)
                )
            }

        }
    }
}