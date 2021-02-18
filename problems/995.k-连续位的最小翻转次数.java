/*
 * @lc app=leetcode.cn id=995 lang=java
 *
 * [995] K 连续位的最小翻转次数
 *
 * https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips/description/
 *
 * algorithms
 * Hard (42.90%)
 * Likes:    120
 * Dislikes: 0
 * Total Accepted:    7.6K
 * Total Submissions: 15.4K
 * Testcase Example:  '[0,1,0]\n1'
 *
 * 在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为
 * 0。
 * 
 * 返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：A = [0,1,0], K = 1
 * 输出：2
 * 解释：先翻转 A[0]，然后翻转 A[2]。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：A = [1,1,0], K = 2
 * 输出：-1
 * 解释：无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：A = [0,0,0,1,0,1,1,0], K = 3
 * 输出：3
 * 解释：
 * 翻转 A[0],A[1],A[2]: A变成 [1,1,1,1,0,1,1,0]
 * 翻转 A[4],A[5],A[6]: A变成 [1,1,1,1,1,0,0,0]
 * 翻转 A[5],A[6],A[7]: A变成 [1,1,1,1,1,1,1,1]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 1 
 * 
 * 
 */

/* 解题思路
 * 滑动窗口
 * https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips/solution/hua-dong-chuang-kou-shi-ben-ti-zui-rong-z403l/
 * 基本思路：要想所有位数都为一，不管K的大小，K[0]为0时翻转到最后定能成功。若A.length-K[0]<K时仍有0，则无法翻转。
 * 这种方法容易超时，因此不需要真的翻转，而是使用标志位代表翻转的位数。
 * 1. 把（翻转后）K[0]为0的i放入队列中
 * 2. 如果队列中的元素超出辐射K[0]的范围，即无法影响K[0]，就踢出。
 * 3. 当前K[0]受前面的翻转影响为队列的大小（翻转次数），若K[0]==0，则需要que.size() % 2 == 奇数，若K[0]==1，则需要que.size() % 2 == 偶数，否则需要加入队列
 * 
 * 差分队列与滑动窗口
 * https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips/solution/k-lian-xu-wei-de-zui-xiao-fan-zhuan-ci-s-bikk/
 * 
 * 差分数组
 * https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips/solution/po-su-tan-xin-jie-fa-yu-tan-xin-chai-fen-4lyy/
 * 使用一个[N + 1]数组存储翻转次数，如果当前值+已翻转次数==偶数，说明还需要翻转一次【1+奇数=偶数，0+偶数=偶数】
 * 需要对某一段 [l,r] 进行 +1 的时候，只需要 arr[l]++ 和 arr[r + 1]-- 即可。
 */

// @lc code=start
class Solution {
    public int minKBitFlips(int[] A, int K) {
        int ans = 0;
        int N = A.length;
        // 翻转次数的差分数组
        int[] diff = new int[N + 1];
        for (int i = 0, cnt = 0; i < N; i++) {
            // 差分数组中，当前真实值 = 数组值+以前累计差分值
            cnt += diff[i];
            if ((A[i] + cnt) % 2 == 0) {
                if (i + K > N) {
                    return -1;
                }
                diff[i + 1]++;
                diff[i + K]--;
                ans++;
            }
        }
        return ans;
    }
}
// @lc code=end
