fun main() {


    fun findSmallestNumber(line: String, prefix: String): Int {
        var md5: String = ""
        var testVal = 0
        do {
            testVal++
            md5 = (line + testVal.toString()).md5()

        } while (!md5.startsWith(prefix))

        return testVal
    }

    fun part1(line: String): Int {

        return findSmallestNumber(line, "00000")
    }

    fun part2(line: String): Int {

        return findSmallestNumber(line, "000000")
    }

    check(part1("abcdef") == 609043)
    check(part1("pqrstuv") == 1048970)

    part1("ckczppom").println()


    part2("ckczppom").println()
}
