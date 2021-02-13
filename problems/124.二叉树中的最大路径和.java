/*
 * @lc app=leetcode.cn id=124 lang=java
 *
 * [124] 二叉树中的最大路径和
 *
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/description/
 *
 * algorithms
 * Hard (43.25%)
 * Likes:    883
 * Dislikes: 0
 * Total Accepted:    96.7K
 * Total Submissions: 223.4K
 * Testcase Example:  '[1,2,3]'
 *
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个
 * 节点，且不一定经过根节点。
 * 
 * 路径和 是路径中各节点值的总和。
 * 
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * 
 * 示例 2：
 * 
 * 
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树中节点数目范围是 [1, 3 * 10^4]
 * -1000 
 * 
 * 
 */

/* 解题思路
 * 定义一个递归，此递归的作用是返回其路径的最大值，如果返回的最大值是负值，那就不要。max(0,traverse())
 * 计算left->root->right作为路径的和与最大值比较
 * 最后返回最大单边
 * 路径停在当前子树的根节点，在这个子树中收益：root.val
 * 走入左子树，在这个子树中的最大收益：root.val + dfs(root.left)
 * 走入右子树，在这个子树中的最大收益：root.val + dfs(root.right)
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        traverse(root);
        return max;
    }
    public int traverse(TreeNode root){
        if(root==null){
            return 0;
        }
        int i = Math.max(0,traverse(root.left));
        int j = Math.max(0,traverse(root.right));
        max = Math.max(max,i+j+root.val);
        return Math.max(i,j)+root.val;
    }
}
// @lc code=end

