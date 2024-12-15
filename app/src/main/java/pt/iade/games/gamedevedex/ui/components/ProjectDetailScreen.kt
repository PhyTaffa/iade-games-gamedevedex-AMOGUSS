package pt.iade.games.gamedevedex.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
        // Main content of the screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
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
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                // Left side: Project votes
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Votes: ${project.votes}", style = MaterialTheme.typography.bodyMedium)
                }

                // Right side: Project description
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Description:", style = MaterialTheme.typography.titleMedium)
                    Text(text = project.description, style = MaterialTheme.typography.bodyLarge)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Row to display all students horizontally
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    //.horizontalArrangement(Arrangement.spacedBy(16.dp)) // Space between students
//            )
//            {
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
//            }

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
        description = "This is an example project.",
        groupMembers = listOf(
            Student(
                id = 1,
                name = "Alice Johnson",
                avatar = URI.create("https://example.com/alice_avatar.png"),// change img
                biography = "Alice loves programming and has a keen interest in mobile development.",
                mood = "Happy"
            ),
            Student(
                id = 2,
                name = "Bob Smith",
                avatar = URI.create("https://example.com/bob_avatar.png"),
                biography = "Bob is passionate about game development and enjoys playing chess in his free time.",
                mood = "Excited"
            ),
            Student(
                id = 3,
                name = "Charlie Davis",
                avatar = URI.create("https://example.com/charlie_avatar.png"),
                biography = "Charlie is a software engineer and a huge fan of machine learning and AI.",
                mood = "Curious"
            )
        ),
        assets = projectAssetsList,
        semester = 1,
        votes = 23,
    )
    ProjectDetailScreen(project = exampleProject)
}
