package sample;

import java.io.*;
import java.util.*;
import java.util.function.Function;

public class PolynomialRegression implements Regression{

    public Wrapper arrays = new Wrapper();

    public static void main(String[] args){
        File file = new File("/home/hok/IdeaProjects/AbstractionStatisticsProject/src/sample/LinearRegDataSample.txt");
        PolynomialRegression polynomialRegression = new PolynomialRegression();
        System.out.println(Arrays.toString(polynomialRegression.apply(file)));
        /*
        Double[][] det = {{1.0,2.0,7.0},
                          {5.0,3.0,4.0},
                          {9.0,10.0,2.0}};
        Double res = Determinant.determinant(det);
        System.out.println(res);*/
        //Determinant checked, answer:179



    }

    public Double[] apply(File file){
        int power = 2;
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            power = Integer.parseInt(reader.readLine());
            arrays.Input(file);
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        catch (IOException e){
            System.out.println("IOException was caught");
        }
        catch (Exception e){
            System.out.println(e.getClass());
            return new Double[2];
        }

        Double[] constants = new Double[power + 1];
        Double[][] gen_arr = arrays.gen_arr_filler(power);
        Double[] y_ans_arr = arrays.y_ans_arr_filler(arrays.getY_arr(),arrays.getX_arr(),power);
        Double gen_det = Determinant.determinant(gen_arr);

        for(int i = 0;i < constants.length; i++){
            constants[i] = Determinant.determinant(col_replacer(gen_arr, y_ans_arr, i)) / gen_det;
        }

        return constants;
    }

    static void printArr(Double[][] arr){
        for (int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }

    static Double[][] col_replacer(Double[][] big_arr, Double[] small_arr, int col){
        Double[][] tmp_arr = array_copy(big_arr);
        for(int i = 0; i < small_arr.length; i++){
            tmp_arr[i][col] = small_arr[i];
        }
        return tmp_arr;
    }

    static Double[][] array_copy(Double[][] arr){
        Double[][] tmp = new Double[arr.length][arr[0].length];

        for(int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                tmp[i][j] = arr[i][j];
            }
        }

        return tmp;
    }

    public static class Determinant{

        public static Double determinant(Double[][] arr){
            Double[] det_arr = new Double[arr[0].length];
            Double det;
            Double sign = 1.0;
            if(arr.length == 2){
                det = arr[0][0] * arr[1][1] - arr[1][0] * arr[0][1];
            }
            else {
                for(int i = 0; i < arr[0].length; i++){
                    det_arr[i] = arr[0][i] * determinant(small_arr_filler(arr,i)) * sign;
                    sign *= -1;
                }
                det = arr_sum(det_arr);
            }
            return det;
        }

        public static Double arr_sum(Double[] arr){
            Double res = 0.0;
            for (Double i : arr){ res += i; }
            return res;
        }

        public static Double[][] small_arr_filler(Double[][] arr, int index){
            Double[][] small_arr = new Double[arr.length-1][arr[0].length-1];
            int i_tmp = 0;
            int shara = arr.length - 1 == index ? 1 : 0;
            for(int i = 1; i < arr.length;i++){
                int j_tmp = 0;
                for(int j = 0; j < arr[0].length - shara; j++){
                    if(j == index) j++;
                    small_arr[i_tmp][j_tmp] = arr[i][j];
                    j_tmp++;
                }
                i_tmp++;
            }
            return small_arr;
        }

    }
}