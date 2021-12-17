package com.bilgeadam.marathon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/mainview/MainView.fxml"));
            Scene scene = new Scene(root,600,600);
            scene.getStylesheets().add(getClass().getResource("../view/mainview/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMainView() {
        FXMLLoader loader = new FXMLLoader();
    }
}
