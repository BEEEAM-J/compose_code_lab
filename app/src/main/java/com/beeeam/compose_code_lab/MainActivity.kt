package com.beeeam.compose_code_lab // ktlint-disable package-name

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beeeam.compose_code_lab.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background,
//                ) {
//                    Greeting("BEEEAM")
//                }
                MyApp()
            }
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClick,
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun MyApp() {
    var onBoardingState by rememberSaveable { mutableStateOf(true) }

    if (onBoardingState) {
        OnboardingScreen(onContinueClick = { onBoardingState = false })
    } else {
        Greetings()
    }
}

@Composable
fun Greetings() {
    val names = List(100) { "$it" }
    Surface(modifier = Modifier.padding(vertical = 4.dp)) {
        LazyColumn {
            items(items = names) { name ->
                Greeting(text = name)
            }
        }
    }
}

@Composable
fun Greeting(text: String) {
    var expand by remember { mutableStateOf(false) }
    val expandArea = if (expand) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
            Column(Modifier.weight(1f).padding(bottom = expandArea)) {
                Text(text = "Hello")
                Text(text = text)
            }
            ElevatedButton(
                onClick = { expand = !expand },
            ) {
                Text(if (expand) "Show less" else "Show more")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    BasicsCodelabTheme {
        MyApp()
    }
}

// @Preview(showSystemUi = true)
// @Composable
// fun OnBoardingPreview() {
//    BasicsCodelabTheme {
//        OnboardingScreen()
//    }
// }
