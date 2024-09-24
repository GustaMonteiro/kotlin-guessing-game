import java.util.Scanner
import kotlin.random.Random

fun main () {
    printIntro()

    startGame()

    printEnding()
}

fun startGame() {
    do {
        val difficulty: Difficulty = askForDifficulty()
        val numberOfGuesses: Int = getNumberOfGuesses(difficulty)
        val randomNumber: Int = getRandomNumber()

        val won: Boolean = askGuesses(numberOfGuesses, randomNumber)

        when (won) {
            true -> printWinningMessage()
            false -> printLoosingMessage(randomNumber)
        }

        val anotherRun: Boolean = askForAnotherRun()
    } while (anotherRun)
}

fun askForAnotherRun(): Boolean {
    println("Do you want another run? [y/N]")

    val scanner = Scanner(System.`in`)
    val answer = scanner.nextLine()

    return answer.equals("y", true)
}

fun printLoosingMessage(randomNumber: Int) {
    println("Ohh, you lose! The number was $randomNumber")
    println(":( :( :( :( :(\n")
}

fun printWinningMessage() {
    println("Congratulations! You won!")
    println(":D :D :D :D :D :D :D :D :D\n")
}

fun askGuesses(numberOfGuesses: Int, randomNumber: Int): Boolean {
    var guessesLeft = numberOfGuesses
    val scanner = Scanner(System.`in`)

    do {
        println("You have $guessesLeft guesses left")
        guessesLeft--

        print("Enter a number: ")

        val guessedNumber = scanner.nextInt()

        when {
            guessedNumber > randomNumber -> println("Too high!\n")
            guessedNumber < randomNumber -> println("Too low!\n")
            else -> return true
        }

    } while (guessesLeft > 0)

    return false
}

fun getRandomNumber(): Int {
    return Random.nextInt(1, 100)
}

fun getNumberOfGuesses(difficulty: Difficulty): Int {
    return when(difficulty) {
        Difficulty.EASY -> 15
        Difficulty.MEDIUM -> 10
        Difficulty.HARD -> 7
        Difficulty.IMPOSSIBLE -> 5
    }
}

fun askForDifficulty(): Difficulty {
    val scanner = Scanner(System.`in`)

    println("Select one difficulty:")

    println("1 = EASY")
    println("2 = MEDIUM")
    println("3 = HARD")
    println("4 = IMPOSSIBLE")

    while (true) {
        return when(val selected = scanner.nextInt()) {
            1 ->  Difficulty.EASY
            2 ->  Difficulty.MEDIUM
            3 ->  Difficulty.HARD
            4 ->  Difficulty.IMPOSSIBLE
            else -> {
                println("$selected is not a valid difficulty, try again")
                continue
            }
        }
    }
}

fun printEnding() {
    println("See ya! :D")
}

fun printIntro() {
    println("============================")
    println("Welcome to the guessing game")
    println("============================\n")
}
