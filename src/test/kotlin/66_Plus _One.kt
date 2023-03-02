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
        val destination = LinkedList<Int>()
        var isNextInc: Boolean = false
        var checkIsAlive: Boolean = true
        var temp: Int = 0
        for (digit in digits.reversed()) {
            if (checkIsAlive) {
                if (digit + 1 == 10) {
                    temp = 0
                    isNextInc = true
                } else {
                    temp = digit + 1
                    isNextInc = false
                }
            } else {
                temp = digit
            }
            checkIsAlive = isNextInc;
            destination.addFirst(temp)
        }
        if (isNextInc) {
            destination.addFirst(1)
        }
        return destination.toIntArray()
    }
}