package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.Function;

public class LinearRegression implements Regression {

    public static void main(String[] args){
        File file = new File("/home/hok/IdeaProjects/AbstractionStatisticsProject/src/sample/regression/RandNums.txt");
        //File file = new File("/home/hok/IdeaProjects/AbstractionStatisticsProject/src/sample/LinearRegDataSample.txt");
        LinearRegression linearRegression = new LinearRegression();
        System.out.println(Arrays.toString(linearRegression.apply(file)));
    }

    Wrapper wrapper = new Wrapper();

    public Double[] apply(File file){
        Double[] ans = new Double[2];
        Integer len = 0;
        try{
            wrapper.Input(file);
            len = Integer.parseInt(new BufferedReader(new FileReader(file)).readLine());
        }
        catch (IOException e){
            System.out.println("IOException");
            return ans;
        }
        catch (Exception e) {
            System.out.println();
            return ans;
        }

        ans[1] = (len * wrapper.mixed_arr_sum() -
                wrapper.arr_sum(wrapper.getX_arr(),aDouble -> aDouble)
                        * wrapper.arr_sum(wrapper.getY_arr(),aDouble -> aDouble))
                / ((len * wrapper.arr_sum(wrapper.getX_arr(),aDouble -> aDouble * aDouble))
                - wrapper.arr_sum(wrapper.getX_arr(),aDouble -> aDouble)
                * wrapper.arr_sum(wrapper.getX_arr(),aDouble -> aDouble));

        ans[0] = (wrapper.arr_sum(wrapper.getY_arr(), aDouble -> aDouble) - ans[1]
                * wrapper.arr_sum(wrapper.getX_arr(), aDouble -> aDouble)) / len;

        return ans;
    }
}
