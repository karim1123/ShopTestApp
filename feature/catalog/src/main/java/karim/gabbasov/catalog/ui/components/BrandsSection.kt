package karim.gabbasov.catalog.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import karim.gabbasov.catalog.R
import karim.gabbasov.catalog.ui.CatalogSectionTitle
import karim.gabbasov.ui.theme.OnlineShopTheme

@Composable
internal fun BrandsSection(
    modifier: Modifier = Modifier
) {
    val images =
        listOf(R.drawable.lego, R.drawable.nintendo, R.drawable.nvidia, R.drawable.playstation)

    Column(
        modifier = modifier
    ) {
        CatalogSectionTitle(
            modifier = modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp),
            title = stringResource(R.string.brands_title)
        )
        LazyRow(
            content = {
                item { Spacer(modifier = Modifier.padding(start = 12.dp)) }
                items(images) { image ->
                    Brands(
                        modifier = Modifier
                            .height(130.dp)
                            .width(130.dp),
                        image = painterResource(image)
                    )
                }
            }
        )
    }
}

@Composable
private fun Brands(
    modifier: Modifier = Modifier,
    image: Painter
) {
    Card(
        modifier = modifier,
        shape = OnlineShopTheme.shapes.small,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Image(
            painter = image,
            contentDescription = null
        )
    }
    Spacer(modifier = Modifier.padding(end = 12.dp))
}
