package com.nubll.fastread;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FastReadApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FastReadApplication.class.getResource("MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image icon = new Image("C:\\Users\\yasse\\Desktop\\java\\Fast-Read\\src\\main\\resources\\com\\nubll\\fastread\\icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Fast Read");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}