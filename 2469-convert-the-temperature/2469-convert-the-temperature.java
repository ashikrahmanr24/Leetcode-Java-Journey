class Solution {
    public double[] convertTemperature(double celsius) {
        for(int i=0;i<2;i++) {
            return new double[]{celsius+273.15,celsius * 1.80 + 32.00};
        }
        return new double[]{};
    }
}