package com.example.geoquiz2

object QuizRepository {
    fun getQuestions(): List<Question> = listOf(
        Question(0, "1. Canberra is the capital of Australia.", true, "***"),
        Question(1, "2. The Pacific Ocean is larger than the Atlantic Ocean.", true, "***"),
        Question(2, "3. The Suez Canal connects the Red Sea and the Indian Ocean.", false, "***"),
        Question(3, "4. The source of the Nile River is in Egypt.", false, "***"),
        Question(4, "5. The Amazon River is the longest river in the Americas.", true, "***"),
        Question(5, "6. Lake Baikal is the world's oldest and deepest freshwater lake.", true, "***"),
    )
}