/*
 * @lc app=leetcode.cn id=1004 lang=java
 *
 * [1004] 最大连续1的个数 III
 *
 * https://leetcode-cn.com/problems/max-consecutive-ones-iii/description/
 *
 * algorithms
 * Medium (55.02%)
 * Likes:    223
 * Dislikes: 0
 * Total Accepted:    41K
 * Total Submissions: 67.8K
 * Testcase Example:  '[1,1,1,0,0,0,1,1,1,1,0]\n2'
 *
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 * 
 * 返回仅包含 1 的最长（连续）子数组的长度。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释： 
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * 
 * 示例 2：
 * 
 * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] 为 0 或 1 
 * 
 * 
 */

/* 解题思路
 * 1. 滑动窗口，保证窗口内的0的数量小于K个
 * 2. 滑动窗口，但不保证窗口内0的数量，只保证right-left是最大值【右指针移动时左指针跟着移动】
 */

// @lc code=start
class Solution {
    public int longestOnes(int[] A, int K) {
        int left = 0, right = 0;
        int count = 0;
        for (; right < A.length; right++) {
            count += A[right] ^ 1;
            if (count > K) {
                count -= A[left++] ^ 1;
            }
        }
        return right - left;
    }
}
// @lc code=end
