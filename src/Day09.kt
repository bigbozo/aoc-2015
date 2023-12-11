fun main() {

    fun parseData(lines: List<String>): Pair<HashMap<String, Int>, List<List<String>>> {
        val splitterRegex = "^(.*) to (.*) = (\\d+)$".toRegex()
        val nodes = HashMap<String, String>()
        val edges = HashMap<String, Int>()
        lines.filter { it.isNotEmpty() }.map {
            val match = splitterRegex.find(it)!!.groupValues
            // Add new nodes
            if (nodes[match[1]] == null) nodes[match[1]] = match[1]
            if (nodes[match[2]] == null) nodes[match[2]] = match[2]
            // Add edges
            edges[match[1] + '-' + match[2]] = match[3].toInt()
            edges[match[2] + '-' + match[1]] = match[3].toInt()
        }
        val nodeList = nodes.values.toList()
        val routes = permutations(nodeList)
        return Pair(edges, routes)
    }

    fun measureRoutes(routes: List<List<String>>, edges: HashMap<String, Int>): List<Int> {
        return routes.map { it ->
            val route = it.toMutableList()
            var current = route.removeFirst()
            var distance = 0
            route.map { leg ->
                distance += edges["$current-$leg"]!!
                current = leg
            }
            distance
        }
    }

    fun part1(lines: List<String>): Int {
        val (edges, routes) = parseData(lines)
        return measureRoutes(routes, edges).min()
    }

    fun part2(lines: List<String>): Int {
        val (edges, routes) = parseData(lines)
        return measureRoutes(routes, edges).max()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 605)
    check(part2(testInput) == 982)

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}
