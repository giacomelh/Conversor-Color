package com.example.teste;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML
    private Pane colorPreview;
    @FXML
    private Slider sliderR;
    @FXML
    private Slider sliderG;
    @FXML
    private Slider sliderB;
    @FXML
    private Spinner<Integer> spinnerR;
    @FXML
    private Spinner<Integer> spinnerG;
    @FXML
    private Spinner<Integer> spinnerB;
    @FXML
    private TextField textCMYK;
    @FXML
    private TextField textHSV;
    @FXML
    private TextField textGray;
    @FXML
    private TextField textNorm;
    @FXML
    private Button button;

    @FXML
    public void initialize() {
        configurar(sliderR, spinnerR);
        configurar(sliderG, spinnerG);
        configurar(sliderB, spinnerB);


    }

    public void configurar(Slider slider, Spinner<Integer> spinner) {
        slider.setMin(0);
        slider.setMax(255);
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));

        slider.valueProperty().addListener((obs, oldVal, newVal) -> {
            spinner.getValueFactory().setValue(newVal.intValue());
        });

        spinner.valueProperty().addListener((obs, oldVal, newVal) -> {
            slider.setValue(newVal.doubleValue());
        });
        spinner.valueProperty().addListener((obs, oldVal, newVal) -> {
            spinner.getValueFactory().setValue(newVal.intValue());
            atualizarCor();
        });
    }

    private void atualizarCor() {
        int r = (int) sliderR.getValue();
        int g = (int) sliderG.getValue();
        int b = (int) sliderB.getValue();
        String hex = String.format("#%02X%02X%02X", r, g, b);
        colorPreview.setStyle("-fx-background-color: " + hex + ";");
    }
        @FXML
        public void converter () {

            int r = spinnerR.getValue();
            int g = spinnerG.getValue();
            int b = spinnerB.getValue();
            double c, m, y;
            double rN = r / 255.0, gN = g / 255.0, bN = b / 255.0;
            double k = 1 - Math.max(rN, Math.max(gN, bN));
            if (k == 1) {
                c = 0;
                m = 0;
                y = 0;
            } else {
                c = (1 - rN - k) / (1 - k);
                m = (1 - gN - k) / (1 - k);
                y = (1 - bN - k) / (1 - k);
            }
            textCMYK.setText(String.format("%.0f%%, %.0f%%, %.0f%%, %.0f%%", c * 100, m * 100, y * 100, k * 100));

            double max = Math.max(rN, Math.max(gN, bN));
            double min = Math.min(rN, Math.min(gN, bN));
            double delta = max - min;
            double v = max * 100;
            double s = (delta / max) * 100;
            double h;
            if (delta == 0) {
                h = 0;
            } else if (max == rN) {
                h = 60 * (((gN - bN) / delta) % 6);
            } else if (max == gN) {
                h = 60 * (((bN - rN) / delta) + 2);
            } else {
                h = 60 * (((rN - gN) / delta) + 4);
            }
            if (h < 0) h += 360;

            textHSV.setText(String.format("%.0f°, %.0f%%, %.0f%%", h, s, v));

            int scaleGray = (r + g + b) / 3;
            textGray.setText(String.format("%d, %d, %d", scaleGray, scaleGray, scaleGray));

            textNorm.setText(String.format("%.1f, %.1f, %.1f", r / 255.0, g / 255.0, b / 255.0));

        }
    }




