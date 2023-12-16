fun main() {


    fun part1(lines: List<String>): Int {
        return 1
    }

    fun part2(lines: List<String>): Int {
        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 1)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
