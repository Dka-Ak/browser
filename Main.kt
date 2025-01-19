import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.VBox
import javafx.stage.Stage
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

class MainApp : Application() {

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    override fun start(primaryStage: Stage) {
        val searchBox = TextField()
        val searchButton = Button("Search")
        val resultsList = ListView<String>()

        searchButton.setOnAction {
            val query = searchBox.text
            if (query.isNotEmpty()) {
                performSearch(query, resultsList)
            }
        }

        val vbox = VBox(10.0, searchBox, searchButton, resultsList)
        val scene = Scene(vbox, 800.0, 600.0)

        primaryStage.title = "Simple Browser"
        primaryStage.scene = scene
        primaryStage.show()
    }

    private fun performSearch(query: String, resultsList: ListView<String>) {
        runBlocking {
            val results: List<String> = client.get("https://api.example.com/search?q=$query")
            resultsList.items.setAll(results)
        }
    }
}

fun main() {
    Application.launch(MainApp::class.java)
}
