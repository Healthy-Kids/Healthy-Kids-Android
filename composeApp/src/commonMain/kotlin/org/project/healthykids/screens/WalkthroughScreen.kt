package org.project.healthykids.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natighajiyev.common.colors.Grayscale50
import com.natighajiyev.common.colors.Grayscale900
import com.natighajiyev.common.colors.PrimaryColors
import healthykids.composeapp.generated.resources.Res
import healthykids.composeapp.generated.resources.healthy_kids
import healthykids.composeapp.generated.resources.next
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.project.healthykids.common.AppFonts
import org.project.healthykids.models.WalkthroughModel

//@Preview
@Composable
fun WalkthroughScreen(
    modifier: Modifier = Modifier,
    walkthroughList: List<WalkthroughModel>,
) {
    val pagerState = rememberPagerState(initialPage = 0) { walkthroughList.size }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 94.dp)
                .size(100.dp),
            text = stringResource(Res.string.healthy_kids),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(AppFonts.Montserrat_Bold)),
                color = PrimaryColors.Primary900,
                textAlign = TextAlign.Center
            )
        )

        HorizontalPager(
            modifier = modifier.fillMaxWidth(),
            state = pagerState
        ) { page ->
            WalkthroughComponent(walkthroughModel = walkthroughList[page])
        }

        AnimatedDotSwipe(count = walkthroughList.size, pagerState = pagerState)


        Button(
            modifier = modifier
                .padding(top = 24.dp)
                .size(width = 200.dp, height = 55.dp),
            shape = RoundedCornerShape(12.dp),
            onClick = {
                coroutineScope.launch { animateCarouselScrollPager(pagerState) }

//                if(pagerState.currentPage >= walkthroughList.size-1) {
//                    viewModel.postEvent(OnboardingContract.OnboardingEvent.WalkthroughViewed)
//                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColors.Primary900)
        ) {

            Text(
                modifier = modifier.fillMaxWidth(),
                text = stringResource(Res.string.next),
                style = TextStyle(
                    fontFamily = FontFamily(Font(AppFonts.Montserrat_Bold)),
                    color = PrimaryColors.Primary100,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            )

        }

    }
}



@Composable
fun WalkthroughComponent(
    modifier: Modifier = Modifier,
    walkthroughModel: WalkthroughModel,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.78f)
            .background(Grayscale50)
    ) {
        Image(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.68f),
            painter = painterResource(walkthroughModel.imageId),
            contentDescription = "Walkthrough Image",
            contentScale = ContentScale.Fit
        )

        Text(
            modifier = modifier
                .padding(horizontal = 48.dp)
                .padding(top = 32.dp)
                .fillMaxWidth(),
            text = walkthroughModel.title,
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(AppFonts.Montserrat_Bold)),
                color = PrimaryColors.Primary900,
                textAlign = TextAlign.Center
            )
        )

        Text(
            modifier = modifier
                .padding(horizontal = 48.dp)
                .padding(top = 18.dp)
                .fillMaxWidth(),
            text = walkthroughModel.desc,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(AppFonts.Montserrat_Medium)),
                color = Grayscale900,
                textAlign = TextAlign.Center,
            ),
        )

    }
}

@Composable
fun AnimatedDotSwipe(
    modifier: Modifier = Modifier,
    count: Int,
    pagerState: PagerState,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Row(modifier = modifier.align(Alignment.Center)) {
            repeat(count) { current ->
                Dot(expandedIndex = pagerState.currentPage, current = current)
            }
        }
    }
}

@Composable
fun Dot(
    modifier: Modifier = Modifier,
    expandedIndex: Int,
    current: Int,
) {
    Box(
        modifier = modifier
            .padding(horizontal = 4.dp)
            .animateContentSize()
            .height(12.dp)
            .width(if (expandedIndex == current) 36.dp else 12.dp)
            .background(PrimaryColors.Primary900, CircleShape)
    )
}

suspend fun animateCarouselScrollPager(pagerState: PagerState) {
    val nextPage = (pagerState.currentPage + 1)
    pagerState.animateScrollToPage(page = nextPage)
}