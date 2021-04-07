package com.hfwang.dp;

import java.util.Vector;

public class Solution {
    // 509. 斐波那契数
    // 暴力递归
    public int fib(int n) {
        if (n == 1 || n == 2) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    // add备忘录
    public void fib1(int N) {

    }

    // dp数组
    public int fib2(int N) {
        if (N < 1) return 0;
        if (N == 1 || N == 2) return 1;
        int prev = 1, cur = 1;
        for (int i = 3; i <= N; i++) {
            int sum = prev + cur;
            prev = cur;
            cur = sum;
        }
        return cur;
    }

    // 322. 零钱兑换
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int amounttt = 1; amounttt <= amount; amounttt++) {
            dp[amounttt] = amount + 1;
            for(int coin : coins){
                int balance = amount - coin;
                if(balance>=0){
                    dp[amounttt] = Math.min(dp[amounttt],1+dp[balance]);
                }
            }
        }
        return dp[amount] == (amount+1)?-1:dp[amount];

    }


}
