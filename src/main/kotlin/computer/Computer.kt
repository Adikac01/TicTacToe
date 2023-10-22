package computer

import board.Board

class Computer(val board: Board) {

    fun getBestMove(): Move {
        val chooseBestFilling = ChooseBestFilling(board.fields)
        return chooseBestFilling.getBestMove()
    }
}