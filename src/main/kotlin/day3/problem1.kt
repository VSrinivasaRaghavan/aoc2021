package day3

import java.io.File

fun main() {
    val inputList = File("src/main/resources/day3/input.txt").readLines()
    var gamma = ""
    var epsilon = ""
    for (i in 0..11) {
        var countOf1s = 0
        var countOf0s = 0
        inputList.forEach {
            when(it[i]){
                '1' -> countOf1s++
                '0' -> countOf0s++
            }
        }
        gamma += if(countOf1s>countOf0s) 1 else 0
        epsilon += if(countOf0s<countOf1s) 0 else 1
    }

    print(gamma.toLong(2) * epsilon.toLong(2))
}