fun main() {


    fun part1(line: String): Int {

        val tester = Regex("""([-0-9]+)""")
        val matches = tester.findAll(line)
        matches.map {
            it.groupValues[1].println()
        }
        val result = matches.map {
            it.groupValues[1].toInt()
        }.sum()

        return result

    }


    // test if implementation meets criteria from the description, like:
    //val testInput = readInput("Day12_test")
    check(part1("[1,2,3]") == 6)
    check(part1("""{"a":2,"b":4}""") == 6)
    check(part1("[[[3]]]") == 3)
    check(part1("""{"a":{"b":4},"c":-1}""") == 3)
    check(part1("""{"a":[-1,1]}""") == 0)
    check(part1("""[-1,{"a":1}]""") == 0)
    check(part1("[]") == 0)
    check(part1("{}") == 0)

    val input = readInput("Day12")
    part1(input[0]).println()
    //part2(input[0]).println()
}
