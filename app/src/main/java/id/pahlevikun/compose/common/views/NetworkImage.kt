package id.pahlevikun.compose.common.views

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.skydoves.frescomposable.FrescoImage

/**
 * A wrapper around [FrescoImage] setting a default [contentScale]
 * and loading indicator for loading images.
 */
@Composable
fun NetworkImage(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    FrescoImage(
        imageUrl = url,
        modifier = modifier,
        contentScale = contentScale,
        loading = {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val indicator = createRef()
                CircularProgressIndicator(
                    modifier = Modifier.constrainAs(indicator) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }
        },
        failure = {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val message = createRef()
                Text(
                    text = "image request failed.",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.constrainAs(message) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }
        }
    )
}
