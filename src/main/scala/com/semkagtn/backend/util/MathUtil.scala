package com.semkagtn.backend.util

object MathUtil {

  def pow(x: Int, n: Int): Double = {
    def rec(x: Int, n: Int): Int = {
      if (n == 0)
        1
      else if (n % 2 == 0)
        rec(x * x, n / 2)
      else
        x * rec(x, n - 1)
    }

    if (x == 0 && n == 0)
      Double.NaN
    else if (n > 0)
      rec(x, n)
    else
      1.0 / rec(x, -n)
  }
}
