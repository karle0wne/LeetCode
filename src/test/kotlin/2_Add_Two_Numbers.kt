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
        return recurs(l1!!, 0, l2!!, ListNode(0)).listNode
    }

    fun recurs(l1: ListNode, depthL1: Int, l2: ListNode, result: ListNode?): TransferObj {
        if (l1.next != null) {
            val transferObj = recurs(l1.next!!, depthL1 + 1, l2, result)
            return recursl2(l1, depthL1, transferObj.listNode, 0, transferObj.depth - 1, result, false)
        } else {
            return recursl2(l1, depthL1, l2, 0, 0, result, true)
        }
    }

    fun recursl2(
        l1: ListNode, depthL1: Int, l2: ListNode, depthL2: Int, limit: Int, result: ListNode?, first: Boolean
    ): TransferObj {
        if (l2.next != null && (depthL2 < limit || first)) {
            val transferObj = recursl2(l1, depthL1, l2.next!!, depthL2 + 1, limit, result, first)
            return if (transferObj.inc + l2.`val` > 9) {
                val sum = transferObj.inc + l2.`val`
                val listNode = ListNode(sum - 10)
                listNode.next = transferObj.listNode
                TransferObj(1, listNode, transferObj.depth)
            } else {
                val listNode = ListNode(l2.`val`)
                listNode.next = transferObj.listNode
                TransferObj(0, listNode, transferObj.depth)
            }
        } else if (limit >= 0) {
            val sum = l1.`val` + l2.`val`
            return if (sum > 9) {
                val listNode = ListNode(sum - 10)
                listNode.next = l2.next
                TransferObj(1, listNode, depthL2)
            } else {
                val listNode = ListNode(sum)
                listNode.next = l2.next
                TransferObj(0, listNode, depthL2)
            }
        } else {
            val listNode = ListNode(l1.`val`)
            listNode.next = l2
            return TransferObj(0, listNode, depthL2)
        }
    }
}

//  345 3 555555 6
class ListNode(var `val`: Int) {
    var next: ListNode? = null

    override fun toString(): String {
        return `val`.toString() + (next?.toString() ?: "")
    }
}

class TransferObj(val inc: Int, val listNode: ListNode, val depth: Int)
