package karim.gabbasov.product_details.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.product_details.R
import karim.gabbasov.ui.theme.AdditionalBarBackground

@Composable
internal fun AdditionalBar(modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = AdditionalBarBackground)
    ) {
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            modifier = Modifier.size(16.dp),
            imageVector = ImageVector.vectorResource(R.drawable.unfavorite),
            contentDescription = stringResource(R.string.add_to_favorite_body)
        )
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.divider),
            contentDescription = null
        )
        Image(
            modifier = Modifier.size(16.dp),
            imageVector = ImageVector.vectorResource(R.drawable.share),
            contentDescription = stringResource(R.string.add_to_favorite_body)
        )
    }
}

@Preview
@Composable
private fun PreviewAdditionalBar() {
    AdditionalBar(
        Modifier
            .height(96.dp)
            .width(42.dp)
            .padding(end = 12.dp)
    )
}
