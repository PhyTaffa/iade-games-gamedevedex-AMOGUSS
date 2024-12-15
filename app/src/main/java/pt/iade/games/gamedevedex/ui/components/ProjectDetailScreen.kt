package pt.iade.games.gamedevedex.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.games.gamedevedex.models.Project
import pt.iade.games.gamedevedex.models.ProjectAsset
import pt.iade.games.gamedevedex.models.Student
import pt.iade.games.gamedevedex.ui.AssetItem
import java.net.URI

@Composable
fun ProjectDetailScreen(project: Project) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = project.title, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = project.description, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Team Members:", style = MaterialTheme.typography.titleMedium)
        project.groupMembers.forEach { member ->
            Text(text = "- $member")
            Spacer(modifier = Modifier.height(16.dp))

        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Assets:", style = MaterialTheme.typography.titleMedium)
        project.assets.forEach { asset ->
            AssetItem(asset)
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
