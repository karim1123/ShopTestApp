package karim.gabbasov.product_details.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.product_details.R
import karim.gabbasov.product_details.ui.UIState
import karim.gabbasov.ui.theme.AccountQuestion
import karim.gabbasov.ui.theme.OnlineShopTheme

@Composable
internal fun ProductInfo(
    modifier: Modifier = Modifier,
    uiState: UIState,
    ratingIcon: Int
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = uiState.name,
                color = OnlineShopTheme.colors.title,
                style = OnlineShopTheme.typography.productDetailsName
            )

            Text(
                text = stringResource(R.string.product_price_body, uiState.price),
                color = OnlineShopTheme.colors.title,
                style = OnlineShopTheme.typography.productDetailsPrice
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = uiState.description,
            color = AccountQuestion,
            style = OnlineShopTheme.typography.productDetailsDescription
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(10.dp),
                imageVector = ImageVector.vectorResource(ratingIcon),
                contentDescription = stringResource(R.string.rating_description, uiState.rating)
            )
            Text(
                modifier = Modifier.padding(end = 2.dp),
                text = uiState.rating.toString(),
                color = OnlineShopTheme.colors.title,
                style = OnlineShopTheme.typography.productDetailsDescription,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = stringResource(
                    R.string.product_number_of_reviews_body, uiState.numberOfReviews
                ),
                color = AccountQuestion,
                style = OnlineShopTheme.typography.productDetailsDescription
            )
        }
    }
}

@Preview
@Composable
private fun PreviewProductInfo() {
    var uiState = UIState.empty
    uiState = uiState.copy(
        name = "Reebok Classic",
        description = "Shoes inspired by 80s running shoes are still relevant today",
        rating = 4.3,
        numberOfReviews = 4000,
        price = 24
    )
    ProductInfo(
        uiState = uiState,
        ratingIcon = R.drawable.star_4
    )
}
