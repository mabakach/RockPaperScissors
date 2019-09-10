package ch.mabaka.rockpaperscissors

import java.lang.IllegalStateException

fun main() {
    val rps = RockPaperScissors();
    rps.play();
}

public class RockPaperScissors {

    enum class Option(val option: String) {
        PAPER("paper"),
        ROCK("rock"),
        SCISSORS("scissors"),
        UNKNOWN("unknown")
    }

    val scoreBoard = ScoreBoard();

    fun play() {
        do {
            val usersChoice = this.userDraw();
            val computersChoice = computerDraw();
            println("You have choosen $usersChoice");
            println("Computer has choosen $computersChoice")
            val whoWins = referee(usersChoice, computersChoice);
            when (whoWins) {
                -1 -> {println("Computer wins")
                    scoreBoard.increaseComputerScore()}
                0 -> println ("No one has won");
                1 -> {println("You win")
                    scoreBoard.increasePlayerScore()}
                else -> throw IllegalStateException();
            }
            println("")
        } while (playAgain())
        print(scoreBoard.printScoreBoard());
    }

    private fun playAgain(): Boolean {
        var answer: String? = "";
        while (!("y".equals(answer) || "n".equals(answer))) {
            println("Would you like to play again? Type 'y' for yes and 'n' for no.")
            answer = readLine()
        }
        return "y".equals(answer);
    }

    fun userDraw(): Option {
        var parsedInput = Option.UNKNOWN
        println("Hello.")
        while (Option.UNKNOWN.equals(parsedInput)) {
            println("Please choose between Rock [r], Paper [p] or Scissors [s]: s")
            var input = readLine()
            parsedInput = when (input) {
                "r" -> Option.ROCK
                "p" -> Option.PAPER
                "s" -> Option.SCISSORS
                else -> Option.UNKNOWN
            }
            if (Option.UNKNOWN.equals(parsedInput)) {
                println("I don't understand you!")
                println("Try again")
                println("")
            }

        }
        return parsedInput
    }

    fun computerDraw(): Option {
        return arrayOf(Option.ROCK, Option.PAPER, Option.SCISSORS).random()
    }

    /**
     * return 1 if user wins, -1 if computer win, 0 if it is unclear.
     * @throws IllegalStateException if <code>OPTION.UNKNOWN</code> is supplied in any parameter.
     */
    fun referee(usersChoice: Option, computersChoice: Option): Int {
        return when (usersChoice) {
            Option.PAPER -> when (computersChoice) {
                Option.PAPER -> 0
                Option.ROCK -> 1
                Option.SCISSORS -> -1
                Option.UNKNOWN -> throw IllegalStateException("Should not happen!")
            }
            Option.ROCK -> when (computersChoice) {
                Option.PAPER -> -1
                Option.ROCK -> 0
                Option.SCISSORS -> 1
                Option.UNKNOWN -> throw IllegalStateException("Should not happen!")
            }
            Option.SCISSORS -> when (computersChoice) {
                Option.PAPER -> 1
                Option.ROCK -> -1
                Option.SCISSORS -> 0
                Option.UNKNOWN -> throw IllegalStateException("Should not happen!")
            }
            Option.UNKNOWN -> throw IllegalStateException("Should not happen!")
        }
    }
}