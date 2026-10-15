class Solution {
    public int findNumbers(int[] nums) {
        int c=0;
        for(int i=0;i<nums.length;i++) {
            if(even(nums[i])) {
                c++;
            }
        }
        return c;
    }
    static boolean even(int num) {
        if(digits(num)%2==0) {
            return true;
        }
        return false;
    }
    static int digits(int n) {
        if(n<0) n*=-1;
        int count=0;
        while(n>0) {
            count++;
            n /= 10;
        }
        return count;
    }
}