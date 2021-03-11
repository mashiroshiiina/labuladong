package list;

public class ReverseList {

    public static void main(String[] args) {

    }

    /**
     * 递归反转单链表
     * @param head 头指针
     * @return 逆转后的链表的头指针
     */
    public static ListNode reverseList(ListNode head){
        //base case 到了链表尾部，记录最后一个节点的指针即为last
        if (head == null) {
            return head;
        }
        ListNode last = reverseList(head);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 将链表的前 n 个节点反转（n <= 链表长度）
     * @param head 头指针
     * @return
     */
    public static ListNode revereN(ListNode head,int n){
        ListNode successer = head;
        //base case 到了第N+1个节点，记录指向这个节点的指针为后驱节点
        if (n == 1){
            // 记录第 n + 1 个节点
            successer = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = revereN(head,n - 1);
        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successer;
        return last;
    }

    /**
     * 将链表的m 至 n 位反转
     * @param head 头指针
     * @param m 起始位置
     * @param n 结尾位置
     * @return
     */
    public static ListNode reverseBetween(ListNode head,int m,int n){
        //base case 当m=1时，相当于从头开始逆转前n位链表
        if (m == 1){
            //调用前N位链表反转
            return revereN(head , n);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next,m-1,n-1);
        return head;
    }

    /**
     * K个节点为一组反转单链表
     *      从前往后数出k个节点来，将链表分为两部分: 根据区间被逆转的子链表 和 余下的子链表。
     *      针对余下的子链表，递归调用上诉逻辑
     *      将两个部分的子链表连接上
     * @param head 链表头指针
     * @param k k个节点为一组
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k){
        ListNode a = head;
        ListNode b = head;
        for (int i = 0; i < k; i++) {
            if (b == null){
                return head;
            }
            b = b.next;
        }
        //逆转[a,b)
        ListNode newhead = reverseA2B(a,b);
        //递归逆转后续部分并衔接
        a.next = reverseKGroup(b,k);

        return newhead;
    }

    /**
     * 迭代反转单链表中的a,b部分
     *  注意：[a,b)相当于[head,null)
     * @param a
     * @param b
     * @return
     */
    public static ListNode reverseA2B(ListNode a,ListNode b){
        ListNode pre = b;
        ListNode cur = a;
        ListNode nxt = a;
        while (nxt != null){
            nxt = nxt.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    /**
     * 判断回文链表方法一: 反转后半段链表，以便实现类似两侧夹逼遍历的效果
     * 利用快慢指针寻找链表的中点，
     * 获取后半段子链表的head
     * 反转后半段子链表
     * 前后两个子链表同时向后遍历并比对值是否相等，不相等则返回false
     * @param head 链表头
     * @return
     */
    public static boolean isPalindrome1(ListNode head) {
        ListNode left = getReverseHead(head);
        ListNode right = head;
        while (left.next != null){
            if (left.val != right.val){
                return false;
            }
        }
        return true;
    }

    /**
     * 利用快慢指针技巧获取链表中间指针,并获取需要反转的链表的head
     * @param head
     * @return
     */
    public static ListNode getReverseHead(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        ListNode subHead;
        while(fast != null && fast.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null){
            //偶数链
            subHead = slow;
        }else{
            //奇数链
            subHead = slow.next;
        }

        return subHead;
    }

}
