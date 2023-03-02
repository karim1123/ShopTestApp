package karim.gabbasov.catalog.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.catalog.R
import karim.gabbasov.catalog.ui.utils.CatalogCategories
import karim.gabbasov.ui.theme.OnlineShopTheme

@Composable
internal fun CatalogCategories() {
    LazyRow(
        content = {
            items(CatalogCategories.getCatalogCategories()) { category ->
                Category(
                    modifier = Modifier.padding(start = 10.dp),
                    category = category
                )
            }
        }
    )
}

@Composable
private fun Category(
    modifier: Modifier = Modifier,
    category: CatalogCategories
) {
    Column(
        modifier = modifier
            .clickable { }
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(42.dp)
                .padding(bottom = 10.dp),
            imageVector = ImageVector.vectorResource(category.drawableResId),
            contentDescription = stringResource(category.stringResId)
        )
        Text(
            text = stringResource(category.stringResId),
            style = OnlineShopTheme.typography.categoryTitle,
            color = OnlineShopTheme.colors.categoryTitle
        )
    }
    Spacer(modifier = Modifier.padding(start = 8.dp))
}

@Composable
internal fun CatalogSectionTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Row(
        modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = OnlineShopTheme.colors.sectionTitle,
            style = OnlineShopTheme.typography.sectionTitle
        )
        Text(
            modifier = Modifier.clickable { },
            text = stringResource(R.string.view_all_title),
            color = OnlineShopTheme.colors.categoryTitle,
            style = OnlineShopTheme.typography.viewAllTitle
        )
    }
}

@Preview
@Composable
private fun PreviewCategory() {
    Category(
        category = CatalogCategories.CARS
    )
}

@Preview
@Composable
private fun PreviewCatalogCategories() {
    CatalogCategories
}

@Preview
@Composable
private fun PreviewCatalogSectionTitle() {
    CatalogSectionTitle(
        title = "Latest"
    )
}
