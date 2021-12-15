package day1

import java.io.File

fun main() {
    val list = File("src/main/resources/day1/input.txt")
        .readLines()
        .map { it.toInt() }
    var answer=0
    var currentMeasurementWindow: Int
    var prevMeasurementWindow = 0
    for(i in 0 until (list.size-3))
    {
        currentMeasurementWindow = list[i] + list[i+1] + list[i+2]
        if(currentMeasurementWindow>prevMeasurementWindow)
            ++answer
        prevMeasurementWindow = currentMeasurementWindow
    }
    println(answer)
}