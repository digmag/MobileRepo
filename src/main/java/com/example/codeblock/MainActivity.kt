package com.example.codeblock

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codeblock.ui.theme.CodeBlockTheme
import androidx.compose.material.Text as Text


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cockobj = Cock()

        setContent {
            Column {
                var showBox by remember {
                    mutableStateOf(false)
                }
                Button(onClick = { showBox = true }) {
                    Text("Create Box")
                }
                if (showBox) {
                    cockobj.mp.put(1, "main")
                    cockobj.blockMain(sizeX = 100, sizeY = 100)
                }
            }
        }
    }
}



class Cock {

    var mp = mutableMapOf<Int, String>()
    private val options = listOf("Блок переменной", "Блок присваивания")


    @Composable
    fun variableblock(sizeX: Int, sizeY: Int) {
        var expanded by remember { mutableStateOf(false) }
        Card(
            modifier = Modifier
                .size(sizeX.dp, sizeY.dp),
            elevation = 5.dp,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Переменная",
                        color = Color.Blue
                    )
                    Row {
                        Button(
                            onClick = {
                                expanded = true
                            },
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(
                                text = "Доб. блок",
                                fontSize = 10.sp
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.width(IntrinsicSize.Max)
                        ) {
                            options.forEach { label ->
                                DropdownMenuItem(onClick = {
                                    // Do something with the selected option
                                    expanded = false
                                }) {
                                    Text(text = label)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @Composable
    fun blockMain(sizeX: Int, sizeY: Int) {
        var text by remember {
            mutableStateOf("")
        }
        var expanded by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf("") }
        Card(
            modifier = Modifier
                .size(sizeX.dp, sizeY.dp),
            elevation = 5.dp,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Main",
                        color = Color.Blue
                    )
                    Button(
                        onClick = {
                            expanded = true
                        },
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = "Доб. блок",
                            fontSize = 10.sp
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.width(IntrinsicSize.Max)
                    ) {
                        options.forEach { label ->
                            DropdownMenuItem(onClick = {
                                // Do something with the selected option
                                selectedOption = label
                                expanded = false
                            }) {
                                Text(text = label)
                            }
                        }
                    }
                    if(selectedOption == "Блок переменной"){
                        variableblock(sizeX = sizeX, sizeY = sizeY)
                    }
                }
            }
        }
    }
}
/*@Preview(showBackground = true)
@Composable
fun Def(){
    CodeBlockTheme {
        Block(sizeX = 300, sizeY = 300, text = "text")
    }
}*/

