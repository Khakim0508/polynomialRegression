package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class ScatterChartSample extends Application {

    @Override public void start(Stage stage) {
        stage.setTitle("Scatter Chart Sample");
        final NumberAxis xAxis = new NumberAxis(0, 1000, 1);
        final NumberAxis yAxis = new NumberAxis(-100, 500, 100);
        final ScatterChart<Number,Number> sc = new
                ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Age (years)");
        yAxis.setLabel("Returns to date");
        sc.setTitle("Investment Overview");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Equities");
        for(int i = 0; i < 30; i++){
            series1.getData().add(new XYChart.Data(i * i, i * i,5));
        }



        sc.getData().addAll(series1);
        Scene scene  = new Scene(sc, 500, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}