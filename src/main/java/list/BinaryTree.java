package list;

import java.util.Iterator;
import java.util.LinkedList;

public class BinaryTree {

    public static void main(String[] args) {

    }

    /**
     * 反转二叉树，交换二叉树的每一个左右子节点
     *
     *    值得注意的是，这道题的本质是对根节点进行交换操作，因而需要保证每一个节点都被访问一遍，
     *    此时前序遍历和后序遍历是可以保证交换后也可以访问每一个节点
     *    但是中序遍历后，在左根右的顺序下，访问右子树时其实是访问了交换后的左节点，此时可以改成访问两次左节点
     *
     *    同时，不能使用递归返回的left 和 right指针来进行交换操作，
     *    因为这两个指针相互交换并没有改变root节点存储的子树指针指向的位置，
     *    而返回的是root节点
     * @param root 根
     * @return
     */
    public static TreeNode invertTree(TreeNode root){
        if (root == null){
            return root;
        }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        return root;
    }

    /**
     *  leetcode 114 - 解法一 左右根
     * @param root
     */
    public static void flatten1(TreeNode root) {
        if (root.left == null || root.right == null){
            return ;
        }

        flatten1(root.left);
        flatten1(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.right = left;

        TreeNode p = root.right;
        while (p.right != null){
            p = p.right;
        }
        p.right = right;

    }

    /**
     *  leetcode 114 - 解法二 右左根
     * @param root
     */
    //定义成员变量
    /**
     *  p
     */
    private static TreeNode p = null;
    public static void fallten2(TreeNode root){
        if (root == null){
            return;
        }
        fallten2(root.right);
        fallten2(root.left);
        root.right = p;
        root.left = null;
        p = root;
    }


}
