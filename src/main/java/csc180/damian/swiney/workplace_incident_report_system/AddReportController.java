package csc180.damian.swiney.workplace_incident_report_system;

import csc180.damian.swiney.workplace_incident_report_system.model.DataBaseManager;
import csc180.damian.swiney.workplace_incident_report_system.model.TypeOfReport;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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


    @FXML private TextField descriptionInputField;
    @FXML public TextField actionTakenInputField;
    @FXML public TextField statusInputField;




    private String value;

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
        value = descriptionInputField.getText();
        String selected = incidentTypeDropDown.getSelectionModel().getSelectedItem();

        if (value == null || value.isBlank())
        {
            close();
            return;
        }

        try
        {
            if (selected != null)
            {
                TypeOfReport type = TypeOfReport.valueOf(selected);
                switch (type)
                {
                    case NEAR_MISS:
                        DataBaseManager.addNearMiss(value);
                        break;
                    case PRODUCT_DAMAGE:
                        DataBaseManager.addProductDamage(value, 0);
                        break;
                    case PROPERTY_DAMAGE:
                        DataBaseManager.addPropertyDamage(value, 0);
                        break;
                    case INJURY:
                        DataBaseManager.addInjury(value, false, "Unspecified");
                        break;
                    case OTHER:
                    default:
                        DataBaseManager.addToMainTable(value);
                        break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Unable to add report: " + e.getMessage());
        }

        close();
    }

    @FXML
    private void onCancel()
    {
        value = null;
        close();
    }

    public String getValue() { return value; }

    private void close()
    {
        Stage stage = (Stage) descriptionInputField.getScene().getWindow();
        stage.close();
    }
}
