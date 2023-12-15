import kotlinx.serialization.json.*

fun main() {


    var stack = ""

    fun part1(line: String): Int {

        val tester = Regex("""([-0-9]+)""")
        val matches = tester.findAll(line)
        matches.map {
            it.groupValues[1].println()
        }
        val result = matches.map {
            it.groupValues[1].toInt()
        }.sum()

        return result

    }


    fun calculate(jsonElement: JsonElement): Int {
        var sum = 0
        if (jsonElement is JsonObject) {
            val keys = jsonElement.keys
            keys.forEach { key ->
                val element = jsonElement[key]
                if (element is JsonObject || element is JsonArray) {
                    sum += calculate(element);
                } else {
                    val value = element!!.jsonPrimitive.content
                    if (value == "red") return 0
                    val numValue = value.toIntOrNull()
                    if (numValue != null) {
                        sum +=numValue
                    }
                }
            }
        } else if (jsonElement is JsonArray) {
            for (i in 0 until jsonElement.size) {
                val element = jsonElement[i]
                if (element is JsonObject || element is JsonArray) {
                    sum += calculate(element);
                } else {
                    val value = element.jsonPrimitive.content
                    val numValue = value.toIntOrNull()
                    if (numValue != null) {
                        sum +=numValue
                    }
                }

            }
        }
        return sum;
    }

    fun part2(line: String): Int {

        val jsonElement: JsonElement = Json.parseToJsonElement(line)

        return calculate(jsonElement);
    }


    // test if implementation meets criteria from the description, like:
    //val testInput = readInput("Day12_test")
    check(part1("[1,2,3]") == 6)
    check(part1("""{"a":2,"b":4}""") == 6)
    check(part1("[[[3]]]") == 3)
    check(part1("""{"a":{"b":4},"c":-1}""") == 3)
    check(part1("""{"a":[-1,1]}""") == 0)
    check(part1("""[-1,{"a":1}]""") == 0)
    check(part1("[]") == 0)
    check(part1("{}") == 0)

    val input = readInput("Day12")
    part1(input[0]).println()
    part2(input[0]).println()
}
