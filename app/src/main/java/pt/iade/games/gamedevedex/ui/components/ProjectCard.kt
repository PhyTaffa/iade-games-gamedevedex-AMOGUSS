package pt.iade.games.gamedevedex.ui.components

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import pt.iade.games.gamedevedex.ProjectDetailActivity
import pt.iade.games.gamedevedex.ProjectSingleton
import pt.iade.games.gamedevedex.R
import pt.iade.games.gamedevedex.models.Project
import pt.iade.games.gamedevedex.models.ProjectAsset
import pt.iade.games.gamedevedex.models.Student
import java.net.URI

@Composable
fun ProjectCard(
    project: Project,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    // var votes = project.votes
    var votes by remember { mutableIntStateOf(project.votes) }

    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = {
//            Toast.makeText(context, project.title,
//                Toast.LENGTH_SHORT).show()
//            val intent = Intent(context, ProjectDetailActivity::class.java)
//            context.startActivity(intent)
            ProjectSingleton.selectedProject = project
            context.startActivity(Intent(context, ProjectDetailActivity::class.java))
        }
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = project.assets[0].uri, // Accessing the URI property
                placeholder = painterResource(R.drawable.dkc_gnawty), // Placeholder image
                contentDescription = "Cover image of the game", // Description for accessibility
                modifier = Modifier.fillMaxSize(), // Fills the parent size
                contentScale = ContentScale.Crop, // Crop the image to fill the container
            )

            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = "$votes",
                    fontSize = 17.sp,
                    color = Color(255, 255, 255)
                )
            }
        }

        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = project.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.weight(weight = 1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Button(
                onClick = {
                    votes++
                    project.votes++
                },
                modifier = Modifier.padding(start = 30.dp)
            ) {
                Text("Vote")
            }
        }
    }
}

@Composable
@Preview()
fun ProjectCardPreview() {

    val projectAssetsList = List(2) {
        ProjectAsset(
            uri = R.drawable.dkc_gnawty,
            description = "cover image"
        )
    }
    val projectAssetsList2 = List(2) {
        ProjectAsset(
            uri = R.drawable.nejior2,
            description = "cover image"
        )
    }

    Column {
        ProjectCard(
            modifier = Modifier.padding(vertical = 20.dp),
            project = Project(
                title = "Among Us",
                votes = 2,
                description = "Super sus.",
                id = 404,
                semester = 1,
                assets = projectAssetsList,
                groupMembers = listOf(
                    Student(
                        id = 123,
                        name = "João Pedro",
                        biography = "Love playing Valorant. Currently thinking of switching courses.",
                        mood = "Lucky",
                        avatar = (R.drawable.filippo)
                    )
                )
            )
        )
        ProjectCard(
            modifier = Modifier.padding(vertical = 20.dp),
            project = Project(
                title = "Zelda: Twilight Princess",
                votes = 123,
                description = "The best Wii game ever made. Apart from Super Smash Bros.",
                id = 404,
                semester = 1,
                assets = projectAssetsList2,
                groupMembers = listOf(
                    Student(
                        id = 123,
                        name = "João Pedro",
                        biography = "Love playing Valorant. Currently thinking of switching courses.",
                        mood = "Lucky",
                        avatar = (R.drawable.filippo)
                    )
                )
            )
        )
    }
}