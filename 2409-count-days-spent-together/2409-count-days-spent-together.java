class Solution {
    private static final int[] PRE_SUM_DAYS = {0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int arriveA = dateToDayOfYear(arriveAlice);
        int leaveA = dateToDayOfYear(leaveAlice);
        int arriveB = dateToDayOfYear(arriveBob);
        int leaveB = dateToDayOfYear(leaveBob);
        int startOverlap = Math.max(arriveA, arriveB);
        int endOverlap = Math.min(leaveA, leaveB);
        return Math.max(0, endOverlap - startOverlap + 1);
    }

    private int dateToDayOfYear(String date) {
        
        int month = (date.charAt(0) - '0') * 10 + (date.charAt(1) - '0');
        int day = (date.charAt(3) - '0') * 10 + (date.charAt(4) - '0');
        
        return PRE_SUM_DAYS[month] + day;
    }
}
