package karim.gabbasov.ui.ui.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import karim.gabbasov.ui.R

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String,
    contentScale: ContentScale = ContentScale.Crop,
) {
    GlideImage(
        imageModel = { url },
        requestBuilder = {
            Glide
                .with(LocalContext.current)
                .asBitmap()
                .centerCrop()
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(withCrossFade())
        },
        imageOptions = ImageOptions(
            contentScale = contentScale
        ),
        failure = {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.image_load_error)
                )
            }
        }
    )
}
