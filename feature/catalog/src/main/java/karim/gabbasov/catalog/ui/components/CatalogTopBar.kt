package karim.gabbasov.catalog.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.catalog.R
import karim.gabbasov.ui.theme.Black
import karim.gabbasov.ui.theme.OnlineShopTheme

@Composable
internal fun CatalogTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 40.dp, start = 12.dp, end = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.baseline_densit),
            contentDescription = null
        )
        Row {
            Text(
                text = "${stringResource(R.string.first_part_of_top_bar)} ",
                style = OnlineShopTheme.typography.appBarTitle,
                color = Black
            )
            Text(
                text = stringResource(R.string.second_part_of_top_bar),
                style = OnlineShopTheme.typography.appBarTitle,
                color = OnlineShopTheme.colors.enabledButton
            )
        }
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(end = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(R.drawable.avatar_example),
                contentDescription = null
            )
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .clickable { },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.location_title),
                    color = Black
                )
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.reveal),
                    contentDescription = stringResource(R.string.location_title)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCatalogTopBar() {
    CatalogTopBar()
}
