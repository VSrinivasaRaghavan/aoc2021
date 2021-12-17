package day6

import java.io.File

fun main() {
    val input = File("src/main/resources/day6/input.txt").readText()

    val list = input.split(",").map { it.toInt() }.toMutableList()

    for(i in 1..80){
        for(j in list.indices){
            if(list[j] == 0){
                list[j] = 6
                list.add(8)
            }
            else --list[j]
        }
    }

    println(list.size)
}