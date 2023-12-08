package com.myproject.foodies.ui.theme.modifier

import android.graphics.BlurMaskFilter
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.annotation.Px
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.shadow(
    color: Color = Color.Black,
    borderRadius: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    spread: Dp = 0.dp
) = this.then(
    Modifier.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = spread.toPx()
            val leftPixel = (0f - spreadPixel) + offsetX.toPx()
            val topPixel = (0f - spreadPixel) + offsetY.toPx()
            val rightPixel = this.size.width + spreadPixel
            val bottomPixel = this.size.height + spreadPixel

            frameworkPaint.maskFilter =
                (BlurMaskFilter(10.dp.toPx(), BlurMaskFilter.Blur.NORMAL))

            frameworkPaint.color = color.toArgb()
            it.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint
            )
        }
    }
)

fun shadow(): CustomShadowParams {
    return CustomShadowParams(
        name = "Shadow",
        layer = listOf(
            Shadow(
                dX = 0f,
                dY = 2f,
                radius = 9f,
                color = Color.Black.hashCode(),
                colorAlpha = 0.14f
            )
        )
    )
}

data class CustomShadowParams(
    val name: String,
    val layer: List<Shadow>
)

data class Shadow(
    @Px val dX: Float,
    @Px val dY: Float,
    @Px val radius: Float,
    @ColorInt val color: Int,
    @FloatRange(from = 0.0, to = 1.0) val colorAlpha: Float
)
