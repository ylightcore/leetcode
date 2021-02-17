/*
 * @lc app=leetcode.cn id=561 lang=java
 *
 * [561] 数组拆分 I
 *
 * https://leetcode-cn.com/problems/array-partition-i/description/
 *
 * algorithms
 * Easy (72.91%)
 * Likes:    244
 * Dislikes: 0
 * Total Accepted:    74.1K
 * Total Submissions: 96.8K
 * Testcase Example:  '[1,4,3,2]'
 *
 * 给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn)
 * ，使得从 1 到 n 的 min(ai, bi) 总和最大。
 * 
 * 返回该 最大总和 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,4,3,2]
 * 输出：4
 * 解释：所有可能的分法（忽略元素顺序）为：
 * 1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
 * 2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
 * 3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
 * 所以最大总和为 4
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [6,2,6,5,1,2]
 * 输出：9
 * 解释：最优的分法为 (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2
 * + 6 = 9
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * nums.length == 2 * n
 * -10^4 
 * 
 * 
 */

/* 解题思路
 * 要求两数最小的和最大，就是求差值最小的数之间的小数和
 * 就是求排序好的数组的奇数和
 */

// @lc code=start
class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }
}
// @lc code=end
