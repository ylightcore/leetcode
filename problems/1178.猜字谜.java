import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=1178 lang=java
 *
 * [1178] 猜字谜
 *
 * https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle/description/
 *
 * algorithms
 * Hard (29.90%)
 * Likes:    153
 * Dislikes: 0
 * Total Accepted:    10.9K
 * Total Submissions: 26.3K
 * Testcase Example:  '["aaaa","asas","able","ability","actt","actor","access"]\n' +
  '["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]'
 *
 * 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
 * 
 * 字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：
 * 
 * 
 * 单词 word 中包含谜面 puzzle 的第一个字母。
 * 单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
 * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而
 * "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）都不能作为谜底。
 * 
 * 
 * 返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i]
 * 所对应的谜底的单词数目。
 * 
 * 
 * 
 * 示例：
 * 
 * 
 * 输入：
 * words = ["aaaa","asas","able","ability","actt","actor","access"], 
 * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * 输出：[1,1,3,2,4,0]
 * 解释：
 * 1 个单词可以作为 "aboveyz" 的谜底 : "aaaa" 
 * 1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
 * 3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
 * 2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
 * 4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
 * 没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 4 
 * 1 
 * puzzles[i].length == 7
 * words[i][j], puzzles[i][j] 都是小写英文字母。
 * 每个 puzzles[i] 所包含的字符都不重复。
 * 
 * 
 */

/* 解题思路
 * 1. puzzles 中的第一个字母必须在 words 中
 * 2. puzzles 中的字母为 words 所能出现的所有字母
 * 
 * 每个 puzzles 中的字母存进 map 中，遍历 words【这里也可以使用int[26]代替map，或用二进制位代替】
 * 看 puzzles 中的第一个字母是否在 words 中
 * 以及 words 中是否都是 puzzles 中的字母
 * 
 * 这道题只会出现小写字母，种类最多是26种，而且单词的字符只要在puzzle里出现了就行。即对每个字符来说，就2种状态：出现与否，可以用 0/1 来表示这种相对的状态。
 * 出现过的字符记为1，否则为0，比如 abc:111，aacc:101，zab:1000…0011（26位）
 * 遍历单词数组，求出单词对应的二进制数，存入map，统计对应的次数，因为有些单词对应同一个二进制数，比如 abc 和 aaabbc 都是 111。
 * 单词要成为 puzzle 的谜底，必须包含 puzzle 的首字符，我们找出所有包含 puzzle 首字母的 puzzle 字母组合。比如 aboveyz 有：a，ab，ao，av，ae，ay，az，abo，abv，abe……这些组合都对应有二进制数。
 * 而每个单词也对应一个二进制数，如果在其中，则这个单词就是 puzzle 的谜底。
 * 所以，对于 puzzle 的这些二进制数，即它的组合，我们去查看 map 中是否有对应的值 c，如果有，说明有 c 个单词是这样的字母组合，是这个 puzzle 的谜底。
 * 把当前 puzzle 所有的组合在 map 中对应的值累加起来，就是当前 puzzle 的谜底单词个数。
 */

// @lc code=start
class Solution {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Integer> ans = new ArrayList<Integer>();

        for (String word : words) {
            int mask = 0;
            for (Character c : word.toCharArray()) {
                mask |= (1 << (c - 'a'));
            }
            if (Integer.bitCount(mask) <= 7) {
                map.put(mask, map.getOrDefault(mask, 0) + 1);
            }
        }
        for (String puzzle : puzzles) {
            ans.add(dfs(puzzle.toCharArray(), 1, map, 1 << (puzzle.charAt(0) - 'a')));
        }
        return ans;
    }

    public int dfs(char[] puzzle, int idx, Map<Integer, Integer> map, int key) {
        if (idx == puzzle.length)
            return map.getOrDefault(key, 0);
        return dfs(puzzle, idx + 1, map, key) + dfs(puzzle, idx + 1, map, key | 1 << puzzle[idx] - 'a');
    }
}
// @lc code=end
