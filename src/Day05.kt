fun main() {

    fun isNice(word: String): Boolean {
        val forbiddenCombosRegex = ".*(ab|cd|pq|xy).*".toRegex()
        //It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
        if (forbiddenCombosRegex.matches(word)) return false

        // It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
        if (word.count { it.equals('a') or it.equals('e') or it.equals('i') or it.equals('o') or it.equals('u') } < 3) return false

        //It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
        return word.toCharArray().groupBy { it.code }.filter {
            it.value.size > 1
        }.values.map { it.first() }.map { word.contains("$it$it") }.contains(true)

    }

    fun isNaughty(word: String): Boolean {
        // It contains a pair of any two letters that appears at least twice in the string without overlapping,
        // like xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).
        val doublePairRegex = ".*(\\w{2}).*\\1.*".toRegex()
        //It contains at least one letter which repeats with exactly one letter between
        // them, like xyx, abcdefeghi (efe), or even aaa.
        val tripletRegex = ".*(\\w)\\w\\1.*".toRegex()

        if (!(doublePairRegex matches word)) return true
        if (!(tripletRegex matches word)) return true
        return false
    }

    fun part1(lines: List<String>): Int {

        var score = 0
        for (line in lines) {
            if (isNice(line)) score++
        }
        return score

    }

    fun part2(lines: List<String>): Int {
        var score = 0
        for (line in lines) {
            if (!isNaughty(line)) score++
        }
        return score
    }

    // test if implementation meets criteria from the description, like:
    check(isNice("ugknbfddgicrmopn"))
    check(isNice("aaa"))
    check(!isNice("jchzalrnumimnmhp"))
    check(!isNice("haegwjzuvuyypxyu"))
    check(!isNice("dvszwmarrgswjxmb"))

    val input = readInput("Day05")
    part1(input).println()

    check(!isNaughty("qjhvhtzxzqqjkmpb"))
    check(!isNaughty("xxyxx"))
    check(isNaughty("uurcxstgmygtbstg"))
    check(isNaughty("ieodomkazucvgmuy"))
    part2(input).println()
}
