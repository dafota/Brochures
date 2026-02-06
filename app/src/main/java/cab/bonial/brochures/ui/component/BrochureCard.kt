package cab.bonial.brochures.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cab.bonial.brochures.R
import cab.bonial.brochures.domain.model.Brochure
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Scale


@Composable
fun BrochureCard(
    brochure: Brochure,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column {
            BrochureImage(
                imageUrl = brochure.imageUrl,
                retailerName = brochure.retailerName,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.7f) // Standard brochure aspect ratio (portrait)
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = brochure.retailerName,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

/**
 * Optimized brochure image with loading/error states.
 * - Downscales images to actual display size (saves memory)
 * - Shows placeholder while loading
 * - Shows error placeholder if image fails to load
 */
@Composable
private fun BrochureImage(
    imageUrl: String?,
    retailerName: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    SubcomposeAsyncImage(
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .scale(Scale.FILL)
            .crossfade(true)
            .memoryCacheKey(imageUrl)
            .diskCacheKey(imageUrl)
            .build(),
        contentDescription = "$retailerName brochure",
        modifier = modifier,
        contentScale = ContentScale.Crop,
        loading = {
            ImagePlaceholder(isLoading = true)
        },
        error = {
            ImagePlaceholder(isLoading = false)
        }
    )
}

/**
 * Placeholder shown during loading or on error.
 */
@Composable
private fun ImagePlaceholder(isLoading: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.7f)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(32.dp),
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 2.dp
            )
        } else {
            // Error state - show a placeholder icon
            Icon(
                painter = painterResource(id = R.drawable.ic_brochure_placeholder),
                contentDescription = "Image not available",
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
