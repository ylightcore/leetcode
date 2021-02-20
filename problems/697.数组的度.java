/*
 * @lc app=leetcode.cn id=697 lang=java
 *
 * [697] 数组的度
 *
 * https://leetcode-cn.com/problems/degree-of-an-array/description/
 *
 * algorithms
 * Easy (55.08%)
 * Likes:    268
 * Dislikes: 0
 * Total Accepted:    42.7K
 * Total Submissions: 72.5K
 * Testcase Example:  '[1,2,2,3,1]'
 *
 * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 * 
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：[1, 2, 2, 3, 1]
 * 输出：2
 * 解释：
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：[1,2,2,3,1,4,2]
 * 输出：6
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * nums.length 在1到 50,000 区间范围内。
 * nums[i] 是一个在 0 到 49,999 范围内的整数。
 * 
 * 
 */

/* 解题思路
 * 使用哈希表储存nums中出现的次数，并记录最左边位置与最右边位置
 * 再找出最大统计次数，查看其长度是否为最小
 */

// @lc code=start
class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        // 统计nums中的值，[0]为出现次数，[1]为最左边位置，[2]为最右边位置
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i])[0]++;
                map.get(nums[i])[2] = i;
            } else {
                map.put(nums[i], new int[] { 1, i, i });
            }
        }
        int maxNum = 0, minLen = 0;
        for (int[] i : map.values()) {
            if (i[0] > maxNum) {
                maxNum = i[0];
                minLen = i[2] - i[1] + 1;
            } else if (i[0] == maxNum) {
                minLen = Math.min(minLen, i[2] - i[1] + 1);
            }
        }
        return minLen;
    }
}
// @lc code=end
