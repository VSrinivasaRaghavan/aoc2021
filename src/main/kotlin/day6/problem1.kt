package day6

import java.io.File
import java.util.concurrent.CompletableFuture

fun main() {
    val input = File("src/main/resources/day6/input.txt").readText()

    val list = input.split(",").map { it.toInt() }.toMutableList()

    val fish = Array<Long>(10){ 0 }.toMutableList()

    list.forEach {
        ++fish[it]
    }
    for(day in 1..256){
        fish[7] += fish[0]
        fish[9] = fish[0]
        for(i in 0 until 9){
            fish[i] = fish[i+1]
        }
        fish[9] = 0
    }
    println(fish.sum())
}