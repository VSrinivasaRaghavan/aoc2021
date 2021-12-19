package day4

import java.io.File

fun main() {
    val input = File("src/main/resources/day4/input.txt").readText()
    val splitBasedOnNewEmptyLines = input.split("\n\n")
    val toBeCalledNumbers = splitBasedOnNewEmptyLines[0].split(",")
    val bingoMatrices = formBingoMatrixFromInput(splitBasedOnNewEmptyLines)
    var markingRow = Array(bingoMatrices.size) { IntArray(5).toMutableList() }.toMutableList()
    var markingColumn = Array(bingoMatrices.size) { IntArray(5).toMutableList() }.toMutableList()
    var breakOuterLoop = false
    var playerIndexByRow = 0
    var playerIndexByColumn = 0
    var lastCalledNumberByRow = 0
    var lastCalledNumberByColumn = 0

    for (it in toBeCalledNumbers) {
        markingRow = markTheCalledNumberInRow(it, bingoMatrices, markingRow)
        markingColumn = markTheCalledNumberInColumn(it, bingoMatrices, markingColumn)

        markingRow.forEachIndexed { index, markedRow ->
            if(markedRow.contains(5)){
                breakOuterLoop = true
                playerIndexByRow = index
                lastCalledNumberByRow = it.toInt()
            }
        }
            markingColumn.forEachIndexed { index, markedColumn ->
                if (markedColumn.contains(5)) {
                    breakOuterLoop = true
                    playerIndexByColumn = index
                    lastCalledNumberByColumn = it.toInt()
                }
            }

        if(breakOuterLoop)
            break
    }
    val indexOfLastCalledNumberByRow = toBeCalledNumbers.indexOf(lastCalledNumberByRow.toString())
    val indexOfLastCalledNumberByColumn = toBeCalledNumbers.indexOf(lastCalledNumberByColumn.toString())
    var playerIndex = 0
    var lastCalledNumber = 0
    var actuallyCalledNumbers = listOf<String>()
    when {
        (indexOfLastCalledNumberByColumn != 0 && indexOfLastCalledNumberByColumn>indexOfLastCalledNumberByRow) -> {
            playerIndex = playerIndexByColumn
            lastCalledNumber = lastCalledNumberByColumn
            actuallyCalledNumbers = toBeCalledNumbers.subList(0, indexOfLastCalledNumberByColumn + 1)
        }
        (indexOfLastCalledNumberByRow != 0 && indexOfLastCalledNumberByRow>indexOfLastCalledNumberByColumn) -> {
            playerIndex = playerIndexByRow
            lastCalledNumber = lastCalledNumberByRow
            actuallyCalledNumbers = toBeCalledNumbers.subList(0, indexOfLastCalledNumberByRow + 1)
        }
    }
    var sumOfUncalledNumbers = 0

    bingoMatrices[playerIndex].forEach { winningMatrixRow ->
        winningMatrixRow.forEach {
            if(!actuallyCalledNumbers.contains(it)) {
                sumOfUncalledNumbers += it.toInt()
            }
        }
    }

    println(sumOfUncalledNumbers*lastCalledNumber)
}

fun markTheCalledNumberInColumn(
    calledNumber: String,
    bingoMatrices: List<List<List<String>>>,
    markingColumn: MutableList<MutableList<Int>>
): MutableList<MutableList<Int>> {
    bingoMatrices.forEachIndexed { playerIndex, bingoMatrix ->
        bingoMatrix.forEach { row ->
            (0..4).forEach {
                if(row[it] == calledNumber)
                    markingColumn[playerIndex][it]+=1
            }
        }
    }
    return markingColumn
}

fun markTheCalledNumberInRow(
    calledNumber: String,
    bingoMatrices: List<List<List<String>>>,
    markingList: MutableList<MutableList<Int>>
): MutableList<MutableList<Int>> {
    bingoMatrices.forEachIndexed { playerIndex, bingoMatrix ->
        bingoMatrix.forEachIndexed { rowIndex, row ->
            row.forEach {
                if(it == calledNumber) {
                    markingList[playerIndex][rowIndex] += 1
                }
            }
        }
    }
    return markingList
}

private fun formBingoMatrixFromInput(splitBasedOnNewEmptyLines: List<String>): List<List<List<String>>> {
    val inputMatrix = mutableListOf<List<List<String>>>()
    var list = mutableListOf<List<String>>()
    var matrixSize = 0
    for (i in 1 until splitBasedOnNewEmptyLines.size) {
        val inputLineList = splitBasedOnNewEmptyLines[i].split("\n")
        inputLineList.forEach {
            val inputList = it.split(",")
            list.add(inputList)
            ++matrixSize
        }

        if(matrixSize == 5) {
            matrixSize=0
            inputMatrix.add(list)
            list = mutableListOf()
        }
    }
    return inputMatrix
}