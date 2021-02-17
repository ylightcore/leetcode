/*
 * @lc app=leetcode.cn id=567 lang=java
 *
 * [567] 字符串的排列
 *
 * https://leetcode-cn.com/problems/permutation-in-string/description/
 *
 * algorithms
 * Medium (38.15%)
 * Likes:    304
 * Dislikes: 0
 * Total Accepted:    73.8K
 * Total Submissions: 177.1K
 * Testcase Example:  '"ab"\n"eidbaooo"'
 *
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 * 
 * 
 */

/* 解题思路
 * 使用滑动窗口解题，维护一个与s1相同长度的窗口，每次更新窗口就判断窗口内的字符数量与s1是否相同
 * **如果与s1的字符数量相同，则它是s1字符排列集之一
 * 这里比较重要的是如何比较窗口字符数量与s1字符数量。一开始我的想法是使用HashMap，但消耗比较大。
 * 考虑到只有26个字母，因此可以使用new int[26];来存储每一个字母的位数
 * 注意：比较两个数组使用的是Arrays.equals(cs1,cs2)
 * 还有，可以注意到滑动窗口移动时只有前后两个字符发生了改变，因此可以使用新-旧得到差值，比较差值而不用比较整个数组
 */

// @lc code=start
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) {
            return false;
        }
        int sum = 0;
        // 把s1 hash 的和记录
        for (char c : s1.toCharArray()) {
            sum += hash(c);
        }
        char[] cs = s2.toCharArray();
        // 减去s2前len1个字符的hash
        for (int i = 0; i < len1; i++) {
            sum -= hash(cs[i]);
        }
        // 如果此时sum==0；说明s2的前len1个字符就和s1个数相同
        if (sum == 0) {
            return true;
        }
        for (int i = len1; i < len2; i++) {
            // 把以前减的加回来，再把现在减去
            sum += hash(cs[i - len1]) - hash(cs[i]);
            if (sum == 0) {
                return true;
            }
        }
        return false;
    }

    // 返回每个字符的独有的hash
    public int hash(char c) {
        return 1 << (c - 'a');
    }
}
// @lc code=end
