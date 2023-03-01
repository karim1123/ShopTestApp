package karim.gabbasov.profile.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import karim.gabbasov.profile.R
import karim.gabbasov.ui.theme.AccountQuestion
import karim.gabbasov.ui.theme.OnlineShopTheme

@Composable
internal fun ImagePicker(
    onChangeAvatar: (String) -> Unit
) {
    var selectImages by remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            selectImages = it
        }

    Text(
        modifier = Modifier.clickable { galleryLauncher.launch("image/*") },
        text = stringResource(R.string.change_photo_title),
        color = AccountQuestion,
        style = OnlineShopTheme.typography.bodySmall
    )

    if (selectImages != null) {
        onChangeAvatar(selectImages.toString())
    }
}

@Preview
@Composable
private fun PreviewImagePicker() {
    ImagePicker({})
}
