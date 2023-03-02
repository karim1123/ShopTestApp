package karim.gabbasov.product_details.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.product_details.R
import karim.gabbasov.product_details.ui.UIState
import karim.gabbasov.ui.theme.AccountQuestion
import karim.gabbasov.ui.theme.OnlineShopTheme
import karim.gabbasov.ui.theme.PurchaseCardSum
import karim.gabbasov.ui.theme.White

@Composable
internal fun PurchaseCard(
    uiState: UIState,
    onQuantityChanged: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = OnlineShopTheme.colors.purchaseCardBackground
        )
    ) {
        Spacer(modifier = Modifier.height(14.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier.wrapContentHeight(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.quantity_value, uiState.quantity),
                    color = AccountQuestion,
                    style = OnlineShopTheme.typography.productDetailsDescription
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    ChangeQuantityButton(
                        icon = R.drawable.remove,
                        contentDescription = R.string.remove_product_from_cart,
                        increaseQuantity = false,
                        onQuantityChanged
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    ChangeQuantityButton(
                        icon = R.drawable.add,
                        contentDescription = R.string.add_product_to_cart,
                        increaseQuantity = true,
                        onQuantityChanged
                    )
                }
            }
            Spacer(modifier = Modifier.width(60.dp))
            AddToCartButton(uiState = uiState)
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
private fun ChangeQuantityButton(
    icon: Int,
    contentDescription: Int,
    increaseQuantity: Boolean,
    onQuantityChanged: (Boolean) -> Unit
) {
    Button(
        modifier = Modifier
            .height(20.dp)
            .width(40.dp),
        onClick = { onQuantityChanged(increaseQuantity) },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = OnlineShopTheme.colors.purchaseCardButton
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(8.dp),
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = stringResource(contentDescription)
            )
        }
    }
}

@Composable
private fun AddToCartButton(uiState: UIState) {
    Button(
        modifier = Modifier
            .height(44.dp)
            .width(170.dp),
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = OnlineShopTheme.colors.purchaseCardButton
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.product_price_body, uiState.purchaseSum),
                color = PurchaseCardSum,
                style = OnlineShopTheme.typography.purchaseButton
            )
            Text(
                text = stringResource(R.string.add_to_card_button_body),
                color = White,
                style = OnlineShopTheme.typography.purchaseButton
            )
        }
    }
}

@Preview
@Composable
private fun PreviewPurchaseCard() {
    val uiState = UIState.empty
    PurchaseCard(
        uiState = uiState.copy(quantity = 4, purchaseSum = 96),
        onQuantityChanged = {}
    )
}

@Preview
@Composable
private fun PreviewChangeQuantityButton() {
    ChangeQuantityButton(
        icon = R.drawable.add,
        contentDescription = R.string.add_product_to_cart,
        increaseQuantity = true,
        onQuantityChanged = {}
    )
}

@Preview
@Composable
private fun PreviewAddToCartButton() {
    AddToCartButton(uiState = UIState.empty)
}
