class Solution {
    public int countCommas(int n) {
        if(n>=1000) {
            return n-999;
        }
        return 0;
    }
}