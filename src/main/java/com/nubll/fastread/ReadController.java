package com.nubll.fastread;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReadController implements Initializable {

    public static String[] allWords;

    @FXML
    private Label readTest;

    @FXML
    private Button exit;
    @FXML
    private Button start;
    @FXML
    private Button clear;
    @FXML
    private Button resume;
    @FXML
    public TextArea textArea;
    @FXML
    private TextField speedField;
    @FXML
    private Slider speedSlider;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textArea.setWrapText(true);
        speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                int speed = t1.intValue();
                speedField.setText(String.valueOf(speed));
            }
        });
    }

    public void clear(ActionEvent event){
        textArea.clear();
    }
    public void exit(ActionEvent event){
        Platform.exit();
    }

    public void start(ActionEvent event){

    }
    public void chooseFile(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text files (*.txt)","*.txt")
        );
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(null);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        String text = "";
        while((line = reader.readLine()) != null){
            text += line + "\n";
            textArea.setText(text);
        }
    }

    public void save(ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt")
        );
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setTitle("Save");
        File file = fileChooser.showSaveDialog(null);

    }

    public void New(ActionEvent event){
        textArea.clear();
    }

    public void showAbout(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("Read Fast is used to improve reading speed \n and lower the eye movement when reading");
        alert.show();
    }

    public void changeSpeedField(ActionEvent event){
        int speed = (int) speedSlider.getValue();
        speedField.setText(String.valueOf(speed));
    }
    public void changeSlider(ActionEvent event){
        String speed = speedField.getText();
        speedSlider.setValue(Double.parseDouble(speed));
    }


    public void showAlert(String text) {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText("Bad Input");
                alert.setContentText(text);
                alert.showAndWait();
            }
        });
    }


    public String[] getText() {
        String text = textArea.getText();
        return text.split(" ");
    }
    public void openReader(ActionEvent event) throws InterruptedException, IOException {
        if(speedSlider.getValue() != 0) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reading-window.fxml"));
            Parent root = fxmlLoader.load();
            long speed = 60000 / (long) speedSlider.getValue();
            ReadingController controller = fxmlLoader.getController();
            controller.startReading(getText(), speed);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.getIcons().add(new Image("C:\\Users\\yasse\\Desktop\\java\\Fast-Read\\src\\main\\resources\\com\\nubll\\fastread\\icon.png"));
            stage.setTitle("Reading");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
        }
        else{
            showAlert("Speed must be higher then 0 !");
        }



    }


}