package csc180.damian.swiney.workplace_incident_report_system;

import csc180.damian.swiney.workplace_incident_report_system.model.DataBaseManager;
import csc180.damian.swiney.workplace_incident_report_system.model.TypeOfReport;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddReportController
{

    @FXML private TextField inputField;
    @FXML public DatePicker incidentDatePicker;
    @FXML public ChoiceBox<String> DropDown;

    private String value;

    @FXML
    private void initialize()
    {
        DropDown.setItems(FXCollections.observableArrayList(
                TypeOfReport.NEAR_MISS.name(),
                TypeOfReport.PROPERTY_DAMAGE.name(),
                TypeOfReport.PRODUCT_DAMAGE.name(),
                TypeOfReport.INJURY.name(),
                TypeOfReport.OTHER.name()
        ));
        DropDown.getSelectionModel().select(TypeOfReport.NEAR_MISS.name());
    }

    @FXML
    private void onOk()
    {
        value = inputField.getText();
        String selected = DropDown.getSelectionModel().getSelectedItem();

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
        Stage stage = (Stage) inputField.getScene().getWindow();
        stage.close();
    }
}
