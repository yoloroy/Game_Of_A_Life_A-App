package com.yoloroy.gameoflife.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import com.yoloroy.gameoflife.R

@Composable
fun ProfileImageWithExp(diameter: Dp, imageUrl: String?, exp: Int, maxExp: Int) {
    Box(
        modifier = Modifier.size(diameter)
    ) {
        GlideImage(
            imageModel = imageUrl,
            placeHolder = Icons.Sharp.AccountCircle,
            previewPlaceholder = R.drawable.img_profile_placeholder,
            modifier = Modifier
                .padding(6.dp)
                .fillMaxSize()
        )
        CircularProgressIndicator(
            progress = exp.toFloat() / maxExp,
            modifier = Modifier
                .fillMaxSize()
                .scale(-1f),
            color = MaterialTheme.colors.secondary
        )
    }
}