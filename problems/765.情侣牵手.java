/*
 * @lc app=leetcode.cn id=765 lang=java
 *
 * [765] 情侣牵手
 *
 * https://leetcode-cn.com/problems/couples-holding-hands/description/
 *
 * algorithms
 * Hard (59.56%)
 * Likes:    231
 * Dislikes: 0
 * Total Accepted:    23.3K
 * Total Submissions: 34.9K
 * Testcase Example:  '[0,2,1,3]'
 *
 * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。
 * 一次交换可选择任意两人，让他们站起来交换座位。
 * 
 * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2,
 * 2N-1)。
 * 
 * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
 * 
 * 示例 1:
 * 
 * 
 * 输入: row = [0, 2, 1, 3]
 * 输出: 1
 * 解释: 我们只需要交换row[1]和row[2]的位置即可。
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: row = [3, 2, 0, 1]
 * 输出: 0
 * 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
 * 
 * 
 * 说明:
 * 
 * 
 * len(row) 是偶数且数值在 [4, 60]范围内。
 * 可以保证row 是序列 0...len(row)-1 的一个全排列。
 * 
 * 
 */

/* 解题思路
 * 因为row中的值连续，因此使用数组index记录row的值对应的下标位置，即row和index的下标与值反转
 * 然后以i+=2遍历row，如果其下一个不是其情侣，则交换其情侣以及下一个数的位置（同时更新index的位置）
 */

// @lc code=start
class Solution {
    public int minSwapsCouples(int[] row) {
        int N = row.length;
        int[] index = new int[N];
        for (int i = 0; i < N; i++) {
            // index记录其下标的值在row中的下标位置
            index[row[i]] = i;
        }
        int count = 0;
        for (int i = 0; i < N; i += 2) {
            int a = row[i];
            int b = a ^ 1;// a的情侣
            if (row[i + 1] != b) {
                swap(row, index, index[b], i + 1);
                count++;
            }
        }
        return count;
    }

    public void swap(int[] row, int[] index, int p1, int p2) {
        int tmp = row[p1];
        row[p1] = row[p2];
        row[p2] = tmp;
        index[row[p1]] = p1;
        index[row[p2]] = p2;
    }
}
// @lc code=end
