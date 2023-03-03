import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import kotlin.test.assertTrue

class `66_Plus _One` {

    companion object {
        @JvmStatic
        fun testValues(): List<Arguments> {
            return listOf(
                Arguments.of(intArrayOf(1, 2, 3, 4)),
                Arguments.of(intArrayOf(5, 6, 7, 8)),
                Arguments.of(intArrayOf(9, 9, 9, 9)),
                Arguments.of(intArrayOf(9, 9, 8, 9)),
                Arguments.of(intArrayOf(9, 0, 9, 9)),
            )
        }
    }

    @ParameterizedTest
    @MethodSource("testValues")
    fun `plus one`(nums: IntArray) {
        val plusOne = plusOne(nums)
        assertTrue { plusOne.isNotEmpty() }
        println(plusOne.contentToString())
    }

    fun plusOne(digits: IntArray): IntArray {
        for (i in digits.indices.reversed()) {
            if (digits[i] + 1 == 10) {
                digits[i] = 0
            } else {
                digits[i]++
                return digits
            }
        }
        val intArray = IntArray(digits.size + 1)
        intArray[0] = 1
        return intArray;
    }
}