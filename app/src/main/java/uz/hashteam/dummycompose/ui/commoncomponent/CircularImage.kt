package uz.hashteam.dummycompose.ui.commoncomponent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uz.hashteam.dummycompose.R
import uz.hashteam.dummycompose.ui.theme.spacing

/**
 * Composable to display a circular Circular Image
 *
 * @param imageUrl Url of the image to be loaded
 * @param widthFraction Percentage of total width of Parent this Composable needs to occupy
 * @param modifier Optional Modifier
 */
@Composable
fun CircularImage(imageUrl: String, widthFraction: Float, modifier: Modifier = Modifier, width: Dp = 50.dp,  height: Dp = 50.dp,) {
    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .padding(start = MaterialTheme.spacing.small)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(imageUrl)
                .error(R.drawable.person)
                .placeholder(R.mipmap.ic_launcher)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .aspectRatio(1f)
                .clip(CircleShape)
                .align(Alignment.Center)
        )
    }
}
