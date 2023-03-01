package karim.gabbasov.profile.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.profile.R
import karim.gabbasov.ui.theme.Black
import karim.gabbasov.ui.theme.OnlineShopTheme

@Composable
internal fun ProfileMenu(onLogOut: () -> Unit) {
    Column {
        DefaultProfileMenuItem(
            title = stringResource(R.string.trade_store_title),
            image = ImageVector.vectorResource(R.drawable.wallet)
        )

        DefaultProfileMenuItem(
            title = stringResource(R.string.payment_methods_title),
            image = ImageVector.vectorResource(R.drawable.wallet)
        )
        ProfileMenuItemBalance()
        DefaultProfileMenuItem(
            title = stringResource(R.string.trade_history_title),
            image = ImageVector.vectorResource(R.drawable.wallet)
        )
        DefaultProfileMenuItem(
            title = stringResource(R.string.restore_purchase_title),
            image = ImageVector.vectorResource(R.drawable.restore)
        )
        SpecialProfileMenuItem(
            title = stringResource(R.string.help_title),
            image = ImageVector.vectorResource(R.drawable.help_outline)
        )
        SpecialProfileMenuItem(
            title = stringResource(R.string.log_out_title),
            image = ImageVector.vectorResource(R.drawable.log_out),
            onClick = onLogOut
        )
    }
}

@Composable
private fun DefaultProfileMenuItem(
    title: String,
    image: ImageVector
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 45.dp, end = 35.dp)
            .clickable { },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = image,
                contentDescription = title
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                color = Black,
                style = OnlineShopTheme.typography.menuItemBody
            )
        }
        Image(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = stringResource(R.string.menu_nav_description, title)
        )
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
private fun SpecialProfileMenuItem(
    title: String,
    image: ImageVector,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 45.dp)
            .clickable { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            imageVector = image,
            contentDescription = title,
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = title,
            color = Black,
            style = OnlineShopTheme.typography.menuItemBody
        )
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
private fun ProfileMenuItemBalance() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 45.dp)
            .clickable { },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.wallet),
                contentDescription = stringResource(R.string.balance_description)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(R.string.balance_description),
                color = Black,
                style = OnlineShopTheme.typography.menuItemBody
            )
        }
        Text(
            text = stringResource(R.string.balance_value, 1593),
            color = Black,
            style = OnlineShopTheme.typography.menuItemBody
        )
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Preview
@Composable
private fun PreviewProfileMenu() {
    ProfileMenu({})
}

@Preview
@Composable
private fun PreviewDefaultProfileMenuItem() {
    DefaultProfileMenuItem(
        title = "Trade store",
        image = ImageVector.vectorResource(R.drawable.wallet)
    )
}

@Preview
@Composable
private fun PreviewProfileMenuItemBalance() {
    ProfileMenuItemBalance()
}

@Preview
@Composable
private fun PreviewSpecialProfileMenuItem() {
    SpecialProfileMenuItem(
        title = "Log out",
        image = ImageVector.vectorResource(R.drawable.log_out)
    )
}
