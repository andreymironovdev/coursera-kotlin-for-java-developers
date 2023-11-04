package week4.rationals

import java.math.BigInteger

class Rational(n: BigInteger, d: BigInteger) : Comparable<Rational> {
    val numer: BigInteger
    val denom: BigInteger

    init {
        require(d != BigInteger.ZERO)

        val gcd = n.gcd(d)
        val sign = d.signum().toBigInteger()
        numer = n / gcd * sign
        denom = d / gcd * sign
    }

    operator fun plus(another: Rational): Rational {
        val newX = this.numer * another.denom + this.denom * another.numer
        val newY = this.denom * another.denom
        return Rational(newX, newY)
    }

    operator fun minus(another: Rational): Rational {
        val newX = this.numer * another.denom - this.denom * another.numer
        val newY = this.denom * another.denom
        return Rational(newX, newY)
    }

    operator fun times(another: Rational): Rational {
        val newX = this.numer * another.numer
        val newY = this.denom * another.denom
        return Rational(newX, newY)
    }

    operator fun div(another: Rational): Rational {
        val newX = this.numer * another.denom
        val newY = this.denom * another.numer
        return Rational(newX, newY)
    }

    operator fun unaryMinus(): Rational {
        return Rational(-this.numer, this.denom)
    }

    override operator fun compareTo(other: Rational): Int {
        return (this.numer * other.denom).compareTo(this.denom * other.numer)
    }

    override fun equals(other: Any?): Boolean = when (other) {
        is Rational -> this.numer == other.numer && this.denom == other.denom
        else -> super.equals(other)
    }

    override fun toString(): String {
        return when (denom) {
            BigInteger.ONE -> "$numer"
            else -> "$numer/$denom"
        }
    }

    override fun hashCode(): Int {
        var result = numer.hashCode()
        result = 31 * result + denom.hashCode()
        return result
    }
}

infix fun Int.divBy(y: Int): Rational {
    return Rational(this.toBigInteger(), y.toBigInteger())
}

infix fun Long.divBy(y: Long): Rational {
    return Rational(this.toBigInteger(), y.toBigInteger())
}

infix fun BigInteger.divBy(y: BigInteger): Rational {
    return Rational(this, y)
}

fun String.toRational(): Rational {
    return when {
        this.contains("/") -> {
            val split = this.split("/").map { BigInteger(it, 10) }
            Rational(split[0], split[1])
        }
        else -> Rational(BigInteger(this, 10), BigInteger.ONE)
    }
}

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println(
        "912016490186296920119201192141970416029".toBigInteger() divBy
                "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2
    )
}