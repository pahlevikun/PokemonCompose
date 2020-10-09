package id.pahlevikun.compose.common.extensions

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity

@Stable
fun Modifier.visible(visibility: Boolean): Modifier {
    return if (visibility) {
        this.then(drawOpacity(1f))
    } else {
        this.then(drawOpacity(0f))
    }
}