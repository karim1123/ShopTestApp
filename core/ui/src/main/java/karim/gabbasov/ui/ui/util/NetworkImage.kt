package karim.gabbasov.ui.ui.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        modifier = modifier,
        imageModel = { url },
        requestBuilder = {
            Glide
                .with(LocalContext.current)
                .asBitmap()
                .centerCrop()
                .placeholder(R.drawable.image_placeholder)
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(withCrossFade())
        },
        imageOptions = ImageOptions(
            contentScale = contentScale
        ),
        failure = {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.image_placeholder),
                contentDescription = stringResource(R.string.image_load_error)
            )
        }
    )
}

@Preview
@Composable
private fun PreviewNetworkImage() {
    NetworkImage(
        modifier = Modifier
            .height(220.dp)
            .width(160.dp),
        url = "",
    )
}
