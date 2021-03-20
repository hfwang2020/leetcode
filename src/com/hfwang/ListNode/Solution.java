package com.hfwang.ListNode;

import com.hfwang.ListNode.ListNode;

public class Solution {

    public static void main(String[] args) {
        ListNode A = new ListNode(1);
        ListNode B = new ListNode(2);
        ListNode C = new ListNode(3);
        ListNode D = new ListNode(4);
        A.next = B;
        B.next = C;
        C.next = D;

        showListNode(A);
        showListNode(reverse(A));
        ListNode successor = null;


    }

    // 打印链表
    public static void showListNode(ListNode head) {
//        System.out.println("输出链表:");
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }


    // 递归翻转链表
    public static ListNode reverse(ListNode head) {
        if (head.next == null)
            return head;
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    ListNode successor = null;

    // 将链表的前 n 个节点反转（n <= 链表长度）
    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    // 迭代反转链表
    ListNode reverseD(ListNode a) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = a;
        nxt = a;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    // reverse a to b [a,b)
    ListNode reverseAtoB(ListNode a, ListNode b) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = a;
        nxt = a;
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    // K一个一组反转链表
    ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode a, b;
        a = b = head;
        for (int i = 1; i <= k; i++) {
            if (b == null) return head;
            b = b.next;
        }
        ListNode newHead = reverseAtoB(a, b);
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    // 判断字符串是否回文串
    boolean isPalindrome(String s){
        int left = 0 ;
        int right = s.length() - 1;
        while(left < right){
            if(s.indexOf(left) != s.indexOf(right))
                return false;
            left ++;
            right --;
        }
        return true;
    }


}

