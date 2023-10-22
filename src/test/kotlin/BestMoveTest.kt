import board.Board
import board.Filling
import computer.Computer
import org.junit.jupiter.api.Test

class BestMoveTest {


    @Test
    fun checkIfTheBestMoveIsPicked() {
        val board = Board()
        board.placeFilling(2, Filling.CROSS)
        board.placeFilling(8, Filling.CROSS)
        board.placeFilling(4, Filling.CIRCLE)
        board.placeFilling(6, Filling.CIRCLE)
        val computer = Computer(board)
        println(computer.getBestMove())
        assert(computer.getBestMove().position == 5)
    }


    @Test
    fun checkIfTheBestMoveIsPicked2() {
        val board = Board()
        board.placeFilling(3, Filling.CROSS)
        board.placeFilling(7, Filling.CROSS)
        board.placeFilling(1, Filling.CIRCLE)
        board.placeFilling(5, Filling.CIRCLE)
        val computer = Computer(board)
        println(computer.getBestMove())
        assert(computer.getBestMove().position == 9)
    }

    @Test
    fun checkIfTheDrawingMoveIsPicked() {
        val board = Board()
        board.placeFilling(3, Filling.CIRCLE)
        board.placeFilling(7, Filling.CIRCLE)
        board.placeFilling(5, Filling.CROSS)
        val computer = Computer(board)
        assert(computer.getBestMove().position == 2)
    }

    @Test
    fun checkTheEmptyBoard() {
        val board = Board()
        val computer = Computer(board)
        println(computer.getBestMove())
    }
}