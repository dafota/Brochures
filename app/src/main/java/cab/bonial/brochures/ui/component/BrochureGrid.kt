package cab.bonial.brochures.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import cab.bonial.brochures.domain.model.Brochure

@Composable
fun BrochureGrid(
    brochures: List<Brochure>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp)
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val columnCount = if (isLandscape) 3 else 2

    LazyVerticalGrid(
        columns = GridCells.Fixed(columnCount),
        modifier = modifier.fillMaxSize(),
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(
            items = brochures,
            key = { it.id },
            span = { brochure ->
                if (brochure.isPremium) {
                    GridItemSpan(columnCount)
                } else {
                    GridItemSpan(1)
                }
            }
        ) { brochure ->
            BrochureCard(brochure = brochure)
        }
    }
}
