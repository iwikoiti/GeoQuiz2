package com.example.geoquiz2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.geoquiz2.ui.theme.GeoQuiz2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoQuiz2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GeoQuizScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun QuizText(message: String, fontSize: Float){
    Text(
        text = message,
        fontSize = fontSize.sp,
    )
}

@Composable
fun GeoQuizScreen(modifier: Modifier = Modifier) {
    val questions = remember { QuizRepository.getQuestions() }
    var currentIndex by remember { mutableStateOf(0) }
    val currentQuestion = questions[currentIndex]
    var score by remember { mutableStateOf(0) }
    var answeredQuestions by remember { mutableStateOf(setOf<Int>()) }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 250.dp, start = 16.dp, end = 16.dp)
    )
    {
        QuizText(message = currentQuestion.text, fontSize = 20f)
        if (currentIndex == questions.lastIndex && answeredQuestions.contains(questions.last().id)) {
            QuizText(message = "Score: $score", fontSize = 18f)
        }

        Spacer(modifier = Modifier.height(50.dp))

        if (!answeredQuestions.contains(currentQuestion.id)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            )
            {
                Button(
                    onClick = {
                        if (currentQuestion.answer) score++
                        answeredQuestions = answeredQuestions + currentQuestion.id
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    QuizText(message = "True", fontSize = 16f)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = {
                            if (!currentQuestion.answer) score++
                            answeredQuestions = answeredQuestions + currentQuestion.id
                    },
                    modifier = Modifier.weight(1f)

                ) {
                    QuizText(message = "False", fontSize = 16f)
                }
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        if (currentIndex < questions.lastIndex) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                //modifier = Modifier.fillMaxWidth()
            )
            {
                Button(
                    onClick = {
                        currentIndex++
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    QuizText(message = "→", fontSize = 16f)
                }
            }
        }

        OutlinedButton(
            onClick = {
                currentIndex = 0
                score = 0
                answeredQuestions = setOf()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reset")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GeoQuizPreview(){
    GeoQuiz2Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            GeoQuizScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}