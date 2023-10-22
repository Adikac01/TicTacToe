import board.Board
import board.Filling
import board.GameOverState
import computer.Computer

fun main(args: Array<String>) {
    val board = Board()
    val computer = Computer(board)
    var isComputerTurn = true
    while (board.gameOverState == null) {
        println(board.gameOverState)
        if (isComputerTurn) {
            val computerMove = computer.getBestMove()
            board.placeFilling(computerMove.position, Filling.CROSS)
            println("Computer picked ${computerMove.position}")
        } else {
            board.printBoard()
            println("Choose position to place a circle")
            do {
                val position = readln()
                val fill = board.placeFilling(position.toInt(), Filling.CIRCLE)
                if (!fill) {
                    println("Try again")
                }
            } while (!fill)
        }
        isComputerTurn = !isComputerTurn
        board.calculateWin()
    }
    when (board.gameOverState) {
        GameOverState.DRAW -> println("The game is drawn")
        GameOverState.WIN -> println("You have won the game")
        GameOverState.LOSE -> println("The computer has won")
        null -> {}
    }
    board.printBoard()
}