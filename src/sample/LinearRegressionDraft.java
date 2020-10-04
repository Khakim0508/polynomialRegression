package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LinearRegressionDraft  {
    public static void main(String[] args){

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {

            int len = Integer.parseInt(reader.readLine());

            //double[] x_arr = new double[len];
            double[] x_arr = {5, 15, 25, 35, 45, 55};
            //double[] y_arr = new double[len];
            double[] y_arr = {5, 20, 14, 32, 22, 38};
            double[] ans_arr = new double[len];

            //x_arr = arr_filler(x_arr);
            //y_arr = arr_filler(y_arr);

            double b = (len * mixed_arr_sum(x_arr,y_arr) - arr_sum(x_arr) * arr_sum(y_arr))
                    / ((len * quadr_arr_sum(x_arr)) - arr_sum(x_arr) * arr_sum(x_arr));
            System.out.println("Угловой градиент равен: " + b);
            double a = (arr_sum(y_arr) - b * arr_sum(x_arr)) / len;
            System.out.println("Значение У при х = 0: " + a);

            double Y;

            for(int i = 0; i < ans_arr.length; i++){
                Y = a + b * x_arr[i];
                ans_arr[i] = Y;
            }

            System.out.println("Значения переменных отклика:" + Arrays.toString(y_arr));
            System.out.println("Предпологаемые значения переменных отклика:" + Arrays.toString(ans_arr));


        }

        catch (Exception e){

        }
    }
    static double[] arr_filler(double[] arr){

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(reader.readLine());
            }
        }
        catch (Exception e){

        }
        return arr;
    }

    static double arr_sum(double arr[]){

        double sum = 0;

        for(double i : arr) sum += i;

        return sum;
    }


    static double mixed_arr_sum(double arr1[], double arr2[]){

        double sum = 0;
        double[] arr = new double[arr1.length];

        for (int i = 0; i < arr.length; i++){
            arr[i] = arr1[i] * arr2[i];
        }

        for(double i : arr) sum += i;

        return sum;
    }


    static double quadr_arr_sum(double arr[]){

        double sum = 0;

        for(double i : arr) sum += i * i;

        return sum;
    }

}
