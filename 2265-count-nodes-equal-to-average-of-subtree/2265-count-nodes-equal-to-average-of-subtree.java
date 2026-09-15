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
    private int matchingNodesCount = 0;

    public int averageOfSubtree(TreeNode root) {
        matchingNodesCount = 0;
        calculateSumAndCount(root);
        return matchingNodesCount;
    }

    private int calculateSumAndCount(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftData = calculateSumAndCount(node.left);
        int rightData = calculateSumAndCount(node.right);
        int leftSum = leftData >> 12;
        int leftCount = leftData & 0xFFF;
        int rightSum = rightData >> 12;
        int rightCount = rightData & 0xFFF;
        int currentSum = node.val + leftSum + rightSum;
        int currentCount = 1 + leftCount + rightCount;
        if (node.val == (currentSum / currentCount)) {
            matchingNodesCount++;
        }
        return (currentSum << 12) | currentCount;
    }
}
