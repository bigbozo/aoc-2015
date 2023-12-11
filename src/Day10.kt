fun main() {

    fun generateNext(s: String): String {
        var oldChar = -1
        var count = 0
        var result = ""
        for (char in s) {
            if (oldChar == -1) {
                oldChar = char.digitToInt()
                count = 1
            } else {
                if (char.digitToInt() == oldChar) {
                    count++
                } else {
                    result += "$count$oldChar"
                    oldChar = char.digitToInt()
                    count=1
                }
            }
        }
        result += "$count$oldChar"

        return result
    }

    fun solve(start: String, iterations: Int): String {
        return (1..iterations).fold(start) { current, _ ->

            generateNext(current)
        }
    }

    check(solve("1", 5) == "312211")

    solve("3113322113", 40).length.println()
    solve("3113322113", 50).length.println()
}
