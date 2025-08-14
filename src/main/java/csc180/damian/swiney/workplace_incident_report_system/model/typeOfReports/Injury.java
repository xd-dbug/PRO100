package csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports;

import csc180.damian.swiney.workplace_incident_report_system.model.Report;
import csc180.damian.swiney.workplace_incident_report_system.model.TypeOfReport;

public class Injury extends Report {
    private String injuryType;
    private boolean isHospitalized;

    public Injury(String fileName, String associateName, String actionTaken, String description, String injuryType, boolean isHospitalized, String status) {
        super(fileName, associateName, actionTaken, description, status);
        setInjuryType(injuryType);
        setHospitalized(isHospitalized);
        setTypeOfReport(TypeOfReport.INJURY);
    }

    public String getInjuryType() {
        return injuryType;
    }
    public void setInjuryType(String injuryType) {
        this.injuryType = injuryType;
    }
    public boolean getIsHospitalized() {
        return isHospitalized;
    }
    public void setHospitalized(boolean hospitalized) {
        isHospitalized = hospitalized;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Report Type: ").append(getTypeOfReport()).append("\n");
        sb.append("Injury Type: ").append(getInjuryType()).append("\n");
        sb.append("Hospitalized: ").append(getIsHospitalized()).append("\n");
        sb.append(super.toString());
        return sb.toString();
    }

}
