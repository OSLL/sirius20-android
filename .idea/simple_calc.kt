import kotlin.collections.contentToString as contentToString1

@Suppress("UNREACHABLE_CODE")
fun main (args: Array<String>) {
    val s: String =readLine().toString()
    val c: Arithmetic = Arithmetic (s)
    println (c.nums.contentToString1())
    println (c.opers.contentToString1())
    println (c.result)
}
class Arithmetic (str: String="0 = 1") {
    var strs: List<String> = str.split(" ")
    var i: Int = 0
    private var l: Int = strs.size
    var result: Int = strs[l-1].toInt()
    var nums = IntArray(l/2)
    var opers = CharArray(l/2-1)
    private fun lol (){for (i in 0..l-3) {if (i%2==0){nums[i/2]=strs[i].toInt()} else opers[i/2]=strs[i].single()}}
    var c : Any= lol()
    /*init{
        this.nums= nums
        this.opers=opers
        this.result=result
    }*/
}//if i actually need to count 'em, tell me, it won't be too hard
