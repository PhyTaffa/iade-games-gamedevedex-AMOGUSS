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
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            // Display the first image from the assets if available
            project.assets.firstOrNull()?.let { firstAsset ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = rememberAsyncImagePainter(model = firstAsset.uri),
                        contentDescription = "First asset image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp) // Adjust the height as needed
                            .clip(RoundedCornerShape(8.dp)) // Optional: add rounded corners
                    )
                }
                Spacer(modifier = Modifier.height(16.dp)) // Add space after the image
            }

            // Row with Project votes on the left and description on the right
            Row(
                modifier = Modifier
                    //.wrapContentHeight()
                    //.wrapContentWidth()
                    .padding(vertical = 8.dp)

            ) {
                // Left side: Project votes
                Column(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically) // Vertically center the content inside the Column
                        .padding(4.dp) // Optional: Adds some space between the votes and description
                ) {
                    Text(text = "Votes: ${project.votes}", style = MaterialTheme.typography.bodyMedium)
                }

                // Right side: Project description
                Column(
                    modifier = Modifier.padding(4.dp)
                        //.fillMaxHeight() // Ensures the description fills the available height
                ) {
                    //Text(text = "Description:", style = MaterialTheme.typography.titleMedium)
                    Text(text = project.description, style = MaterialTheme.typography.bodyLarge)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            project.groupMembers.forEach { member ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column {
                        // Image on the left
                        Image(
                            painter = rememberAsyncImagePainter(model = member.avatar),
                            contentDescription = "Avatar of ${member.name}",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                        )
                    }
                    Column {
                        Text(text = "Name: ${member.name}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Biography: ${member.biography}", style = MaterialTheme.typography.bodySmall)
                        Text(text = "Mood: ${member.mood}", style = MaterialTheme.typography.bodyMedium)

                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

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
            uri = URI.create("https://lutris.net/media/games/screenshots/ss_649e19ff657fa518d4c2b45bed7ffdc4264a4b3a.jpg"),
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
                avatar = URI.create("https://example.com/alice_avatar.png"),// change img
                biography = "Really enjoys android studio and definitely is not threatened to write this.",
                mood = "Scared"
            ),
            Student(
                id = 2,
                name = "BBBBBBBBBBBBBBB",
                avatar = URI.create("https://example.com/bob_avatar.png"),
                biography = "Believes the earth is flat.",
                mood = "Imaginative"
            ),
            Student(
                id = 3,
                name = "CCCCCCCCCCCCCCC",
                avatar = URI.create("https://example.com/charlie_avatar.png"),
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
