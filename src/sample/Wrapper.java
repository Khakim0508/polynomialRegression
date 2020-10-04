package sample;

import javafx.stage.PopupWindow;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;

public class Wrapper {
    private ArrayList<Double> x_arr = new ArrayList<>();
    private ArrayList<Double> y_arr = new ArrayList<>();

    public ArrayList<Double> getX_arr(){ return x_arr; }
    public ArrayList<Double> getY_arr(){ return y_arr; }


    public void Input(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < 2; i++){
            ArrayList<Double> tmpArr = (i == 0) ? x_arr : y_arr;
            for (int j = 0; j < n; j++){
                tmpArr.add(Double.parseDouble(reader.readLine()));
            }
        }
    }

    public Double arr_sum(ArrayList<Double> arr, Function<Double, Double> func){
        Double res = 0.0;
        for (Double i : arr){ res += func.apply(i); }
        return res;
    }

    public Double mixed_arr_sum(){
        Double res = 0.0;
        for (int i = 0; i < x_arr.size(); i++){ res += x_arr.get(i) * y_arr.get(i); }
        return res;
    }

    // Polynomial Regression Methods

    public Double[][] gen_arr_filler(int power){
        Double[][] my_arr = new Double[power + 1][power + 1];
        for (int i = 0; i < power + 1; i++){
            for (int j = 0; j < power + 1; j++){
                my_arr[i][j] = arr_sum(x_arr, i + j);
            }
        }
        return my_arr;
    }

    private static Double arr_sum(ArrayList<Double> arr, double pow){
        Double sum = 0.0;
        for(Double i : arr) sum += Math.pow(i, pow);
        return sum;
    }

    Double y_sum(ArrayList<Double> x_arr, ArrayList<Double> y_arr,double pow){
        Double sum = 0.0;
        for(int i = 0; i < y_arr.size(); i++){
            sum += y_arr.get(i) * Math.pow(x_arr.get(i), pow);
        }
        return sum;
    }

    Double[] y_ans_arr_filler(ArrayList<Double> y_arr, ArrayList<Double> x_arr, int pow){
        Double[] y_arr_ans = new Double[pow + 1];
        for(int i = 0; i < pow + 1; i++){
            y_arr_ans[i] = y_sum(x_arr,y_arr,i);
        }
        return y_arr_ans;
    }

    public Double getMinY(){
        return Collections.min(y_arr);
    }

    public Double getMinX(){
        return Collections.min(x_arr);
    }

    public Double getMaxY(){
        return Collections.max(y_arr);
    }

    public Double getMaxX(){
        return Collections.max(x_arr);
    }


}
