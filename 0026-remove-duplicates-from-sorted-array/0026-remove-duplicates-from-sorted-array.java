class Solution {
    public int removeDuplicates(int[] arr) {
        int n = arr.length;
        int c=0;
        for(int i=0;i<n;i++) {
            if(i==0 || arr[i]!=arr[i-1]) {
                arr[c]=arr[i];
                c++;
            }
        }
        return c;
    }
}