package pt.iade.games.gamedevedex.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import pt.iade.games.gamedevedex.models.Project
import pt.iade.games.gamedevedex.models.ProjectAsset
import pt.iade.games.gamedevedex.models.Student
import pt.iade.games.gamedevedex.ui.AssetItem
import java.net.URI
import androidx.compose.material3.*
import androidx.compose.ui.res.painterResource
import pt.iade.games.gamedevedex.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun ProjectDetailScreen(project: Project) {
    Scaffold(
        topBar = {
            // Top App Bar with the project title
            TopAppBar(
                title = {
                    Text(
                        text = project.title,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            )
        }
    ) { paddingValues ->

        val scrollState = rememberScrollState()

        // Main content of the screen
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            // Display the first image from the assets if available
            project.assets.firstOrNull()?.let { firstAsset ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = rememberAsyncImagePainter(model = firstAsset.uri),
                        contentDescription = "First asset image",
                        modifier = Modifier
                            .fillMaxWidth() // Take full width
                            .wrapContentHeight() // Allow dynamic height based on the image's content
                            .clip(RoundedCornerShape(32.dp)) // Optional: add rounded corners
                            .aspectRatio(1f) // Optional: Maintain the aspect ratio if needed
                    )
                }
            }
            // You can add more content here

            // Column for Project votes
            Column(
                modifier = Modifier
                    .fillMaxWidth() // Ensures the Column takes up the full width
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Votes: ${project.votes}", style = MaterialTheme.typography.titleMedium, modifier = Modifier.align(Alignment.End))
            }
            // Column for Project description
            Column(
                modifier = Modifier
                    .fillMaxWidth() // Ensures the Column takes up the full width
                    .padding(vertical = 8.dp)
            ) {
                Text(text = project.description, style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(32.dp))

            project.groupMembers.forEach { member ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 8.dp)
                ) {
                    Column {
                        // Image on the left
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = member.avatar,
                                placeholder = painterResource(R.drawable.filippo),
                                error = painterResource(R.drawable.placeholder_cover_image)
                            ),
                            contentDescription = "Avatar of ${member.name}",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                        )
                    }
                    Column(
                        modifier = Modifier.padding(start = 8.dp)
                            .align(alignment = Alignment.CenterVertically)
                    ) {
                        Text(text = "Name: ${member.name}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Biography: ${member.biography}", style = MaterialTheme.typography.bodySmall)
                        Text(text = "Mood: ${member.mood}", style = MaterialTheme.typography.bodyMedium)
//                        Spacer(modifier = Modifier.width(32.dp))
                    }
                    //Spacer(modifier = Modifier.width(32.dp))
                }
                //Spacer(modifier = Modifier.width(320.dp))
            }

            //from members to asset list
            Spacer(modifier = Modifier.height(8.dp))

            // Assets section
            Text(text = "Assets:", style = MaterialTheme.typography.titleMedium)
            project.assets.forEach { asset ->
                AssetItem(asset)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewProjectDetailScreen() {
    val projectAssetsList = List(5) {
        ProjectAsset(
            uri = R.drawable.placeholder_cover_image,
            description = "cover image"
        )
    }
    val exampleProject = Project(
        id = 1,
        title = "Example Project",
        description = "This is an example project.dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd",
        groupMembers = listOf(
            Student(
                id = 1,
                name = "AAAAAAAAAAAAAAA",
                avatar = (R.drawable.filippo),// change img
                biography = "Really enjoys android studio and definitely is not threatened to write this.",
                mood = "Scared"
            ),
            Student(
                id = 2,
                name = "BBBBBBBBBBBBBBB",
                avatar = (R.drawable.dito),
                biography = "Believes the earth is flat.",
                mood = "Imaginative"
            ),
            Student(
                id = 3,
                name = "CCCCCCCCCCCCCCC",
                avatar = (R.drawable.konnie),
                biography = "Hate majorities and is look at youtube while eating.",
                mood = "Hate"
            )
        ),
        assets = projectAssetsList,
        semester = 1,
        votes = 23,
    )
    ProjectDetailScreen(project = exampleProject)
}
