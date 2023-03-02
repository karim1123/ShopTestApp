package karim.gabbasov.product_details.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import karim.gabbasov.product_details.ui.UIState
import karim.gabbasov.ui.ui.util.NetworkImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun ImageViewPager(
    uiState: UIState,
    onImageChanged: (Int) -> Unit
) {
    val pagerState = rememberPagerState()
    if (pagerState.isScrollInProgress) {
        onImageChanged(pagerState.currentPage)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            count = uiState.imageUrls.size,
            itemSpacing = 20.dp
        ) { page ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                NetworkImage(url = uiState.imageUrls[page])
            }
        }
        Spacer(modifier = Modifier.height(36.dp))
        ImagesPreviews(
            uiState = uiState,
            onImageChanged = onImageChanged,
            pagerState = pagerState
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ImagesPreviews(
    uiState: UIState,
    onImageChanged: (Int) -> Unit,
    pagerState: PagerState
) {
    LazyRow(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        itemsIndexed(uiState.imageUrls) { index, _ ->
            ImagesPreviewsItem(
                uiState = uiState,
                index = index,
                onImageChanged = onImageChanged,
                pagerState = pagerState
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ImagesPreviewsItem(
    uiState: UIState,
    index: Int,
    onImageChanged: (Int) -> Unit,
    pagerState: PagerState

) {
    val coroutineScope = rememberCoroutineScope()

    val modifier = if (pagerState.currentPage == index) {
        Modifier
            .height(46.dp)
            .width(84.dp)
            .shadow(ambientColor = Color.LightGray, elevation = 15.dp)
    } else {
        Modifier
            .height(38.dp)
            .width(66.dp)
            .shadow(ambientColor = Color.LightGray, elevation = 0.dp)
    }
    Card(
        modifier = modifier.clickable {
            onImageChanged(index)
            coroutineScope.launch {
                pagerState.animateScrollToPage(page = index)
            }
        },
        shape = RoundedCornerShape(6.dp)
    ) {
        NetworkImage(url = uiState.imageUrls[index])
    }
    Spacer(modifier = Modifier.width(4.dp))
}

@Preview
@Composable
private fun PreviewImageViewPager() {
    ImageViewPager(
        uiState = UIState.empty.copy(imageUrls = listOf("", "", "")),
        onImageChanged = {}
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
private fun PreviewImagesPreviews() {
    ImagesPreviews(
        uiState = UIState.empty.copy(imageUrls = listOf("", "", "")),
        onImageChanged = {},
        pagerState = rememberPagerState()
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
private fun PreviewImagesPreviewsItem() {
    ImagesPreviewsItem(
        uiState = UIState.empty.copy(imageUrls = listOf("", "", "")),
        index = 1,
        onImageChanged = {},
        pagerState = rememberPagerState()
    )
}
