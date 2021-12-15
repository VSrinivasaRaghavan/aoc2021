package day2

import java.io.File

fun main() {
    val inputList = File("src/main/resources/day2/input.txt").readLines()
    var horizontal = 0
    var depth = 0
    var aim = 0

    inputList.forEach {
        val command = it.split(" ")
        val direction = command[0]
        val distance = command[1].toInt()

        when (direction) {
            "up" -> aim -= distance
            "down" -> aim += distance
            "forward" -> {
                horizontal += distance
                depth+=(aim*distance)
            }
        }
    }

    print(horizontal * depth)
}