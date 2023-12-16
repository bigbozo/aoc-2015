import kotlin.math.min

fun main() {

    data class Reindeer(val name: String, val speed: Int, val duration: Int, val pause: Int, var points: Int = 0)
    data class Score(val reindeer: Reindeer, val score: Int)

    fun parseReindeerData(lines: List<String>): MutableList<Reindeer> {
        val matcher = """(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.""".toRegex()
        val reindeers = mutableListOf<Reindeer>()
        for (line in lines) {
            val match = matcher.find(line)
            val (name, speed, duration, pause) = match!!.destructured
            reindeers.add(Reindeer(name, speed.toInt(), duration.toInt(), pause.toInt()))
        }
        return reindeers
    }
    fun runReindeerRun(reindeer: Reindeer, seconds: Int): Int {
        val cycleDuration = reindeer.duration + reindeer.pause
        val cycleLength = reindeer.speed * reindeer.duration

        val cycles: Int = seconds / cycleDuration

        val restTime = seconds % cycleDuration

        return cycles * cycleLength + min(restTime, reindeer.duration) * reindeer.speed
    }

    fun part1(lines: List<String>, seconds: Int): Int {
        val reindeers = parseReindeerData(lines)

        return reindeers.maxOfOrNull { reindeer -> runReindeerRun(reindeer, seconds) } ?: 0
    }
    fun part2(lines: List<String>, seconds: Int): Int {
        val reindeers = parseReindeerData(lines)

        var scores: List<Score>
        for (i in 0..seconds) {
            scores = reindeers.map { reindeer ->
                Score(reindeer, runReindeerRun(reindeer, i + 1))
            }

            val fastestReindeerDistance = scores.maxBy { it.score }.score

            scores.filter { it.score == fastestReindeerDistance }
                .map {  it.reindeer.points++ }

        }

        return reindeers.maxBy { it.points }.points
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day14_test")
    check(part1(testInput, 1000) == 1120)
    check(part2(testInput, 1000) == 689)

    val input = readInput("Day14")
    part1(input, 2503).println()
    part2(input, 2503).println()
}

