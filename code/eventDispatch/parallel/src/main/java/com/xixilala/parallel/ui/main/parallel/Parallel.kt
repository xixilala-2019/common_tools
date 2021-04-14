package com.xixilala.parallel.ui.main.parallel

data class Parallel(
    val x_in:Float  = 0F,
    val x_out:Float = 0F,
    val y_in:Float  = 0F,
    val y_out:Float = 0F,
    val a_in:Float  = 0F,
    val a_out:Float = 0F
) {
    override fun toString(): String {
        return "Parallel(x_in=$x_in, x_out=$x_out, y_in=$y_in, y_out=$y_out, a_in=$a_in, a_out=$a_out)"
    }
}