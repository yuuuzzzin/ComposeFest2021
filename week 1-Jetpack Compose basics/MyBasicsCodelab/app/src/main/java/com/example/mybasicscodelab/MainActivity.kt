package com.example.mybasicscodelab

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybasicscodelab.ui.theme.Green200
import com.example.mybasicscodelab.ui.theme.MyBasicsCodelabTheme
import com.example.mybasicscodelab.ui.theme.Red200
import com.example.mybasicscodelab.ui.theme.Teal200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBasicsCodelabTheme {
                ContentView()
            }
        }
    }
}

@Composable
private fun ContentView() {
//    Surface(
//        color = Red200,
//    ) {
//        Name("박유진", Modifier.padding(20.dp))
//    }

//    Surface(
//        color = Green200
//    ) {
//        MyColumn(modifier = Modifier.padding(10.dp))
//    }

    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = {
            Log.d("TAG", "ContentView: 온보딩 클릭됨")
            shouldShowOnboarding = !shouldShowOnboarding
        })
    } else {
        MyLazyColumn(modifier = Modifier.padding(20.dp))
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    //var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text(text = "Continue")
            }
        }
    }
}

@Composable
fun MyLazyColumn(nums: List<String> = List(1000) { "번호 : $it" }, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = nums) { num ->
            NameView(name = num)
        }
    }
}

@Composable
fun MyColumn(names: List<String> = listOf("박유진", "이하영", "김어쩌구"), modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        for (name in names) {
            NameView(name = name)
        }
    }
}

//- `var mutableState = remember { mutableStateOf(default) }`
//- `var value by remember { mutableStateOf(default) }`
//- `var (value, setValue) = remember { mutableStateOf(default) }`

@Composable
fun NameView(name: String) {

    var isExpanded by remember { mutableStateOf(false) }
//    var isExpanded = remember { mutableStateOf(false) }
//    var (isExpanded, setIsEx panded) = remember { mutableStateOf(false) }

//    val extraPadding = if(isExpanded) 48.dp else 0.dp
    val extraPadding by animateDpAsState(
        if (isExpanded) 48.dp else 0.dp
    )

    Surface(
        color = Red200,
        modifier = Modifier
            .padding(bottom = extraPadding)
            .clickable {
                Log.d("TAG_CLICK", "NameView: $name")
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(end = 10.dp)
                .fillMaxWidth(1.0f)
        ) {
            Text(
                text = name,
                modifier = Modifier
                    .padding(10.dp)
                    .background(Teal200)
                    .weight(1f),
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            OutlinedButton(
                onClick = {
                    isExpanded = !isExpanded
                    Log.d("TAG_CLICK", "NameView: 버튼 클릭 $name")
                }
            ) {
                Text(
                    text = if (isExpanded) "열림" else "닫힘"
                )
            }
        }
    }
}

@Composable
fun Name(name: String, modifier: Modifier = Modifier.padding(10.dp)) {
    Surface(
        color = Green200,
        modifier = modifier
    ) {
        Text(
            text = "내 이름은 $name!",
            color = Color.White,
            modifier = Modifier.padding(30.dp)
        )
    }
}

@Preview(
    showBackground = true,
    name = "NamePreview",
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
fun NamePreview() {
    MyBasicsCodelabTheme {
        ContentView()
//        MyLazyColumn(modifier = Modifier.padding(20.dp))
    }
}