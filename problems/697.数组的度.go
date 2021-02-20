/*
 * @lc app=leetcode.cn id=697 lang=golang
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
 * 与java相似
 * 但可以将比较取最大值放到同一个循环里面
 * 注意go中map是值传递，因此使用后需要覆盖回去
 */

// @lc code=start
type entry struct {
	count, left, right int
}

func findShortestSubArray(nums []int) (ans int) {
	hashmap := map[int]entry{}
	for i, v := range nums {
		if ele, has := hashmap[v]; has {
			ele.count++
			ele.right = i
			hashmap[v] = ele
		} else {
			hashmap[v] = entry{1, i, i}
		}
	}
	max := 0
	for _, ele := range hashmap {
		if ele.count > max {
			max, ans = ele.count, ele.right-ele.left+1
		} else if ele.count == max {
			ans = min(ans, ele.right-ele.left+1)
		}
	}
	return
}
func min(a, b int) (c int) {
	if a > b {
		c = b
	} else {
		c = a
	}
	return
}

// @lc code=end

