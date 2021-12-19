package day7

import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("src/main/resources/day7/input.txt").readText()
    val inputList = input.split(",").toList()

    var minimumFuel = 999999999999
    var totalFuelRequiredToReachPosition: Long
    val maximumDistance = inputList.maxOf { it.toInt() }
    for(position in 0..maximumDistance){
        totalFuelRequiredToReachPosition = 0
        inputList.forEach {
            val distance = abs(position - it.toInt())
            totalFuelRequiredToReachPosition += calculateRequiredFuelToReachPosition(distance)
        }
        if(totalFuelRequiredToReachPosition< minimumFuel)
            minimumFuel = totalFuelRequiredToReachPosition
    }
    print(minimumFuel)
}

private fun calculateRequiredFuelToReachPosition(distance: Int) = (distance*(distance+1))/2