package board

enum class Filling(val printed: String) {
    CROSS("x"),
    CIRCLE("o"),
    EMPTY(" ");

    fun printFilling(i: Int) {
        if (this == EMPTY) {
            print(i)
        } else {
            print(printed)
        }
    }
}