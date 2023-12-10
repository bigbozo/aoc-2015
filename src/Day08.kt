fun main() {


    fun part1(lines: List<String>): Int {

        return lines.sumOf {
            it.length
        } - lines.sumOf {
            it.substring(1..it.length-2).replace("\\\\","#")
                .replace("\\\"","$")
                .replace("\\\\x[0-9a-f]{2}".toRegex(),"%")
                .length
        }
    }

    fun part2(lines: List<String>): Int {
        return lines.sumOf {
            ("\"" + it.replace("\"","##")
                .replace("\\","\\\\")
            + "\"").length
        } - lines.sumOf {
            it.length
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    val part1 = part1(testInput)
    check(part1 == 12)

    val input = readInput("Day08")
    part1(input).println()

    val part2 = part2(testInput)
    check(part2 == 19)
    part2(input).println()
}
