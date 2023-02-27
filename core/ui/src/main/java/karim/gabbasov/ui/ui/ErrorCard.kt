package karim.gabbasov.ui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.ui.R
import karim.gabbasov.ui.theme.Black
import karim.gabbasov.ui.theme.OnlineShopTheme

@Composable
fun ErrorCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    title: String,
    errorMessage: String,
    icon: ImageVector,
    iconDescription: String
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = OnlineShopTheme.colors.background
        ),
        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Image(
                imageVector = icon,
                contentDescription = iconDescription,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(OnlineShopTheme.colors.inputTextContainer)
                    .padding(10.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = title,
                color = Black
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = errorMessage,
                color = Black
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                shape = RoundedCornerShape(20.dp),
                onClick = { onClick.invoke() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = OnlineShopTheme.colors.enabledButton
                ),
                contentPadding = PaddingValues(),
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    color = OnlineShopTheme.colors.buttonText,
                    text = stringResource(R.string.update)
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Preview
@Composable
fun PreviewErrorCard() {
    ErrorCard(
        onClick = {},
        title = "No network connection",
        errorMessage = "Check the Internet connection",
        icon = ImageVector.vectorResource(R.drawable.no_network_icon),
        iconDescription = "No network connection"
    )
}
