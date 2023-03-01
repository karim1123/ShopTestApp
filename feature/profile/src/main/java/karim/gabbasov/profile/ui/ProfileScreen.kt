package karim.gabbasov.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import karim.gabbasov.model.data.user.UserDomain
import karim.gabbasov.profile.R.drawable.upload
import karim.gabbasov.profile.R.string.upload_button_text
import karim.gabbasov.profile.ui.components.ImagePicker
import karim.gabbasov.profile.ui.components.ProfileAppBar
import karim.gabbasov.profile.ui.components.ProfileMenu
import karim.gabbasov.ui.R
import karim.gabbasov.ui.theme.OnlineShopTheme
import karim.gabbasov.ui.ui.Avatar
import karim.gabbasov.ui.ui.BottomNavItem
import karim.gabbasov.ui.ui.ShopBottomAppBar
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
internal fun ProfileScreenRoute(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val user = viewModel.user.collectAsStateWithLifecycle()
    ProfileScreen(
        navController = navController,
        user = user,
        onChangeAvatar = { viewModel.updateUserAvatar(it) },
        onLogOut = {
            viewModel.logOut()
            navController.popBackStack()
            navController.navigate(viewModel.signInFeatureApi.signInRoute())
        },
        onBack = {
            val previousRoute = navController.previousBackStackEntry?.destination?.route
            navController.popBackStack()
            previousRoute?.let {
                navController.navigate(it) {
                    launchSingleTop = true
                }
            }
        },
        catalogNavRoute = viewModel.catalogFeatureApi.catalogRoute(),
        profileNavRoute = viewModel.profileFeatureApi.profileRoute()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileScreen(
    navController: NavController,
    user: State<UserDomain?>,
    onChangeAvatar: (String) -> Unit,
    onLogOut: () -> Unit,
    onBack: () -> Unit,
    catalogNavRoute: String,
    profileNavRoute: String
) {
    Scaffold(
        contentColor = OnlineShopTheme.colors.background,
        topBar = { ProfileAppBar(onBack) },
        bottomBar = {
            ShopBottomAppBar(
                items = listOf(
                    BottomNavItem(
                        route = catalogNavRoute,
                        icon = ImageVector.vectorResource(R.drawable.ic_home)
                    ),
                    BottomNavItem(
                        route = "",
                        icon = ImageVector.vectorResource(R.drawable.ic_favorite)
                    ),
                    BottomNavItem(
                        route = "",
                        icon = ImageVector.vectorResource(R.drawable.ic_cart)
                    ),
                    BottomNavItem(
                        route = "",
                        icon = ImageVector.vectorResource(R.drawable.ic_chat)
                    ),
                    BottomNavItem(
                        route = profileNavRoute,
                        icon = ImageVector.vectorResource(R.drawable.ic_profile)
                    )
                ),
                navController = navController,
                onItemClick = {
                    if (it.route == catalogNavRoute) navController.popBackStack()
                    navController.navigate(it.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            ProfileInfo(
                onChangeAvatar = onChangeAvatar,
                user = user.value
            )
            UploadButton()
            Spacer(modifier = Modifier.height(36.dp))
            ProfileMenu(onLogOut)
        }
    }
}

@Composable
private fun UploadButton() {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 45.dp)
            .height(40.dp),
        onClick = {},
        shape = OnlineShopTheme.shapes.medium,
        enabled = true,
        colors = ButtonDefaults.buttonColors(
            containerColor = OnlineShopTheme.colors.enabledButton
        ),
        contentPadding = PaddingValues(top = 2.dp, bottom = 2.dp)
    ) {
        Box {
            Image(
                modifier = Modifier.padding(start = 60.dp),
                imageVector = ImageVector.vectorResource(upload),
                contentDescription = stringResource(upload_button_text)
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(upload_button_text),
                textAlign = TextAlign.Center,
                style = OnlineShopTheme.typography.labelLarge,
                color = OnlineShopTheme.colors.buttonText
            )
        }
    }
}

@Composable
private fun ProfileInfo(
    onChangeAvatar: (String) -> Unit,
    user: UserDomain?
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Avatar(
            modifier = Modifier.size(60.dp),
            url = user?.imageUri.toString()
        )
        ImagePicker(onChangeAvatar = onChangeAvatar)
        Spacer(modifier = Modifier.height(16.dp))
        if (user != null) {
            Text(
                text = "${user.firstName} ${user.lastName}",
                style = OnlineShopTheme.typography.userInfo,
                color = OnlineShopTheme.colors.title
            )
        }
        Spacer(modifier = Modifier.height(36.dp))
    }
}

@Preview
@Composable
private fun PreviewProfileScreen() {
    val user = UserDomain(
        firstName = "firstName",
        lastName = "lastName",
        email = "email"
    )
    val state = MutableStateFlow(user).collectAsState()
    ProfileScreen(
        navController = rememberNavController(),
        user = state,
        onChangeAvatar = {},
        onBack = {},
        onLogOut = {},
        catalogNavRoute = "",
        profileNavRoute = ""
    )
}

@Preview
@Composable
private fun PreviewUploadButton() {
    UploadButton()
}

@Preview
@Composable
private fun PreviewProfileInfo() {
    ProfileInfo(
        onChangeAvatar = {},
        user = UserDomain(
            firstName = "firstName",
            lastName = "lastName",
            email = "email"
        )
    )
}
