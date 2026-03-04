package com.example.teste;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 360, 320);
        stage.setTitle("Conversor de Espaço de Cores");
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("icone.png")));
        stage.setScene(scene);
        stage.show();
    }
}
