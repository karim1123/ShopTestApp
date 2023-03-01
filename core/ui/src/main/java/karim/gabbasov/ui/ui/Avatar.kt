package karim.gabbasov.ui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import karim.gabbasov.ui.R

@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    url: String
) {
    GlideImage(
        modifier = modifier,
        imageModel = { url },
        requestBuilder = {
            Glide
                .with(LocalContext.current)
                .asBitmap()
                .circleCrop()
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(BitmapTransitionOptions.withCrossFade())
                .placeholder(R.drawable.profile_placeholder)
        },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop
        ),
        failure = {
            Image(
                modifier = modifier,
                imageVector = ImageVector.vectorResource(R.drawable.profile_placeholder),
                contentDescription = null
            )
        }
    )
}

@Preview
@Composable
private fun PreviewAvatar() {
    Avatar(
        modifier = Modifier.size(40.dp),
        url = ""
    )
}
