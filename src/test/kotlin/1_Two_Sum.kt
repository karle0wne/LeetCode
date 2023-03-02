import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import kotlin.test.assertTrue

class `1_Two_Sum` {

    companion object {
        @JvmStatic
        fun testValues(): List<Arguments> {
            return listOf(
                Arguments.of(intArrayOf(2, 7, 11, 15), 9),
                Arguments.of(intArrayOf(3, 2, 4), 6),
                Arguments.of(intArrayOf(3, 3), 6)
            )
        }
    }

    @ParameterizedTest
    @MethodSource("testValues")
    fun `two sum`(nums: IntArray, target: Int) {
        val twoSum = twoSum(nums, target)
        assertTrue { twoSum.isNotEmpty() }
        println(twoSum.contentToString())
    }

    fun twoSum(nums: IntArray, target: Int): IntArray {
        val hashMap = HashMap<Int, Int>()
        for (i in nums.indices) {
            if (hashMap.contains(nums[i])) {
                return intArrayOf(hashMap[nums[i]]!!, i);
            } else {
                hashMap[target - nums[i]] = i
            }
        }
        return intArrayOf();
    }
}
