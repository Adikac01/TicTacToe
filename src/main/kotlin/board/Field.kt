package board

data class Field(var filling: Filling, val position: Int) {
    fun printFilling() = filling.printFilling(position)
    fun isEmpty() = filling == Filling.EMPTY

    fun isSameNonEmpty(other: Field): Boolean {
        return this.filling == other.filling && !this.isEmpty()
    }
}