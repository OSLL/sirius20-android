fun main (args: Array<String>) {
    val s: String =readLine().toString()//input string of different ints and operators +-*/
    val c: Arithmetic = arithm(s)//class construct through function
    println ("$s=${c.result}")
    println (c.nums.contentToString())
    println (c.opers.contentToString())
    println (c.result)
}
class Arithmetic constructor (var nums: IntArray, var opers: CharArray, var result: Int)

fun arithm (s:String): Arithmetic {
    var R: Int = 0
    var N: MutableList<Int> = arrayListOf()
    var O: MutableList<Char> = arrayListOf()
    var S = s.toCharArray()
    for (i in 0..s.length-1){if (S[i] in "+-*/"){O.add(S[i]);S[i]=' '}}
    var z=S.joinToString(separator="")
    var Z=z.split(" ")
    for (i in 0..Z.size-1){N.add(Z[i].toInt())}
    var N1=N; var O1=O //we need clones of these to perform calculations
    val opers=O.toCharArray()
    val nums=N.toIntArray()
    var i=0 //time for some calculations
    while ('*' in O1 || '/' in O1){
        if (O1[i]=='*'){N1[i]*=N1[i+1];N1.removeAt(i+1);O1.removeAt(i)} else if (O1[i]=='/'){if(N1[i+1]!=0){N1[i]/=N1[i+1]}else{N1[i]=0};N1.removeAt(i+1);O1.removeAt(i)} else {i++}
    }//note that division by 0 is made to result in 0, to avoid errors
    i=0
    while ('+' in O1 || '-' in O1){
        if (O1[i]=='+'){N1[i]+=N1[i+1];N1.removeAt(i+1);O1.removeAt(i)} else if (O1[i]=='-'){N1[i]-=N1[i+1];N1.removeAt(i+1);O1.removeAt(i)} else {i++}
    }
    R=N1[0]
    val result=R
    return Arithmetic(nums,opers,result)//constructs a class
}//does this cost another commit