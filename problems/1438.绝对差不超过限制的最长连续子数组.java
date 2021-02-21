import java.util.TreeMap;

/*
 * @lc app=leetcode.cn id=1438 lang=java
 *
 * [1438] 绝对差不超过限制的最长连续子数组
 *
 * https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/description/
 *
 * algorithms
 * Medium (37.49%)
 * Likes:    123
 * Dislikes: 0
 * Total Accepted:    14.8K
 * Total Submissions: 33.2K
 * Testcase Example:  '[8,2,4,7]\n4'
 *
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于
 * limit 。
 * 
 * 如果不存在满足条件的子数组，则返回 0 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [8,2,4,7], limit = 4
 * 输出：2 
 * 解释：所有子数组如下：
 * [8] 最大绝对差 |8-8| = 0 <= 4.
 * [8,2] 最大绝对差 |8-2| = 6 > 4. 
 * [8,2,4] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
 * [2] 最大绝对差 |2-2| = 0 <= 4.
 * [2,4] 最大绝对差 |2-4| = 2 <= 4.
 * [2,4,7] 最大绝对差 |2-7| = 5 > 4.
 * [4] 最大绝对差 |4-4| = 0 <= 4.
 * [4,7] 最大绝对差 |4-7| = 3 <= 4.
 * [7] 最大绝对差 |7-7| = 0 <= 4. 
 * 因此，满足题意的最长子数组的长度为 2 。
 * 
 * 
 * 示例 2：
 * 
 * 输入：nums = [10,1,2,4,7,2], limit = 5
 * 输出：4 
 * 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
 * 
 * 
 * 示例 3：
 * 
 * 输入：nums = [4,2,2,2,4,4,2,2], limit = 0
 * 输出：3
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 * 
 * 
 */

/* 解题思路
 * 使用滑动窗口，求出窗口内的最大差值是否小于limit，若不符合则将左指针向右移动
 * 最大差值可以使用TreeMap来求，TreeMap的特性是有序，可以获取最大值和最小值【记录窗口内的出现次数，来间接获得最大最小值】
 */

// @lc code=start
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int left = 0, right = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int res = 0;
        while (right < nums.length) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }
}
// @lc code=end
