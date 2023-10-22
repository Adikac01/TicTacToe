package computer

import board.Field
import board.Filling
import kotlin.math.max
import kotlin.math.min

class ChooseBestFilling(val fields: HashMap<Int, Field>) {

    fun getBestMove(): Move {
        val possibleMoves = fields.findPossibleMoves()
        var bestMoveValue = Int.MIN_VALUE
        var bestPosition = -1
        for (move in possibleMoves) {
            move.value.filling = Filling.CROSS
            val chooseBestFilling = ChooseBestFilling(fields)
            val nextStateValue = chooseBestFilling.calculateNextState(depth = 1, false)
            if (nextStateValue > bestMoveValue) {
                bestMoveValue = nextStateValue
                bestPosition = move.key
            }
            move.value.filling = Filling.EMPTY
        }
        return Move(bestMoveValue, bestPosition)
    }

    fun calculateNextState(depth: Int, isComputerTurn: Boolean): Int {
        val score = calculateGameOver()
        if (score != 0) {
            return score
        }
        val possibleMoves = fields.findPossibleMoves()
        if (possibleMoves.isEmpty()) {
            return 0
        }
        if (isComputerTurn) {
            var bestMoveValue = Int.MIN_VALUE
            for (move in possibleMoves) {
                move.value.filling = Filling.CROSS
                val chooseNewBestFilling = ChooseBestFilling(fields)
                bestMoveValue = max(
                    bestMoveValue,
                    chooseNewBestFilling.calculateNextState(depth + 1, false)
                )
                move.value.filling = Filling.EMPTY
            }
            return bestMoveValue
        } else {
            var bestMoveValue = Int.MAX_VALUE
            for (move in possibleMoves) {
                move.value.filling = Filling.CIRCLE
                val chooseNewBestFilling = ChooseBestFilling(fields)
                bestMoveValue = min(
                    bestMoveValue,
                    chooseNewBestFilling.calculateNextState(depth + 1, true)
                )
                move.value.filling = Filling.EMPTY
            }
            return bestMoveValue
        }
    }

    private fun calculateGameOver(): Int {
        if (isWinningLine(1, 2, 3)
            || isWinningLine(1, 4, 7)
            || isWinningLine(1, 5, 9)
        ) {
            return winner(1)
        }

        if (isWinningLine(3, 6, 9)
            || isWinningLine(3, 5, 7)
        ) {
            return winner(3)
        }

        if (isWinningLine(4, 5, 6)
            || isWinningLine(2, 5, 8)
        ) {
            return winner(5)
        }

        if (isWinningLine(7, 8, 9)) return winner(7)

        return 0
    }

    private fun isWinningLine(position1: Int, position2: Int, position3: Int): Boolean {
        return fields[position1]!!.isSameNonEmpty(fields[position2]!!) &&
                fields[position1]!!.isSameNonEmpty(fields[position3]!!)
    }

    private fun winner(position1: Int): Int {
        return if (fields[position1]!!.filling == Filling.CROSS) 1 else -1
    }
}

fun HashMap<Int, Field>.findPossibleMoves(): Map<Int, Field> {
    return this.filter { it.value.isEmpty() }
}