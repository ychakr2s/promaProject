package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class ControlViewer implements Initializable {
    /*
     ** This is located in First Page and when you click this Button goes to Composant.
     */
    @FXML
    private Button btn1;
    @FXML
    private TextArea date;
    @FXML
    private TextArea conShift;
    @FXML
    private TextArea gravure;
    @FXML
    private TextArea status;
    @FXML
    private TextArea chassis;


    @FXML
    private ComboBox<String> controleurControl;
    ObservableList<String> control = FXCollections.observableArrayList("Mounia", "Hanane", "Fatimazehra", "Wissal",
            "Imane", "Ouassima", "Zineb", "Najlae", "Ali", "Brahim", "Mouhamed");

    private String selectedControleur = "";

    public void fillCombo() {
        controleurControl.setItems(control);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;

        if (event.getSource() == btn1) {
            if (gravure.getText().equals("")) {
                System.out.println("alles ist leeer" + gravure.getText());
            }

            stage = (Stage) btn1.getScene().getWindow();
            stage.setTitle("Composant");
            URL url = new File("src/main/resources/fxml/qcl.fxml").toURI().toURL();
            root = FXMLLoader.load(url);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date.setText(getStartDate());
        conShift.setText(String.valueOf(getShift()));
        fillCombo();
        getSelectedControleur();
    }

    private int getShift() {
        int shift = 0;
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter forma = DateTimeFormatter.ofPattern("HH");
        String formattedDate = currentTime.format(forma);
        int time = Integer.parseInt(formattedDate);
        if (time >= 6 && time < 14) {
            shift = 1;
        } else if (time >= 14 && time < 22) {
            shift = 2;
        } else {
            shift = 3;
        }
        return shift;
    }

    private static String getStartDate() {
        Date date = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        String formatedDate = formater.format(date);
        return formatedDate;
    }

    /*
     * This Method gets the selected Controleur
     */
    public String getSelectedControleur() {
        controleurControl.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> observable,
                                        String oldValue, String newValue) {
                        selectedControleur = newValue;
                    }
                });
        return selectedControleur;
    }
}
