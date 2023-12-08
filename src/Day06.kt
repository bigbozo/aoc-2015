import java.awt.Color
import kotlin.math.max
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main() {


    fun createPNG(filename: String, grid: Array<Array<Int>>) {
        val bufferedImage = BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB)
        val g2d = bufferedImage.createGraphics()

        for (cx in 0 until 1000) {
            for (cy in 0 until 1000) {
                g2d.color = Color(10 * grid[cx][cy])
                g2d.drawRect(cx, cy, 1, 1)
            }
        }
        val file = File("output/day06.$filename.png")
        ImageIO.write(bufferedImage, "png", file)
    }

    fun part1(lines: List<String>): Int {

        val parserRegex = "^(turn on|turn off|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)$".toRegex()
        val grid = Array(1000) { Array(1000) { 0 } }

        for (line in lines) {
            val match = parserRegex.find(line)

            val command = match!!.groupValues[1]
            val x = match.groupValues[2]
            val y = match.groupValues[3]
            val x2 = match.groupValues[4]
            val y2 = match.groupValues[5]

            for (cx in x.toInt() until x2.toInt() + 1) {
                for (cy in y.toInt() until y2.toInt() + 1) {
                    when (command) {
                        "turn off" -> grid[cx][cy] = 0
                        "turn on" -> grid[cx][cy] = 1
                        "toggle" -> grid[cx][cy] = 1 - grid[cx][cy]
                        else -> println("nonono")
                    }
                }
            }
        }
        createPNG("part1", grid)
        return grid.map { it.sum() }.sum()
    }

    fun part2(lines: List<String>): Int {
        val parserRegex = "^(turn on|turn off|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)$".toRegex()
        val grid = Array(1000) { Array(1000) { 0 } }


        for (line in lines) {
            val match = parserRegex.find(line)

            val command = match!!.groupValues[1]
            val x = match.groupValues[2]
            val y = match.groupValues[3]
            val x2 = match.groupValues[4]
            val y2 = match.groupValues[5]

            for (cx in x.toInt() until x2.toInt() + 1) {
                for (cy in y.toInt() until y2.toInt() + 1) {
                    when (command) {
                        "turn off" -> grid[cx][cy] = max(0, grid[cx][cy] - 1)
                        "turn on" -> grid[cx][cy] += 1
                        "toggle" -> grid[cx][cy] += 2
                        else -> println("nonono")
                    }
                }
            }
        }
        createPNG("part2", grid)
        return grid.map { it.sum() }.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 16)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
