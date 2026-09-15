class Solution {
    public long countCommas(long n) {
        long ans = 0;
        long[] thresholds = {
            1000L,                
            1000000L,             
            1000000000L,          
            1000000000000L,       
            1000000000000000L     
        };

        for (long t : thresholds) {
            if (n >= t) {
                ans += (n - t + 1);
            } else {
                break;
            }
        }

        return ans;
    }
}