fun main() {

    fun targetFloor(test: String) = test.count { it == '(' } - test.count { it == ')' }

    fun part1(line: List<String>): Int {

        val test = line.first()
        return targetFloor(test)

    }

    fun part2(line: List<String>): Int {
        val test = line.first()
        for (i in test.indices) {
            if (targetFloor(test.substring(0, i)) == -1) {
                return i
            }
        }
        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 3)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
