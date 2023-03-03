import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.math.abs
import kotlin.test.assertTrue

class `67_Add_Binary` {

    companion object {
        @JvmStatic
        fun testValues(): List<Arguments> {
            return listOf(
                Arguments.of("1010", "1011"),
                Arguments.of("11", "1"),
                Arguments.of("011101", "0111011101"),
                Arguments.of("0", "0"),
                Arguments.of("1111", "1111"),
            )
        }
    }

    @ParameterizedTest
    @MethodSource("testValues")
    fun `add binary`(a: String, b: String) {
        val addBinary = addBinary(a, b)
        assertTrue { addBinary.isNotEmpty() }
        println(addBinary)
    }

    fun addBinary(a: String, b: String): String {
        var result: String = "";
        var aval = a
        var bval = b
        var isNextInc: Boolean = false
        val diff = a.length - b.length
        if (diff > 0) {
            bval = '0'.repeat(diff).plus(b)
        } else if (diff < 0) {
            aval = '0'.repeat(abs(diff)).plus(a)
        }
        for (i in aval.indices.reversed()) {
            val y = aval[i].toInt() - '0'.toInt()
            val v = bval[i].toInt() - '0'.toInt()
            val xor = y.xor(v).xor(isNextInc.toInt())
            result = xor.toString().plus(result)
            isNextInc = y + v + isNextInc.toInt() > 1
        }
        return if (isNextInc) {
            "1$result"
        } else {
            result
        }
    }

    fun Boolean.toInt() = if (this) 1 else 0
    fun Char.repeat(count: Int): String = this.toString().repeat(count)
}