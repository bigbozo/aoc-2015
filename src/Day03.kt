fun main() {


    fun part1(line: String): Int {

        val visited = mutableSetOf<Pair<Int, Int>>()

        var x = 0
        var y = 0
        visited.add(Pair(x, y))

        val directions = line.toCharArray()
        for (direction in directions) {
            when (direction) {
                '^' -> y++
                'v' -> y--
                '>' -> x++
                '<' -> x--
            }
            visited.add(Pair(x, y))
        }


        return visited.size

    }

    fun part2(line: String): Int {

        val visited = mutableSetOf<Pair<Int, Int>>()

        var x = 0
        var y = 0
        var x2 = 0
        var y2 = 0
        var realSanta = true

        visited.add(Pair(x, y))

        val directions = line.toCharArray()
        for (direction in directions) {
            when (direction) {
                '^' -> if (realSanta) y++ else y2++
                'v' -> if (realSanta) y-- else y2--
                '>' -> if (realSanta) x++ else x2++
                '<' -> if (realSanta) x-- else x2--
            }
            visited.add(Pair(x, y))
            visited.add(Pair(x2, y2))
            realSanta = !realSanta
        }


        return visited.size
    }

    check(part1(">") == 2)
    check(part1("^>v<") == 4)
    check(part1("^v^v^v^v^v") == 2)


    val input = readInput("Day03")
    part1(input.first()).println()

    check(part2("^v") == 3)
    check(part2("^>v<") == 3)
    check(part2("^v^v^v^v^v") == 11)

    part2(input.first()).println()
}
