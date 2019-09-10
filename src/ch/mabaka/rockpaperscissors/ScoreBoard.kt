package ch.mabaka.rockpaperscissors

class ScoreBoard {

    private var playerScore  = 0

    private var computerScore = 0

    fun increasePlayerScore() {
        playerScore = playerScore + 1
    }

    fun increaseComputerScore() {
        computerScore = computerScore + 1
    }

    fun printScoreBoard() {
        println("User has won $playerScore time. Computer has won $computerScore times.")
    }
}