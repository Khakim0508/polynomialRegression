package sample;

import java.util.Arrays;

public class RegressionTest<T> {
    T test;

    RegressionTest(T test){
        this.test = test;
    }


    public static void main(String[] args){
        /*RegressionTest<LinearRegression> sample = new RegressionTest<>(new LinearRegression());
        System.out.println(Arrays.toString(sample.regression.apply()));*/
    }
}
