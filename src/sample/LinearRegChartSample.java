package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.File;


public class LinearRegChartSample extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Linear Regression");
        final LineChart<Number,Number> sc = new LineChart<>(new NumberAxis(),new NumberAxis());
        sc.setTitle("Data Sample");
/*

        XYChart.Series series1 = new XYChart.Series();//points
        series1.setName("Equities");
        series1.getData().add(new XYChart.Data(4.2, 193.2));
        series1.getData().add(new XYChart.Data(2.8, 33.6));
        series1.getData().add(new XYChart.Data(6.8, 23.6));
*/

/*

        XYChart.Series series2 = new XYChart.Series();//lines
        series2.setName("Mutual funds");
        series2.getData().add(new XYChart.Data(5.2, 229.2));
        series2.getData().add(new XYChart.Data(2.4, 37.6));
        series2.getData().add(new XYChart.Data(6.4, 15.6));

*/


        RegressionTest<LinearRegression> regression = new RegressionTest<>(new LinearRegression());
        //File file = new File("/home/hok/IdeaProjects/AbstractionStatisticsProject/src/sample/regression/RandNums.txt");
        File file = new File("/home/hok/IdeaProjects/AbstractionStatisticsProject/src/sample/LinearRegDataSample.txt");
        Double[] ans_arr = regression.test.apply(file);
        XYChart.Series series1 = new XYChart.Series();//points
        series1.setName("Data");
        for (int i = 0; i < regression.test.wrapper.getX_arr().size(); i++){
            series1.getData().add(new XYChart.Data((double)regression.test.wrapper.getX_arr().get(i)
                    , (double)regression.test.wrapper.getY_arr().get(i)));
        }

        XYChart.Series series2 = new XYChart.Series();//lines
        series2.setName("Regression Line");

        for (int i = 0; i < regression.test.wrapper.getX_arr().size(); i++){
            series2.getData().add(new XYChart.Data(regression.test.wrapper.getX_arr().get(i)
                    , ans_arr[0] + regression.test.wrapper.getX_arr().get(i) * ans_arr[1]));
        }

        sc.setAnimated(false);
        sc.setCreateSymbols(true);

        sc.getData().addAll(series1, series2);//

        Scene scene  = new Scene(sc, 500, 400);
        scene.getStylesheets().add(getClass().getResource("root.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}