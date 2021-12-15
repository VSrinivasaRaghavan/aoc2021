import java.io.File

fun main() {
    val list = File("src/main/resources/day1/input.txt").readLines()
    var answer=0
    for(i in 1 until list.size)
    {
        if(list[i].toBigInteger()>list[i-1].toBigInteger())
            ++answer
    }
    println(answer)
}