package com.example.mybasicscodelab

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.mybasicscodelab.ui.theme.MyBasicsCodelabTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBasicsCodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    LayoutsCodelab()
                }
            }
        }
    }
}

@Composable
fun LazySimpleList(lazyListState: LazyListState, listSize: Int) {
    //val scrollState = rememberScrollState()

    val getBGColor: (Int) -> Color = { index ->
        if (index == 0) Color.Red else Color.Yellow
    }

    LazyColumn(
        state = lazyListState
    ) {
        items(listSize) {
//            Text(
//                text = "Item #$it",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(getBGColor(it))
//            )
            ImageListItem(it)
        }
    }
}

@Composable
fun SimpleList(scrollState: ScrollState) {
    //val scrollState = rememberScrollState()

    val getBGColor: (Int) -> Color = { index ->
        if (index == 0) Color.Red else Color.Yellow
    }

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        repeat(100) {
            Text(
                text = "Item #$it",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(getBGColor(it))
            )
        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Text(
            "Item #$index",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun LayoutsCodelab() {
    val listSize = 100
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = {
                        Log.d("TAG", "좋아요 클릭")
                    }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }

                    IconButton(onClick = {
                        Log.d("TAG", "홈 클릭")
                    }) {
                        Icon(Icons.Filled.Home, contentDescription = null)
                    }

                    IconButton(onClick = {
                        Log.d("TAG", "뒤로가기 클릭")
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                IconButton(
                    onClick = {
                        Log.d("TAG", "좋아요 클릭")
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Filled.Favorite, contentDescription = null)
                }

                IconButton(
                    onClick = {
                        Log.d("TAG", "홈 클릭")
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Filled.Home, contentDescription = null)
                }

                IconButton(
                    onClick = {
                        Log.d("TAG", "뒤로가기 클릭")
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
        },
        drawerContent = {
            IconButton(onClick = {
                Log.d("TAG", "좋아요 클릭")
            }) {
                Icon(Icons.Filled.Favorite, contentDescription = null)
            }

            IconButton(onClick = {
                Log.d("TAG", "홈 클릭")
            }) {
                Icon(Icons.Filled.Home, contentDescription = null)
            }

            IconButton(onClick = {
                Log.d("TAG", "뒤로가기 클릭")
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        },
        floatingActionButton = {
//            IconButton(
//                onClick = {
//                    Log.d("TAG", "최상단으로 이동 클릭")
//                    coroutineScope.launch {
//                        //scrollState.animateScrollTo(0)
//                        lazyListState.animateScrollToItem(0)
//                    }
//                },
//                modifier = Modifier.background(Color.Red, CircleShape)
//            ) {
//                Icon(Icons.Filled.KeyboardArrowUp, contentDescription = null)
//            }

            IconButton(
                onClick = {
                    Log.d("TAG", "최하단으로 이동 클릭")
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(listSize - 1)
                    }
                },
                modifier = Modifier.background(Color.Red, CircleShape)
            ) {
                Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
            }
        },
        backgroundColor = Color.Green
    ) { innerPadding ->
        BodyContent(
            Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp), contentSlot = {
                LazySimpleList(lazyListState, listSize)
            })
    }
}

@Composable
fun SomeText() {
    Column() {
        Text("안녕하세요.")
        Text("박유진입니다.")
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier, contentSlot: @Composable () -> Unit) {
    Surface(
        color = Color.Gray,
        modifier = modifier,
        content = contentSlot
    )
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                Log.d("TAG", "click!")
            }
            .padding(8.dp)
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {

        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.spacedBy(4.dp)

        ) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
                Text("이용 불가", style = MaterialTheme.typography.caption)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyBasicsCodelabTheme {
        PhotographerCard(Modifier.padding(4.dp))
    }
}