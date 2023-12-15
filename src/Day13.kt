import kotlin.math.max

fun main() {

    data class Edge(val from: String, val to: String)

    val matcher = """(\w+) would (gain|lose) (\d+) happiness units by sitting next to (\w+).""".toRegex()


    fun calculateHappiness(
        participant: Set<String>,
        edges: MutableMap<Edge, Int>
    ): Int {
        val permutations = permutations(participant.toList())
        var maxHappiness = 0
        for (permutation in permutations) {
            var happiness = 0
            for (i in permutation.indices) {
                val edge = Edge(permutation[i], permutation[(i + 1) % permutation.size])
                val edge2 = Edge(permutation[(i + 1) % permutation.size], permutation[i])
                happiness += edges[edge]!!
                happiness += edges[edge2]!!
            }
            maxHappiness = max(maxHappiness, happiness)
        }
        return maxHappiness
    }

    fun part1(lines: List<String>): Int {

        val edges = mutableMapOf<Edge, Int>()
        val participant = mutableSetOf<String>()
        for (line in lines) {
            val match = matcher.find(line)
            val (from, sign, amount, to) = match!!.destructured
            edges[Edge(from, to)] = if (sign == "gain") amount.toInt() else -amount.toInt()
            participant.add(from)
            participant.add(to)
        }

        return calculateHappiness(participant, edges)

    }

    fun part2(lines: List<String>): Int {

        val edges = mutableMapOf<Edge, Int>()
        val participants = mutableSetOf<String>()

        for (line in lines) {
            val match = matcher.find(line)
            val (from, sign, amount, to) = match!!.destructured
            edges[Edge(from, to)] = if (sign == "gain") amount.toInt() else -amount.toInt()
            participants.add(from)
            participants.add(to)
        }

        for (participant in participants) {
            edges[Edge("Me", participant)] = 0
            edges[Edge(participant, "Me")] = 0
        }
        participants.add("Me")

        return calculateHappiness(participants, edges)

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day13_test")
    check(part1(testInput) == 330)

    val input = readInput("Day13")
    part1(input).println()
    part2(input).println()
}
