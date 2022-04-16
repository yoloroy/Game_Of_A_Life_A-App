package com.yoloroy.gameoflife.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.yoloroy.gameoflife.R

val pressStartFonts = FontFamily(Font(R.font.press_start_2p_regular))

val playFonts = FontFamily(
    Font(R.font.play_regular, FontWeight.Normal),
    Font(R.font.play_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = playFonts,
    h1 = TextStyle(fontFamily = pressStartFonts, fontSize = 96.sp, letterSpacing = (-1.5).sp),
    h2 = TextStyle(fontFamily = pressStartFonts, fontSize = 60.sp, letterSpacing = (-0.5).sp),
    h3 = TextStyle(fontFamily = pressStartFonts, fontSize = 48.sp, letterSpacing = 0.sp),
    h4 = TextStyle(fontFamily = pressStartFonts, fontSize = 36.sp, letterSpacing = (-1).sp),
    h5 = TextStyle(fontFamily = pressStartFonts, fontSize = 26.sp, letterSpacing = (-1).sp),
    h6 = TextStyle(fontFamily = pressStartFonts, fontSize = 18.sp, letterSpacing = (-1).sp),
    subtitle1 = TextStyle(fontFamily = playFonts, fontSize = 24.sp, letterSpacing = 1.sp),
    subtitle2 = TextStyle(fontFamily = playFonts, fontSize = 20.sp, letterSpacing = 1.5.sp)
)