package pt.iade.games.gamedevedex.ui

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.games.gamedevedex.models.ProjectAsset
import pt.iade.games.gamedevedex.R
import java.net.URI

@Composable
fun AssetItem(asset: ProjectAsset) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.placeholder_cover_image), // Replace with real image loader
            contentDescription = asset.description,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = asset.description)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAssetItem() {
    val context = LocalContext.current
    val uri = URI.create("android.resource://${context.packageName}/drawable/placeholder_cover_image")

    AssetItem(ProjectAsset(uri = uri, description = "AMOGUSSSSSS"))
}