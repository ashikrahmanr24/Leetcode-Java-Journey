class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        char[] chars = seq.toCharArray();
        int n = chars.length;
        int[] res = new int[n];
        
        for (int i = 0; i < n; i++) {
            res[i] = chars[i] == '(' ? (i & 1) : (1 - (i & 1));
        }
        
        return res;
    }
}