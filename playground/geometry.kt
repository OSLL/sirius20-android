import kotlin.math.acos
import kotlin.math.sqrt


fun square(x:Float):Float{
    return x*x
}
class Dot(var x:Float, var y:Float)

class triangle( var dot_1:Dot, var dot_2:Dot, var dot_3:Dot) {
    public var line_12:Float = sqrt(square(this.dot_1.x-this.dot_2.x)+square(this.dot_1.y-this.dot_2.y))
    public var line_23:Float = sqrt(square(this.dot_3.x-this.dot_2.x)+square(this.dot_3.y-this.dot_2.y))
    public var line_13:Float = sqrt(square(this.dot_1.x-this.dot_3.x)+square(this.dot_1.y-this.dot_3.y))

    public var angle_1: Double = acos((square(line_23)-square(line_13)-square(line_12))/(-2f*line_13*line_12))*57.3
    public var angle_2: Double = acos((square(line_13)-square(line_23)-square(line_12))/(-2f*line_23*line_12))*57.3
    public var angle_3: Double = 180-angle_1-angle_2
}

fun isTriangleSharp(triangle: triangle): Boolean{
    if (triangle.angle_1<90 && triangle.angle_2<90 && triangle.angle_3<90){
        return true
} else {
        return false
    }
}
