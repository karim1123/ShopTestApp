package karim.gabbasov.catalog.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import karim.gabbasov.catalog.R
import karim.gabbasov.catalog.ui.CatalogSectionTitle
import karim.gabbasov.model.data.shop.ProductEntity
import karim.gabbasov.ui.theme.CategoryBackground
import karim.gabbasov.ui.theme.CategoryText
import karim.gabbasov.ui.theme.OnlineShopTheme
import karim.gabbasov.ui.ui.util.NetworkImage

@Composable
internal fun LatestProductsSection(
    modifier: Modifier = Modifier,
    latestProducts: List<ProductEntity>
) {
    Column(
        modifier = modifier
    ) {
        CatalogSectionTitle(
            modifier = modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp),
            title = stringResource(R.string.latest_title)
        )
        LazyRow(
            content = {
                item { Spacer(modifier = Modifier.padding(start = 12.dp)) }
                items(latestProducts) { product ->
                    LatestProduct(
                        modifier = Modifier
                            .height(150.dp)
                            .width(100.dp),
                        product = product
                    )
                }
            }
        )
    }
}

@Composable
private fun LatestProduct(
    modifier: Modifier = Modifier,
    product: ProductEntity,
) {
    Card(
        modifier = modifier,
        shape = OnlineShopTheme.shapes.small,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Box() {
            NetworkImage(url = product.imageUrl)
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {
                Column(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(bottom = 6.dp),
                        shape = RoundedCornerShape(6.dp),
                        colors = CardDefaults.cardColors(containerColor = CategoryBackground)
                    ) {
                        Text(
                            modifier = Modifier
                                .height(12.dp)
                                .wrapContentWidth()
                                .padding(start = 6.dp, end = 6.dp, top = 2.dp, bottom = 2.dp),
                            text = product.category,
                            color = CategoryText,
                            style = OnlineShopTheme.typography.smallCategoryTitle
                        )
                    }
                    Text(
                        modifier = Modifier
                            .height(20.dp)
                            .width(80.dp),
                        textAlign = TextAlign.Start,
                        maxLines = 2,
                        overflow = TextOverflow.Clip,
                        text = product.name,
                        style = OnlineShopTheme.typography.smallProductName,
                        color = Color.White
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(
                                R.string.latest_product_price_value, product.price.toInt()
                            ),
                            style = OnlineShopTheme.typography.smallProductPrice,
                            color = Color.White
                        )
                        Image(
                            modifier = Modifier
                                .clickable { }
                                .size(20.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.plus),
                            contentDescription = product.name
                        )
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.padding(end = 12.dp))
}
