package board

class Lines(val first: Field, val second: Field, val third: Field) {
    var lineState = LineState.NOT_FILLED

    fun checkState(): LineState {
        if (first.isSameNonEmpty(second) && first.isSameNonEmpty(third)) {
            lineState = if (first.filling == Filling.CIRCLE) {
                LineState.ALL_CIRCLES
            } else {
                LineState.ALL_CROSSES
            }
        }
        return LineState.NOT_FILLED
    }
}