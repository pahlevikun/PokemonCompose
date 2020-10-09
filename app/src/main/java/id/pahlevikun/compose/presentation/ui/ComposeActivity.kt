package id.pahlevikun.compose.presentation.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.ui.tooling.preview.Preview
import id.pahlevikun.compose.presentation.theme.ComposeTheme

class ComposeActivity : AppCompatActivity() {

    private var testVariable: String = "empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Content(this) }
    }
}

@Composable
fun Content(context: Context? = null) {
    ComposeTheme {
        var sampleText by remember { mutableStateOf(TextFieldValue("Text")) }
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(text = "Compose")
                }
                )
            },
            bodyContent = {
                Column(
                    modifier = Modifier.padding(all = Dp(16f)),
                ) {
                    Text(text = "Android 1")
                    Text(text = sampleText.text)
                }
            },
            floatingActionButton = {
                Button(onClick = {
                    sampleText = TextFieldValue("this is new value")
                    Toast.makeText(context!!, "Clicked!!", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "This is Floating Action Button")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    Content()
}