enum class CommandVerb {
    SET,
    AND,
    OR,
    NOT,
    LSHIFT,
    RSHIFT
}

data class Command(val command: CommandVerb, var left: String, val right: String = "", val target: String)

fun main() {


    /**
     * Retrieves the value of a wire from the given HashMap, based on the provided key.
     * If the key consists only of digits, it is assumed to be a numeric value and is converted to an Int.
     * If the wire is not found in the HashMap, null is returned.
     *
     * @param wires The HashMap of wire names and their corresponding values.
     * @param key The key representing the wire.
     * @return The value of the wire, or null if the wire is not found.
     */
    fun getWireValue(wires: HashMap<String, Int>, key: String): Int? {
        if (key.all { it.isDigit() }) return key.toInt()
        if (!wires.contains(key)) return null
        return wires[key]!!
    }

    /**
     * Processes a list of commands and returns a HashMap of wire names and their corresponding values.
     *
     * @param commands The list of commands to process.
     * @return The HashMap of wire names and their corresponding values.
     */
    fun processCommands(commands: List<Command>): HashMap<String, Int> {
        val wires: HashMap<String, Int> = HashMap()

        var workingCopy = commands.toList()

        do workingCopy = workingCopy.filter {

            when (it.command) {
                CommandVerb.SET -> {
                    val left = getWireValue(wires, it.left)
                    if (left != null) {
                        wires[it.target] = left
                        false
                    } else {
                        true
                    }
                }

                CommandVerb.AND -> {
                    val left = getWireValue(wires, it.left)
                    val right = getWireValue(wires, it.right)
                    if (left != null && right != null) {
                        wires[it.target] = left and right
                        false
                    } else {
                        true
                    }
                }

                CommandVerb.OR -> {
                    val left = getWireValue(wires, it.left)
                    val right = getWireValue(wires, it.right)
                    if (left != null && right != null) {
                        wires[it.target] = left or right
                        false
                    } else {
                        true
                    }
                }

                CommandVerb.LSHIFT -> {
                    val left = getWireValue(wires, it.left)
                    val right = getWireValue(wires, it.right)
                    if (left != null && right != null) {
                        wires[it.target] = left shl right
                        false
                    } else {
                        true
                    }
                }

                CommandVerb.RSHIFT -> {
                    val left = getWireValue(wires, it.left)
                    val right = getWireValue(wires, it.right)
                    if (left != null && right != null) {
                        wires[it.target] = left shr right
                        false
                    } else {
                        true
                    }
                }

                CommandVerb.NOT -> {
                    val left = getWireValue(wires, it.left)
                    if (left != null) {
                        wires[it.target] = left.inv() and 0xFFFF
                        false
                    } else {
                        true
                    }
                }
            }
        }
        while (workingCopy.size > 0)

        return wires
    }

    /**
     * Parses the given list of strings and creates a list of commands based on the provided regular expression.
     * Each string in the list represents a command.
     *
     * @param lines The list of strings to parse.
     * @return The list of parsed commands.
     */
    fun parseCommands(lines: List<String>): List<Command> {
        val commandRegex = "([a-z]+|\\d+)? ?(AND |OR |LSHIFT |RSHIFT |NOT )?([a-z]+|\\d+)? ?-> ([a-z]+)".toRegex()
        return lines.map { line ->
            val parts = commandRegex.find(line)!!.groupValues
            when {
                parts[2] == "AND " -> Command(CommandVerb.AND, parts[1], parts[3], parts[4])
                parts[2] == "OR " -> Command(CommandVerb.OR, parts[1], parts[3], parts[4])
                parts[2] == "LSHIFT " -> Command(CommandVerb.LSHIFT, parts[1], parts[3], parts[4])
                parts[2] == "RSHIFT " -> Command(CommandVerb.RSHIFT, parts[1], parts[3], parts[4])
                parts[2] == "NOT " -> Command(CommandVerb.NOT, parts[3], "", parts[4])
                else -> Command(CommandVerb.SET, parts[1], "", parts[4])
            }
        }
    }

    /**
     * Processes a list of commands and returns a HashMap of wire names and their corresponding values.
     *
     * @return The HashMap of wire names and their corresponding values.
     */
    fun part1(lines: List<String>): HashMap<String, Int> {

        val commands = parseCommands(lines)
        return processCommands(commands)
    }

    /**
     * Executes a list of commands with a given b value and returns a HashMap of wire names and their corresponding values.
     *
     * @param lines The list of input strings.
     * @param bValue The value to be assigned to the wire "b" before processing the commands.
     * @return The HashMap of wire names and their corresponding values after executing the commands.
     */
    fun part2(lines: List<String>, bValue: Int): HashMap<String, Int> {
        val commands = parseCommands(lines)
        commands.find { it.target == "b" }?.left = bValue.toString()

        return processCommands(commands)
    }

    val testInput = readInput("Day07_test")
    val result = part1(testInput)
    check(result["d"] == 72)
    check(result["e"] == 507)
    check(result["f"] == 492)
    check(result["g"] == 114)
    check(result["h"] == 65412)
    check(result["i"] == 65079)
    check(result["x"] == 123)
    check(result["y"] == 456)

    val input = readInput("Day07")
    part1(input)["a"].println()
    part2(input, part1(input)["a"]!!.toInt()).get("a").println()
}
