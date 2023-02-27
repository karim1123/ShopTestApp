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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
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
import karim.gabbasov.ui.theme.SaleBackground
import karim.gabbasov.ui.theme.White
import karim.gabbasov.ui.ui.util.NetworkImage

@Composable
internal fun FlashSaleProductsSection(
    modifier: Modifier = Modifier,
    latestProducts: List<ProductEntity>
) {
    Column(
        modifier = modifier
    ) {
        CatalogSectionTitle(
            modifier = modifier.padding(start = 12.dp, end = 12.dp, bottom = 8.dp),
            title = stringResource(R.string.flash_sale_title)
        )
        LazyRow(
            content = {
                item { Spacer(modifier = Modifier.padding(start = 12.dp)) }
                items(latestProducts) { product ->
                    DiscountedProduct(
                        modifier = Modifier
                            .height(220.dp)
                            .width(160.dp),
                        product = product
                    )
                }
            }
        )
    }
}

@Composable
private fun DiscountedProduct(
    modifier: Modifier = Modifier,
    product: ProductEntity,
) {
    Card(
        modifier = modifier,
        shape = OnlineShopTheme.shapes.small,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        var favorite by rememberSaveable { mutableStateOf(false) }

        Box {
            NetworkImage(url = product.imageUrl)
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopStart
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Image(
                        modifier = Modifier.size(26.dp),
                        painter = painterResource(R.drawable.saller),
                        contentDescription = null
                    )
                    Card(
                        modifier = Modifier
                            .wrapContentSize(),
                        shape = RoundedCornerShape(28.dp),
                        colors = CardDefaults.cardColors(containerColor = SaleBackground)
                    ) {
                        Text(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(start = 6.dp, end = 6.dp, top = 2.dp, bottom = 2.dp),
                            text = stringResource(
                                R.string.sale_value, product.discount ?: 0, "%"
                            ),
                            color = White,
                            style = OnlineShopTheme.typography.saleTitle
                        )
                    }
                }
            }
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
                                .wrapContentSize()
                                .padding(start = 6.dp, end = 6.dp, top = 2.dp, bottom = 2.dp),
                            text = product.category,
                            color = CategoryText,
                            style = OnlineShopTheme.typography.mediumCategoryTitle
                        )
                    }
                    Text(
                        modifier = Modifier
                            .height(36.dp)
                            .width(80.dp),
                        textAlign = TextAlign.Start,
                        maxLines = 2,
                        overflow = TextOverflow.Clip,
                        text = product.name,
                        style = OnlineShopTheme.typography.mediumProductPrice,
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
                            text = "$${product.price}",
                            color = Color.White,
                            style = OnlineShopTheme.typography.mediumProductPrice
                        )
                        Row(
                            modifier = Modifier.wrapContentHeight(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .clickable { }
                                    .size(28.dp)
                                    .padding(end = 6.dp)
                                    .clickable { favorite = !favorite },
                                imageVector = if (favorite) {
                                    ImageVector.vectorResource(R.drawable.favorite)
                                } else {
                                    ImageVector.vectorResource(R.drawable.unfovorite)
                                },
                                contentDescription = stringResource(R.string.favorite_description)
                            )
                            Image(
                                modifier = Modifier
                                    .clickable { }
                                    .size(35.dp),
                                imageVector = ImageVector.vectorResource(R.drawable.plus),
                                contentDescription = product.name
                            )
                        }
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.padding(end = 12.dp))
}
