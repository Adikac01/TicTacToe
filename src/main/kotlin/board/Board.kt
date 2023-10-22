package board

class Board(val fields: HashMap<Int, Field> = createEmptyBoardFields()) {

    val lines = initializeLines()

    var gameOverState: GameOverState? = null

    fun calculateWin() {
        lines.forEach {
            if (it.lineState == LineState.ALL_CROSSES) {
                gameOverState = GameOverState.LOSE
            }
            if (it.lineState == LineState.ALL_CIRCLES) {
                gameOverState = GameOverState.WIN
            }
            if (fields.none { (_, field) -> field.isEmpty() }) {
                gameOverState = GameOverState.DRAW
            }
        }
    }

    fun printBoard() {
        fields.forEach { (_, field) ->
            let {
                field.printFilling()
                if (field.position % 3 != 0) {
                    print('|')
                } else if (field.position != 9) {
                    println()
                    println("-----")
                }
            }
        }
        println()
    }

    fun placeFilling(place: Int, filling: Filling): Boolean {
        return if (fields[place]?.filling == Filling.EMPTY) {
            fields[place]?.filling = filling
            updateBoard()
            true
        } else {
            println("This field is already filled")
            false
        }
    }

    private fun initializeLines(): Set<Lines> {
        val lines = mutableSetOf<Lines>()
        lines.add(Lines(fields[1]!!, fields[2]!!, fields[3]!!))
        lines.add(Lines(fields[4]!!, fields[5]!!, fields[6]!!))
        lines.add(Lines(fields[7]!!, fields[8]!!, fields[9]!!))
        lines.add(Lines(fields[1]!!, fields[4]!!, fields[7]!!))
        lines.add(Lines(fields[2]!!, fields[5]!!, fields[8]!!))
        lines.add(Lines(fields[3]!!, fields[6]!!, fields[9]!!))
        lines.add(Lines(fields[1]!!, fields[5]!!, fields[9]!!))
        lines.add(Lines(fields[3]!!, fields[5]!!, fields[7]!!))
        return lines
    }

    private fun updateBoard() {
        lines.forEach(Lines::checkState)
    }
}

fun createEmptyBoardFields(): HashMap<Int, Field> {
    val fields = hashMapOf<Int, Field>()
    for (i in (1..9)) {
        fields[i] = Field(Filling.EMPTY, i)
    }
    return fields
}