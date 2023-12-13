fun main() {
    // Passwords must include one increasing straight of at least three letters, like abc, bcd, cde, and so on, up to xyz.
    // They cannot skip letters; abd doesn't count.
    val tripletsRegex =
        """.*?(abc|bcd|cde|def|efg|fgh|ghi|hij|ijk|jkl|klm|lmn|mno|nop|opq|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz).*""".toRegex()
    // Passwords must contain at least two different, non-overlapping pairs of letters, like aa, bb, or zz.
    val doubletsRegex = """.*?([a-z])\1.*?([a-z])\2""".toRegex()
    // Passwords may not contain the letters i, o, or l, as these letters can be mistaken for other characters and are therefore confusing.
    val iOLRegex = "iol".toRegex()

    fun checkValidity(password: String): Boolean {

        if (iOLRegex.matches(password)) return false;
        if (!doubletsRegex.matches(password)) return false;
        return tripletsRegex.matches(password)
    }

    fun incrementPassword(password: String): String {
        var newPassword = ""
        var overflow = 0
        var increment = 1
        for (i in password.length - 1 downTo 0) {
            var newChar = password[i] + increment + overflow
            increment = 0
            if (newChar > 'z') {
                newPassword = 'a' + newPassword
                overflow = 1
            } else {
                if ("iou".indexOf(newChar) > -1) {
                    newPassword = (newChar + 1) + "0".repeat(newPassword.length)
                } else {
                    newPassword = newChar + newPassword
                }
                overflow = 0
            }
        }
        return newPassword
    }

    fun part1(line: String): String {
        var password = line
        do {
            password = incrementPassword(password)
        } while (!checkValidity(password))

        return password
    }

    fun part2(line: String): String {
        return line
    }

    check(!checkValidity("hijklmmn"))
    check(!checkValidity("abbceffg"))
    check(!checkValidity("abbcegjk"))
    check(checkValidity("abcdffaa"))
    check(checkValidity("ghjaabcc"))
    // test if implementation meets criteria from the description, like:
    //check(part1("abcdfeyz") == "abcdffaa")
    //check(part1("ghj009tb") == "ghjaabcc")

    val part1 = part1("cqjxjnds")
    val part2 = part1(part1)

    "$part1 / $part2".println()

}
