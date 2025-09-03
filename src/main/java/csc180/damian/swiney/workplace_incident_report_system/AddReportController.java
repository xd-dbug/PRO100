package csc180.damian.swiney.workplace_incident_report_system;

import csc180.damian.swiney.workplace_incident_report_system.model.DataBaseManager;
import csc180.damian.swiney.workplace_incident_report_system.model.TypeOfReport;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AddReportController
{

    @FXML public TextField titleInputField;
    @FXML public TextField employeeInputField;
    @FXML public DatePicker incidentDatePicker;
    @FXML public ChoiceBox<String> incidentTypeDropDown;

    @FXML public VBox injuryFields;
    @FXML public TextField injuryTypeInputField;
    @FXML public CheckBox hospitalizedCheckbox;

    @FXML public VBox productDamageFields;
    @FXML public TextField productDamageInputField;

    @FXML public VBox propertyDamageFields;
    @FXML public TextField propertyDamageInputField;


    @FXML private TextArea descriptionInputField;
    @FXML public TextArea actionTakenInputField;
    @FXML public TextField statusInputField;



    private final DataBaseManager db = new DataBaseManager();
    private ReportsPageController reportsPageController;

    public void setController(ReportsPageController reportsPageController){
        this.reportsPageController = reportsPageController;
    }

    private String Description;
    private String Title;
    private String IncidentType;
    private String ActionTaken;
    private String Status;
    private int employeeID;
    private LocalDate Date;

    @FXML
    private void initialize() {
        incidentTypeDropDown.setItems(FXCollections.observableArrayList(
                TypeOfReport.INJURY.name(),
                TypeOfReport.NEAR_MISS.name(),
                TypeOfReport.PRODUCT_DAMAGE.name(),
                TypeOfReport.PROPERTY_DAMAGE.name(),
                TypeOfReport.OTHER.name()
        ));

        hideIncidentSpecificFields();

        incidentTypeDropDown.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            hideIncidentSpecificFields();

            switch (incidentTypeDropDown.getSelectionModel().getSelectedItem()) {
                case "INJURY":
                    injuryFields.setVisible(true);
                    injuryFields.setManaged(true);
                    break;

                case "PRODUCT_DAMAGE":
                    productDamageFields.setVisible(true);
                    productDamageFields.setManaged(true);
                    break;
                case "PROPERTY_DAMAGE":
                    propertyDamageFields.setVisible(true);
                    propertyDamageFields.setManaged(true);
                    break;

                default:
                     break;
            }

        });

    }

    public void hideIncidentSpecificFields() {
        injuryFields.setVisible(false);
        injuryFields.setManaged(false);

        productDamageFields.setVisible(false);
        productDamageFields.setManaged(false);

        propertyDamageFields.setVisible(false);
        propertyDamageFields.setManaged(false);
    }

    @FXML
    private void onOk()
    {
        Description = descriptionInputField.getText();
        Title = titleInputField.getText();
        ActionTaken = actionTakenInputField.getText();
        Status = statusInputField.getText();
        IncidentType = incidentTypeDropDown.getSelectionModel().getSelectedItem();
        employeeID = Integer.parseInt(employeeInputField.getText());
        Date = incidentDatePicker.getValue();

        if(checkValidState()) {

            String selected = incidentTypeDropDown.getSelectionModel().getSelectedItem();


            try {
                if (selected != null) {
                    TypeOfReport type = TypeOfReport.valueOf(selected);
                    switch (type) {
                        case NEAR_MISS:
                            reportsPageController.addReportToTable(DataBaseManager.addNearMiss(Description, Title, IncidentType, ActionTaken, Status, employeeID, Date));
                            break;
                        case PRODUCT_DAMAGE:
                            int productDamage = Integer.parseInt(productDamageInputField.getText());
                            reportsPageController.addReportToTable(DataBaseManager.addProductDamage(Description, Title, IncidentType, ActionTaken, Status, productDamage, employeeID, Date));
                            break;
                        case PROPERTY_DAMAGE:
                            int propertyDamage = Integer.parseInt(propertyDamageInputField.getText());
                            reportsPageController.addReportToTable(DataBaseManager.addPropertyDamage(Description, Title, IncidentType, ActionTaken, Status, propertyDamage, employeeID, Date));
                            break;
                        case INJURY:
                            Boolean inHospital;
                            if (hospitalizedCheckbox.isSelected()) {
                                inHospital = true;
                            } else {
                                inHospital = false;
                            }
                            reportsPageController.addReportToTable(DataBaseManager.addInjury(Description, Title, IncidentType, ActionTaken, Status, inHospital, IncidentType, employeeID, Date));
                            break;
                        case OTHER:
                            reportsPageController.addReportToTable(DataBaseManager.addOther(Description, Title, IncidentType, ActionTaken, Status, employeeID, Date));
                        default:
                            break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Unable to add report: " + e.getMessage());
            }

            close();
        }else{
            
        }
    }

    @FXML
    private void onCancel()
    {
        close();
    }


    private void close()
    {
        Stage stage = (Stage) descriptionInputField.getScene().getWindow();
        stage.close();
    }

    public Boolean checkValidState(){

        if(Description.isEmpty() || Title.isEmpty()  || IncidentType.isEmpty() || ActionTaken.isEmpty() || Status.isEmpty()) {
            return false;
        } else if (IncidentType.equals("INJURY") && injuryTypeInputField.getText().isEmpty() || !hospitalizedCheckbox.isSelected()) {
            return false;
        } else if (IncidentType.equals("PRODUCT_DAMAGE") && productDamageInputField.getText().isEmpty()) {
            return false;
        } else if (IncidentType.equals("PROPERTY_DAMAGE") && propertyDamageInputField.getText().isEmpty()) {
            return false;
        }

        return true;

    }

}
