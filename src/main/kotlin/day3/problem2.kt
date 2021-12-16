package day3

import java.io.File

fun main() {
    val inputList = File("src/main/resources/day3/input.txt").readLines()

    var listWithMostCommonValueInCurrentPosition = inputList
    var listWithLeastCommonValueInCurrentPosition = inputList
    for(i in 0..11){
        val mostCommonValue = findMostCommonValue(listWithMostCommonValueInCurrentPosition, i)
        listWithMostCommonValueInCurrentPosition = findAllWithGivenValueInCurrentPosition(
            listWithMostCommonValueInCurrentPosition, mostCommonValue, i
        )
        if(listWithMostCommonValueInCurrentPosition.size == 1)
            break
    }
    val oxygenRating = listWithMostCommonValueInCurrentPosition[0].toLong(2)

    for(i in 0..11){
        val leastCommonValue = findLeastCommonValue(listWithLeastCommonValueInCurrentPosition, i)
        listWithLeastCommonValueInCurrentPosition = findAllWithGivenValueInCurrentPosition(
            listWithLeastCommonValueInCurrentPosition, leastCommonValue, i
        )
        if(listWithLeastCommonValueInCurrentPosition.size == 1)
            break
    }

    val co2Rating = (listWithLeastCommonValueInCurrentPosition[0].toLong(2))

    println(oxygenRating * co2Rating)
}

fun findAllWithGivenValueInCurrentPosition(
    inputList: List<String>,
    value: Char,
    currentPosition: Int
): List<String> {
    val listWithGivenValueInCurrentPosition = mutableListOf<String>()
    inputList.forEach {
        if(it[currentPosition] == value)
        listWithGivenValueInCurrentPosition.add(it)
    }
    return listWithGivenValueInCurrentPosition
}

fun findMostCommonValue(inputList: List<String>, i: Int): Char {
    var oneCount = 0
    var zeroCount = 0
    inputList.forEach {
        when(it[i]){
            '1' -> ++oneCount
            '0' -> ++zeroCount
        }
    }
    return if(oneCount>=zeroCount) '1' else '0'
}

fun findLeastCommonValue(inputList: List<String>, i: Int): Char {
    var oneCount = 0
    var zeroCount = 0
    inputList.forEach {
        when(it[i]){
            '1' -> ++oneCount
            '0' -> ++zeroCount
        }
    }
    return if(zeroCount<=oneCount) '0' else '1'
}
