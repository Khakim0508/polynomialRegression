package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class PolRegChartSample extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Polynomial Regression");
        final LineChart<Number, Number> sc = new LineChart<>(new NumberAxis(), new NumberAxis());
        sc.setTitle("Data Sample");

        List<String> names =
                Arrays.asList("Reflection","Collection","Please enter power of Regression");

        // demonstration of filter method
        List<String> result = names.parallelStream().filter(s->s.startsWith("P")).
                collect(Collectors.toList());
        System.out.println(result.toString());

        try{ RandNumbs(); }
        catch (Exception e){}

        RegressionTest<PolynomialRegression> regression = new RegressionTest<>(new PolynomialRegression());
        //File file = new File("/home/hok/IdeaProjects/AbstractionStatisticsProject/src/sample/regression/RandNums.txt");
        //File file = new File("/home/hok/IdeaProjects/AbstractionStatisticsProject/src/sample/LinearRegDataSample.txt");
        File file = new File("/home/hok/IdeaProjects/AbstractionStatisticsProject/src/sample/PolynomialRegressionData.txt");
        Double[] ans_arr = regression.test.apply(file);
        XYChart.Series series1 = new XYChart.Series();//points
        series1.setName("Data");
        for (int i = 0; i < regression.test.arrays.getX_arr().size(); i++) {
            series1.getData().add(new XYChart.Data(regression.test.arrays.getX_arr().get(i)
                    , regression.test.arrays.getY_arr().get(i)));
        }

        XYChart.Series series2 = new XYChart.Series();//lines
        series2.setName("Regression line");

        /*for (int i = 0; i < regression.test.wrapper.getX_arr().size(); i++) {
            series2.getData().add(new XYChart.Data(regression.test.wrapper.getX_arr().get(i)
                    , ans_arr[0] + regression.test.wrapper.getX_arr().get(i) * ans_arr[1]));
        }*/

        for (int i = (int)(double)regression.test.arrays.getMinX()
             - (int)(double)regression.test.arrays.getMaxX() / 10;
             i < (int)(double)regression.test.arrays.getMaxX()
                     + (int)(double)regression.test.arrays.getMaxX() / 10; i++) {
            series2.getData().add(new XYChart.Data(i
                    , helpAlg(ans_arr,i)));
        }

        sc.setAnimated(false);
        sc.setCreateSymbols(true);

        sc.getData().addAll(series1, series2);//

        Scene scene = new Scene(sc, 700, 600);
        scene.getStylesheets().add(getClass().getResource("root.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Double helpAlg(Double[] arr, int x){
        Double tmp = arr[0];
        for (int i = 1; i < arr.length; i++){
            tmp += arr[i] * Math.pow(x,i);
        }
        return tmp;
    }

    public static void RandNumbs() throws IOException {
        String str = "400";
        FileOutputStream outputStream = new FileOutputStream("/home/hok/IdeaProjects/AbstractionStatisticsProject/src/sample/regression/RandNums.txt");
        byte[] strToBytes = str.getBytes();
        outputStream.write(strToBytes);
        String lineSeparator = System.getProperty("line.separator");
        outputStream.write(lineSeparator.getBytes());

        for (int i = 0; i < 400; i++){
            str = (Math.random() * 200 - 100) + "";
            strToBytes = str.getBytes();
            outputStream.write(strToBytes);
            outputStream.write(lineSeparator.getBytes());
        }
        for (int i = 0; i < 400; i++){
            str = (Math.random() * 100 - 50) + "";
            strToBytes = str.getBytes();
            outputStream.write(strToBytes);
            outputStream.write(lineSeparator.getBytes());
        }

        outputStream.close();
    }


}