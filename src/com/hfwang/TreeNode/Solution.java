package com.hfwang.TreeNode;


import java.util.HashSet;
import java.util.LinkedList;

public class Solution {

    void traverse0(TreeNode root) {
        // 前序遍历
        traverse0(root.left);
        // 中序遍历
        traverse0(root.right);
        // 后序遍历

    }

    // 二叉树题目的一个难点就是，如何把题目的要求细化成每个节点需要做的事情
    // 计算二叉树节点个数
    int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + count(root.left) + count(root.right);
    }

    // 镜像反转
    TreeNode invertTree(TreeNode root) {
        // base case
        if (root == null) {
            return null;
        }

        // 前序遍历
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    // 116. 填充每个节点的下一个右侧节点指针
    Node connect(Node root) {
        if (root == null) return null;
        connectTwoNode(root.left, root.right);
        return root;
    }

    void connectTwoNode(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        node1.next = node2;

        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node2.left, node2.right);
        connectTwoNode(node1.right, node2.left);
    }

    // 114. 二叉树展开为链表
    void flatten(TreeNode root) {
        // base case
        if (root == null)
            return;

        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;
        root.right = left;

        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

    // 654. 最大二叉树
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    public TreeNode build(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        int index = -1;
        int maxVal = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (maxVal < nums[i]) {
                index = i;
                maxVal = nums[i];
            }
        }
        TreeNode root = new TreeNode(maxVal);

        root.left = build(nums, lo, index - 1);
        root.right = build(nums, index + 1, hi);
        return root;
    }

    // 105. 从前序与中序遍历序列构造二叉树
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }

    public TreeNode build(int[] preorder, int preStart, int preEnd,
                          int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        int rootVal = preorder[preStart];

        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }

        int leftSize = index - inStart;
        TreeNode root = new TreeNode(rootVal);
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, inStart + leftSize - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                inorder, index + 1, inEnd);

        return root;
    }

    // 106. 从中序与后序遍历序列构造二叉树
    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        return build1(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
    }
    public TreeNode build1(int[] inorder,  int inStart,  int inEnd,
                          int[] postorder,int postStart,int postEnd){

        if(inStart>=inEnd){
            return null;
        }

        int rootVal = postorder[postEnd];

        int index = -1;
        for(int i = 0; i <= inEnd;i++){
            if(inorder[i]==rootVal){
                index = i;
                break;
            }
        }
        int leftSize = index - inStart;
        TreeNode root = new TreeNode(rootVal);
        root.left  = build(inorder,inStart,index-1,postorder,postStart,postStart+leftSize-1);
        root.right = build(inorder,index+1,inEnd,postorder,postStart+leftSize,postEnd-1);

        return root;
    }

    // 652. 寻找重复的子树
    //
    //


    // 230. 二叉搜索树中第K小的元素
    public int kthSmallest(TreeNode root, int k) {
        traverse1(root,k);
        return res;
    }

    int res = 0;
    int rank = 0;
    void traverse1(TreeNode root,int k){
        if(root == null){
            return;
        }
        traverse1(root.left,k);
        rank++;
        if(k==rank){
            res = root.val;
            return;
        }
        traverse1(root.right,k);

    }


}
