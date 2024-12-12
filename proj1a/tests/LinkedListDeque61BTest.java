import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    public void isEmptyTestEmptyList() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.isEmpty()).isTrue(); // 期望：链表为空


        LinkedListDeque61B<String> lld2 = new LinkedListDeque61B<>();

        lld2.addFirst("front"); // 添加一个元素后，我们期望：["front"]
        assertThat(lld2.isEmpty()).isFalse(); // 期望：链表不为空


        LinkedListDeque61B<String> lld3 = new LinkedListDeque61B<>();

        lld3.addFirst("front"); // 添加一个元素后，我们期望：["front"]
        lld3.addLast("back"); // 再添加一个元素，我们期望：["front", "back"]
        assertThat(lld3.isEmpty()).isFalse(); // 期望：链表不为空
    }
    @Test
    public void sizeTestEmptyList() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.size()).isEqualTo(0); // 期望：链表大小为0
    }
    @Test
    public void sizeTestSingleElement() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("front"); // 添加一个元素后，我们期望：["front"]
        assertThat(lld1.size()).isEqualTo(1); // 期望：链表大小为1
    }
    @Test
    public void sizeTestMultipleElements() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("front"); // 添加一个元素后，我们期望：["front"]
        lld1.addLast("back"); // 再添加一个元素，我们期望：["front", "back"]
        assertThat(lld1.size()).isEqualTo(2); // 期望：链表大小为2
    }
    @Test
    public void removeFirstTestEmptyList() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.removeFirst()).isEqualTo(null); // 期望：返回null，因为没有元素可以移除
    }
    @Test
    public void removeFirstTestSingleElement() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst("front");

        String removed = lld1.removeFirst();
        assertThat(removed).isEqualTo("front"); // 期望：返回"front"
        assertThat(lld1.size()).isEqualTo(0); // 期望：链表大小为0
    }
    @Test
    public void removeFirstTestMultipleElements() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        String removed = lld1.removeFirst();
        assertThat(removed).isEqualTo("front"); // 期望：返回"front"
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder(); // 期望：链表剩余["middle", "back"]
    }
    @Test
    public void removeFirstTestRemoveAllElements() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld1.removeFirst(); // 移除"front"
        lld1.removeFirst(); // 移除"middle"
        String removed = lld1.removeFirst();
        assertThat(removed).isEqualTo("back"); // 期望：返回"back"
        assertThat(lld1.isEmpty()).isTrue(); // 期望：链表为空
    }
    @Test
    public void removeLastTestEmptyList() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.removeLast()).isNull(); // 期望：返回null，因为没有元素可以移除
    }

    @Test
    public void removeLastTestSingleElement() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");

        String removed = lld1.removeLast();
        assertThat(removed).isEqualTo("front"); // 期望：返回"front"
        assertThat(lld1.isEmpty()).isTrue(); // 期望：链表为空
    }

    @Test
    public void removeLastTestMultipleElements() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        String removed = lld1.removeLast();
        assertThat(removed).isEqualTo("back"); // 期望：返回"back"
        assertThat(lld1.toList()).containsExactly("front", "middle").inOrder(); // 期望：链表剩余["front", "middle"]
    }

    @Test
    public void removeLastTestRemoveAllElements() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("1");
        lld1.addLast("2");
        lld1.addLast("3");

        lld1.removeLast(); // 移除"3"
        lld1.removeLast(); // 移除"2"
        String removed = lld1.removeLast();
        assertThat(removed).isEqualTo("1"); // 期望：返回"1"
        assertThat(lld1.isEmpty()).isTrue(); // 期望：链表为空
    }

    @Test
    public void removeLastTestRemoveLastFromSortedList() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("1");
        lld1.addLast("2");
        lld1.addLast("3");

        String removed = lld1.removeLast();
        assertThat(removed).isEqualTo("3"); // 期望：返回"3"
        assertThat(lld1.toList()).containsExactly("1", "2").inOrder(); // 期望：链表剩余["1", "2"]
    }
    @Test
    public void getTestInvalidIndex() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertThat(lld1.get(3)).isNull(); // 期望：返回null，因为索引超出范围
    }

    @Test
    public void getTestFirstElement() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertThat(lld1.get(0)).isEqualTo("front"); // 期望：返回"front"，第一个元素
    }

    @Test
    public void getTestMiddleElement() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertThat(lld1.get(1)).isEqualTo("middle"); // 期望：返回"middle"，中间元素
    }

    @Test
    public void getTestLastElement() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertThat(lld1.get(2)).isEqualTo("back"); // 期望：返回"back"，最后一个元素
    }

    @Test
    public void getTestSingleElement() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("single");

        assertThat(lld1.get(0)).isEqualTo("single"); // 期望：返回"single"，唯一的元素
    }

    @Test
    public void getTestEmptyList() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.get(0)).isNull(); // 期望：返回null，因为列表为空
    }
    @Test
    public void getRecursiveTestInvalidIndex() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertThat(lld1.getRecursive(3)).isNull(); // 期望：返回null，因为索引超出范围
    }

    @Test
    public void getRecursiveTestFirstElement() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertThat(lld1.getRecursive(0)).isEqualTo("front"); // 期望：返回"front"，第一个元素
    }

    @Test
    public void getRecursiveTestMiddleElement() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertThat(lld1.getRecursive(1)).isEqualTo("middle"); // 期望：返回"middle"，中间元素
    }

    @Test
    public void getRecursiveTestLastElement() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertThat(lld1.getRecursive(2)).isEqualTo("back"); // 期望：返回"back"，最后一个元素
    }

    @Test
    public void getRecursiveTestSingleElement() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("single");

        assertThat(lld1.getRecursive(0)).isEqualTo("single"); // 期望：返回"single"，唯一的元素
    }

    @Test
    public void getRecursiveTestEmptyList() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.getRecursive(0)).isNull(); // 期望：返回null，因为列表为空
    }

    @Test
    public void getRecursiveTestNegativeIndex() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertThat(lld1.getRecursive(-1)).isNull(); // 期望：返回null，因为索引是负数
    }
}