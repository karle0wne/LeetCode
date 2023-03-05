import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import kotlin.test.assertNotNull

class `2_Add_Two_Numbers` {

    companion object {
        @JvmStatic
        fun testValues(): List<Arguments> {
            return listOf(
                Arguments.of(listNode(2, 3, 4), listNode(5, 5, 5, 5, 5, 5)),
                Arguments.of(listNode(5, 5, 5, 5, 5, 5), listNode(2, 3, 4)),
                Arguments.of(listNode(9, 9, 9, 9, 9, 9, 9), listNode(9, 9, 9, 9)),
            )
        }

        private fun listNode(vararg args: Int): ListNode {
            val list = args.map { arg -> ListNode(arg) }.toList()
            for (i in list.size - 2 downTo 0 step 1) {
                list[i].next = list[i + 1]
            }
            return list[0]
        }
    }

    @ParameterizedTest
    @MethodSource("testValues")
    fun `add two numbers`(l1: ListNode?, l2: ListNode?) {
        val twoNumbs = addTwoNumbers(l1, l2)
        assertNotNull(twoNumbs)
        println(twoNumbs.toString())
    }

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        return round(l1!!, l2!!, 0)
    }

    fun round(l1: ListNode?, l2: ListNode?, unitItMind: Int): ListNode? {
        if (l1?.next != null || l2?.next != null) {
            val sum = (l2?.`val` ?: 0) + (l1?.`val` ?: 0) + unitItMind
            return if (sum > 9) {
                val listNode = round(l1?.next, l2?.next, 1)
                ListNode(sum - 10).apply { next = listNode }
            } else {
                val listNode = round(l1?.next, l2?.next, 0)
                ListNode(sum).apply { next = listNode }
            }
        } else {
            val sum = (l2?.`val` ?: 0) + (l1?.`val` ?: 0) + unitItMind
            return if (sum > 9) {
                ListNode(sum - 10).apply { next = ListNode(1) }
            } else {
                ListNode(sum)
            }
        }
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null

    override fun toString(): String {
        return `val`.toString() + (next?.toString() ?: "")
    }
}
