fun main() {


    fun part1(line: List<String>): Int {

        val test =line.map {
            it.split("x")
                    .map { it.toInt() }
        }.map {
            (it[0] * it[1] * it[2] / it.max()
                    + 2 * it[0] * it[1]
                    + 2 * it[0] * it[2]
                    + 2 * it[1] * it[2])
        }.sum()

        return test

    }

    fun part2(line: List<String>): Int {

        return line.map {
            it.split("x")
                    .map { it.toInt() }
        }.map {
            (2 * (it[0] + it[1] + it[2] - it.max())
             + it[0] * it[1] * it[2])
        }.sum()

    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 101)
    check(part2(testInput) == 48)


    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
