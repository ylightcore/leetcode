/*
 * @lc app=leetcode.cn id=395 lang=java
 *
 * [395] 至少有K个重复字符的最长子串
 *
 * https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/description/
 *
 * algorithms
 * Medium (45.51%)
 * Likes:    355
 * Dislikes: 0
 * Total Accepted:    27.2K
 * Total Submissions: 55.6K
 * Testcase Example:  '"aaabb"\n3'
 *
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * s 仅由小写英文字母组成
 * 1 
 * 
 * 
 */

/* 解题思路
 * 使用int[26]记录当前字符串所出现的字符次数
 * 前提：如果某字母c出现次数小于k，那么任何包含c的子字符串都不符合要求
 * 因此，使用 s.split(c) 获得子串，递归此子串
 * 若所有字母出现次数均大于k，则返回自身长度
 * 
 * 对于字符串 sss，如果存在某个字符 ch\textit{ch}ch，它的出现次数大于 000 且小于 kkk，则任何包含 ch\textit{ch}ch 的子串都不可能满足要求。也就是说，我们将字符串按照 ch\textit{ch}ch 切分成若干段，则满足要求的最长子串一定出现在某个被切分的段内，而不能跨越一个或多个段。因此，可以考虑分治的方式求解本题。
 */

// @lc code=start
class Solution {
    public int longestSubstring(String s, int k) {
        if (s.length() < k)
            return 0;
        int[] map = new int[26];
        for (Character c : s.toCharArray()) {
            map[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (map[i] == 0)
                continue;
            // 如果某个字母长度<k，那么以他分割成子串，求这些子串的最长长度
            if (map[i] < k) {
                int res = 0;
                for (String str : s.split(String.valueOf((char) (i + 'a')))) {
                    res = Math.max(res, longestSubstring(str, k));
                }
                return res;
            }
        }
        // 如果所有字母都大于k，返回自身长度
        return s.length();
    }
}
// @lc code=end
