package day7

import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("src/main/resources/day7/input.txt").readText()
    val inputList = input.split(",").toList()

    var minimumFuel = 999999999999
    var totalFuelRequiredToReachPosition: Long
    inputList.sorted().forEach { destinationPosition ->
        totalFuelRequiredToReachPosition = 0
        inputList.forEach {
            totalFuelRequiredToReachPosition += abs(destinationPosition.toInt() - it.toInt())
        }
        if(totalFuelRequiredToReachPosition< minimumFuel)
            minimumFuel = totalFuelRequiredToReachPosition
    }
    print(minimumFuel)
}