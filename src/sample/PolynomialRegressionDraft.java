package sample;

import java.util.Arrays;

public class PolynomialRegressionDraft {

    public static void main(String[] args) {
        // write your code here

        /*double[] x_arr = {80, 40, -40, -120, -200, -280};
        double[] y_arr = {6.47, 6.24, 5.72, 5.09, 4.30, 3.33};*/
        double[] x_arr = {5, 15, 25, 35, 45, 55};
        double[] y_arr = {5, 20, 14, 32, 22, 38};

        double[] ex = constant_finder(y_arr,x_arr,1);
        System.out.println(Arrays.toString(ex));
        System.out.println(Arrays.toString(y_ans_arr_filler(y_arr,x_arr,1)));
        //printArr(gen_arr_filler(x_arr,2));
    }

    static double[] constant_finder(double[] y_arr, double[] x_arr, int pow){
        double[] constants = new double[pow + 1];
        double[][] gen_arr = gen_arr_filler(x_arr,pow);
        double[] y_ans_arr = y_ans_arr_filler(y_arr,x_arr,pow);
        double gen_det = determinant(gen_arr);

        for(int i = 0;i < constants.length; i++){
            constants[i] = determinant(col_replacer(gen_arr, y_ans_arr, i)) / gen_det;
        }

        return constants;
    }

    static double[][] gen_arr_filler(double[] arr_x, int power){

        double[][] my_arr = new double[power + 1][power + 1];

        for(int i = 0; i < power + 1; i++){
            for(int j = 0; j < power + 1; j++){
                my_arr[i][j] = arr_sum(arr_x,j + i);
            }
        }

        return my_arr;
    }//done

    static double arr_sum(double[] arr, double pow){
        double sum = 0;
        for(double i : arr) sum += Math.pow(i,pow);
        return sum;
    }//done

    static void printArr(double[][] arr){
        for (int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }


    static double[][] col_replacer(double[][] big_arr, double[] small_arr, int col){
        double[][] tmp_arr = array_copy(big_arr);
        for(int i = 0; i < small_arr.length; i++){
            tmp_arr[i][col] = small_arr[i];
        }
        return tmp_arr;
    }//done

    static double[][] array_copy(double[][] arr){
        double[][] tmp = new double[arr.length][arr[0].length];

        for(int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                tmp[i][j] = arr[i][j];
            }
        }

        return tmp;
    }

    static double y_sum(double[] y_arr, double[] x_arr, double pow){
        double sum = 0;
        if(pow == 0){
            sum = arr_sum(y_arr,1);
        }
        else{
            for(int i = 0; i < y_arr.length; i++){
                sum += y_arr[i] * Math.pow(x_arr[i],pow);
            }
        }
        return sum;
    }//done

    static double[] y_ans_arr_filler(double[] y_arr, double[] x_arr, int pow){
        double[] y_arr_ans = new double[pow + 1];
        for(int i = 0; i < pow + 1; i++){
            y_arr_ans[i] = y_sum(y_arr,x_arr,i);
        }
        return y_arr_ans;
    }

    static double determinant(double[][] arr){
        double[] det_arr = new double[arr[0].length];
        double det;
        double sign = 1;
        if(arr.length == 2){
            det = arr[0][0] * arr[1][1] - arr[1][0] * arr[0][1];
        }
        else {
            for(int i = 0; i < arr[0].length; i++){
                det_arr[i] = arr[0][i] * determinant(small_arr_filler(arr,i)) * sign;
                sign *= -1;
            }
            det = arr_sum(det_arr,1);
        }
        return det;
    }




    static double[][] small_arr_filler(double[][] arr, int index){
        double[][] small_arr = new double[arr.length-1][arr[0].length-1];

        int i_tmp = 0;

        if(arr.length - 1 == index){
            for(int i = 1; i < arr.length;i++){
                int j_tmp = 0;
                for(int j = 0; j < arr[0].length - 1; j++){
                    if(j == index) j++;
                    small_arr[i_tmp][j_tmp] = arr[i][j];
                    j_tmp++;
                }
                i_tmp++;
            }
        }
        else{
            for(int i = 1; i < arr.length;i++){
                int j_tmp = 0;
                for(int j = 0; j < arr[0].length; j++){
                    if(j == index) j++;
                    small_arr[i_tmp][j_tmp] = arr[i][j];
                    j_tmp++;
                }
                i_tmp++;
            }
        }
        return small_arr;
    }

}
