package Controllers;
/*
 * Course: Course Name
 * Topic:
 */
import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {
    /**
     * @param args the command line arguments
     * @author Cecilia Constantine
     */

    public RadioButton addPartIn;
    public RadioButton addPartOut;
    public TextField addPartID;
    public TextField addPartName;
    public TextField addPartInv;
    public TextField addPartPrice;
    public TextField addPartMax;
    public TextField addPartMin;
    public TextField addPartOrigination;
    public Label addPartOLabel;
    public Button addPartSave;
    public Button addPartCancel;

    private int thisPartID;
    public static Part partData = null;
        @FXML
        private AnchorPane modifyPart;

        @FXML
        void cancelModifiedPart(MouseEvent event) throws IOException{
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Cancel Check");
            confirmAlert.setContentText("Are you sure you want to leave this screen?");
            Optional<ButtonType> result = confirmAlert.showAndWait();

            if (result.get() == ButtonType.OK) {
                Parent root = FXMLLoader.load(getClass().getResource("/Views/main.fxml"));
                Scene mainScene = new Scene(root);
                Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                primaryStage.setScene(mainScene);
                primaryStage.setResizable(false);
                primaryStage.show();
            } else {
                return;
            }
        }

        @FXML
        void saveModifiedPart(MouseEvent event) throws IOException{

            if (addPartIn.isSelected()) {

                try {
                    int userStock = Integer.parseInt(addPartInv.getText());
                    int userMin = Integer.parseInt(addPartMin.getText());
                    int userMax = Integer.parseInt(addPartMax.getText());
                    String userName = addPartName.getText();
                    double userPrice = Double.parseDouble(addPartPrice.getText());
                    int userOrigin = Integer.parseInt(addPartOrigination.getText());

                    if (userMin > userMax){
                        Alert minMaxWarning = new Alert(Alert.AlertType.WARNING);
                        minMaxWarning.setTitle("Min/Max Check");
                        minMaxWarning.setHeaderText("Check your Min or Max value");
                        minMaxWarning.setContentText("Minimum must have a value less than maximum and Maximum must have a value greater than minimum");
                        minMaxWarning.showAndWait();
                    } else if (userStock > userMax || userStock < userMin){
                        Alert userInv = new Alert(Alert.AlertType.WARNING);
                        userInv.setTitle("Inventory Check");
                        userInv.setHeaderText("Your Inventory doesn't add up");
                        userInv.setContentText("Inventory must be between the minimum or maximum value for that Part or Product");
                        userInv.showAndWait();
                    } else if (userName.isEmpty()) {
                        Alert userPartsAlert = new Alert(Alert.AlertType.WARNING);
                        userPartsAlert.setTitle("Value Check");
                        userPartsAlert.setHeaderText("Please check your values");
                        userPartsAlert.setContentText("Ensure that a part must have a name");
                        userPartsAlert.showAndWait();
                    } else if (userOrigin <= 0) {
                        Alert userPartsAlert = new Alert(Alert.AlertType.WARNING);
                        userPartsAlert.setTitle("Value Check");
                        userPartsAlert.setHeaderText("Please check your values");
                        userPartsAlert.setContentText("Ensure that a part must have a Machine ID greater than 0");
                        userPartsAlert.showAndWait();
                    } else {
                        addPartID.setText("" + thisPartID);

                        Inventory.updatePart(0, new InHouse(thisPartID, userStock, userMin, userMax, userName, userPrice, userOrigin));

                        Parent root = FXMLLoader.load(getClass().getResource("/Views/main.fxml"));
                        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        primaryStage.setScene(new Scene(root, 886, 413));
                        primaryStage.setResizable(false);
                        primaryStage.show();
                    }
                } catch (NumberFormatException e) {
                    Alert userPartsAlert = new Alert(Alert.AlertType.WARNING);
                    userPartsAlert.setTitle("Value Check");
                    userPartsAlert.setHeaderText("Please check your values");
                    userPartsAlert.setContentText("Ensure that your part has all of the following: a price, inventory, min/max, and machine id values");
                    userPartsAlert.showAndWait();
                }
            }

            if (addPartOut.isSelected()) {
                try {
                    int userStock = Integer.parseInt(addPartInv.getText());
                    int userMin = Integer.parseInt(addPartMin.getText());
                    int userMax = Integer.parseInt(addPartMax.getText());
                    String userName = addPartName.getText();
                    double userPrice = Double.parseDouble(addPartPrice.getText());
                    String userOrigin = addPartOrigination.getText();

                    if (userMin > userMax){
                        Alert minMaxWarning = new Alert(Alert.AlertType.WARNING);
                        minMaxWarning.setTitle("Min/Max Check");
                        minMaxWarning.setHeaderText("Check your Min or Max value");
                        minMaxWarning.setContentText("Minimum must have a value less than maximum and Maximum must have a value greater than minimum");
                        minMaxWarning.showAndWait();
                    } else if (userStock > userMax || userStock < userMin){
                        Alert userInv = new Alert(Alert.AlertType.WARNING);
                        userInv.setTitle("Inventory Check");
                        userInv.setHeaderText("Your Inventory doesn't add up");
                        userInv.setContentText("Inventory must be between the minimum or maximum value for that Part or Product");
                        userInv.showAndWait();
                    } else if (userName.isEmpty()) {
                        Alert userPartsAlert = new Alert(Alert.AlertType.WARNING);
                        userPartsAlert.setTitle("Value Check");
                        userPartsAlert.setHeaderText("Please check your values");
                        userPartsAlert.setContentText("Ensure that a part must have a name");
                        userPartsAlert.showAndWait();
                    } else if (userOrigin.isEmpty()) {
                        Alert userPartsAlert = new Alert(Alert.AlertType.WARNING);
                        userPartsAlert.setTitle("Value Check");
                        userPartsAlert.setHeaderText("Please check your values");
                        userPartsAlert.setContentText("Ensure that a part must have a company name");
                        userPartsAlert.showAndWait();
                    }  else {
                        addPartID.setText("" + thisPartID);
                        Inventory.updatePart(0, new Outsourced(thisPartID, userStock, userMin, userMax, userName, userPrice, userOrigin));

                        Parent root = FXMLLoader.load(getClass().getResource("/Views/main.fxml"));
                        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        primaryStage.setScene(new Scene(root, 886, 413));
                        primaryStage.setResizable(false);
                        primaryStage.show();
                    }
                } catch (NumberFormatException e) {
                    Alert userPartsAlert = new Alert(Alert.AlertType.WARNING);
                    userPartsAlert.setTitle("Value Check");
                    userPartsAlert.setHeaderText("Please check your values");
                    userPartsAlert.setContentText("Ensure that your part has all of the following: a price, inventory, min/max, and machine id values");
                    userPartsAlert.showAndWait();
                }
            }
        }

        @FXML
        public void addPartSourceSelected(MouseEvent mouseEvent) {
        if (addPartIn.isSelected())
            addPartOLabel.setText("Machine ID");
        addPartOrigination.setPromptText("Machine ID");
        if (addPartOut.isSelected())
            addPartOLabel.setText("Company Name");
    }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
    //      Modify Part Screen
            thisPartID = partData.getId();
            addPartID.setText("" + thisPartID);
            addPartInv.setText("" + partData.getStock());
            addPartMin.setText("" + partData.getMin());
            addPartMax.setText("" + partData.getMax());
            addPartName.setText("" + partData.getName());
            addPartPrice.setText("" + partData.getPrice());
            if (partData instanceof InHouse){
                InHouse ih = (InHouse)partData;
                addPartOrigination.setText(Integer.toString(ih.getMachineid()));
                // set selected buttons and labels!
                addPartID.setText("" + thisPartID);
                addPartInv.setText("" + partData.getStock());
                addPartMin.setText("" + partData.getMin());
                addPartMax.setText("" + partData.getMax());
                addPartName.setText("" + partData.getName());
                addPartPrice.setText("" + partData.getPrice());
            } else {
                Outsourced os = (Outsourced) partData;
                addPartOrigination.setText(os.getCompanyName());
            }
        }


}
