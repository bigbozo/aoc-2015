fun main() {

    fun parseData(lines: List<String>): Pair<HashMap<String, Int>, List<List<String>>> {
        val splitterRegex = "^(.*) to (.*) = (\\d+)$".toRegex()
        val nodes = HashMap<String, String>()
        val edges = HashMap<String, Int>()
        lines.filter { it.isNotEmpty() }.map {
            val match = splitterRegex.find(it)!!.groupValues
            // Add new nodes
            nodes.getOrPut(match[1]) { match[1] }
            nodes.getOrPut(match[2]) { match[2] }
            // Add edges
            edges[match[1] + '-' + match[2]] = match[3].toInt()
            edges[match[2] + '-' + match[1]] = match[3].toInt()
        }
        return Pair(edges, permutations(nodes.values.toList()))
    }

    fun measureRoutes(routes: List<List<String>>, edges: HashMap<String, Int>): List<Int> =
        routes.map { route ->
            route.zipWithNext { a, b -> edges["$a-$b"] ?: 0 }.sum()
        }

    fun part1(lines: List<String>): Int =
        parseData(lines).let { (edges, routes) ->
            measureRoutes(routes, edges).min()
        }

    fun part2(lines: List<String>): Int =
        parseData(lines).let { (edges, routes) ->
            measureRoutes(routes, edges).max()
        }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 605)
    check(part2(testInput) == 982)

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}
