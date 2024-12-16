package pt.iade.games.gamedevedex

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import pt.iade.games.gamedevedex.controllers.StudentController
import pt.iade.games.gamedevedex.models.Project
import pt.iade.games.gamedevedex.models.ProjectAsset
import pt.iade.games.gamedevedex.models.Student
import pt.iade.games.gamedevedex.ui.components.ProjectCard
import pt.iade.games.gamedevedex.ui.theme.GamedevedexTheme
import java.net.URI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GamedevedexTheme {
                MainView()
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {
    var student by remember { mutableStateOf<Student?>(null) }
    val studentController = StudentController()
    studentController.GetById(
        id = 123,
        onSuccess = { studentReq ->
            student = studentReq
        }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Gamedevedex")
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            if (student != null) {
                Text(student!!.name)
            } else {
                // TODO: Show progress circle thingy.
            }

            ProjectCard(
                modifier = Modifier.padding(vertical = 20.dp),
                project = ProjectExample()
            )
            ProjectCard(
                modifier = Modifier.padding(vertical = 20.dp),
                project = ProjectExample2()
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainViewPreview() {
    GamedevedexTheme {
        MainView()
    }
}

fun ProjectExample(): Project {
    val projectAssetsList = List(5) {
        ProjectAsset(
            uri = URI.create("https://lutris.net/media/games/screenshots/ss_649e19ff657fa518d4c2b45bed7ffdc4264a4b3a.jpg"),
            description = "cover image"
        )
    }

    return Project(
        title = "Unbox the Truth",
        votes = 17,
        description = "Unbox the Truth is a 2D puzzle-platformer where players rotate the world to navigate obstacles as a conscious cardboard box, blending physics-based challenges, strategy, and customization through a companion app.",
        id = 44,
        semester = 3,
        assets = projectAssetsList,
        groupMembers = listOf(
            StudentD(),
            StudentF(),
            StudentK(),
        )
    )
}

fun ProjectExample2(): Project {
    val projectAssetsList = List(5) {
        ProjectAsset(
            uri = URI.create("https://lutris.net/media/games/screenshots/ss_649e19ff657fa518d4c2b45bed7ffdc4264a4b3a.jpg"),
            description = "cover image"
        )
    }

    return Project(
        title = "Flying Gorilla",
        votes = 222,
        description = "in the game flying gorilla, YOU are flying a grorilla trhought a really legnth and preilous journy.",
        id = 45,
        semester = 2,
        assets = projectAssetsList,
        groupMembers = listOf(
            Student(
                id = 1232,
                name = "you",
                biography = "XDXDXDXD.",
                mood = "Freaky",
                avatar = (R.drawable.filippo)
            ),
            Student(
                id = 12332,
                name = "Me",
                biography = "Love tranding on the future of third world country. Currently thinking of YOU.",
                mood = "sadge",
                avatar = (R.drawable.dito)
        )
        )
    )
}

fun StudentF(): Student{
    return Student(
        id = 1,
        name = "Filippo",
        avatar = (R.drawable.filippo),
        biography = "A reformed ex-mafia member, now develops video games, channeling his past into storytelling and themes of redemption.",
        mood = ":^)",
    )
}

fun StudentD(): Student{
    return Student(
        id = 2,
        name = "Dito",
        avatar = (R.drawable.dito),
        biography = "A reformed African American ex-gang member battles past drug addiction, turning struggles into inspiration and creating a path to redemption.",
        mood = "Fentanyl",
    )
}

fun StudentK(): Student{
    return Student(
        id = 3,
        name = "Konstantine",
        avatar = (R.drawable.konnie),
        biography = "German.",
        mood = "German",
    )
}

