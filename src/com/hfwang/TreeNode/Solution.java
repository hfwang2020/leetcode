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


    // 538. 把二叉搜索树转换为累加树
    public TreeNode convertBST(TreeNode root) {
        traverse2(root);
        return root;
    }

    int sum=0;
    public void traverse2(TreeNode root){
        if(root == null){
            return ;
        }

        traverse2(root.right);
        sum+=root.val;
        root.val = sum;
        traverse2(root.left);
    }

    // 98. 验证二叉搜索树
    // 此处leetcode测试用例卡边界值
    long value_98 = Long.MIN_VALUE;
    boolean res1 = true;
    public boolean isValidBST(TreeNode root) {
        traverse3(root);
        return res1;
    }

    public void traverse3(TreeNode root){
        if(root == null) return;
        traverse3(root.left);
        res1 = res1 && ( root.val < value_98);
        value_98 = root.val;
        traverse3(root.right);
    }

    // 98. 验证二叉搜索树 (dongGE)
    boolean isValidBST1(TreeNode root) {
        return isValidBST1(root, null, null);
    }

    /* 限定以 root 为根的子树节点必须满足 max.val > root.val > min.val */
    boolean isValidBST1(TreeNode root, TreeNode min, TreeNode max) {
        // base case
        if (root == null) return true;
        // 若 root.val 不符合 max 和 min 的限制，说明不是合法 BST
        if (min != null && root.val <= min.val) return false;
        if (max != null && root.val >= max.val) return false;
        // 限定左子树的最大值是 root.val，右子树的最小值是 root.val
        return isValidBST1(root.left, min, root)
                && isValidBST1(root.right, root, max);
    }

    // 297. 二叉树的序列化与反序列化
    String SEP = ",";
    String NULL = "#";

    String serialize(TreeNode root){
        StringBuilder sb = new StringBuilder();
        serialize(root,sb);
        return sb.toString();
    }

    void serialize(TreeNode root,StringBuilder sb){
        if(root == null){
            sb.append(NULL).append(SEP);
            return;
        }

        sb.append(root.val).append(SEP);
        serialize(root.left,sb);
        serialize(root.right,sb);


    }

    TreeNode deserialize(String data){
        LinkedList<String> nodes = new LinkedList<>();
        for(String s : data.split(SEP)){
            nodes.addLast(s);
        }
        return deserialize(nodes);
    }

    TreeNode deserialize(LinkedList<String> nodes){
        if(nodes.isEmpty()) return null;
        String first = nodes.removeFirst();
        if(first.equals(NULL)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(first));

        root.left = deserialize(nodes);
        root.right = deserialize(nodes);

        return root;
    }






}
