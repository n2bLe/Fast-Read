package com.nubll.fastread;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import com.nubll.fastread.ReadController.*;
import javafx.scene.control.Label;

public class ReadingController implements Initializable {
    public static Thread thread;
    @FXML
    private Label wordCount;
    @FXML
    private Label timeLeft;
    @FXML
    private Label wordLabel;


    public void pause(ActionEvent event) throws InterruptedException {
        thread.wait();
    }

    public void startReading(String[] words,long speed) throws InterruptedException {
        thread = new Thread( ()-> {
            for(int i = 0;i< words.length;i++){
                int finalI = i;
                String len = String.valueOf(words.length);
                String current = String.valueOf(finalI);
                long remain = (words.length - i) * speed / 1000;
                long total = words.length * speed/1000;
                String temp = String.format(("on work ... %s / %s"),current,len);
                Platform.runLater(()-> {
                    for(String s : words){
                        System.out.println(s);
                    }
                    wordCount.setText(temp);
                    wordLabel.setText(words[finalI]);
                    timeLeft.setText(remain + "s / " + total + "s");
                });
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
