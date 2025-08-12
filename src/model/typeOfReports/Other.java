package model.typeOfReports;

import model.Report;
import model.TypeOfReport;

public class Other extends Report {
    public Other(String fileName, String associateName, String actionTaken, String description, String status) {
        super(fileName, associateName, description, actionTaken, status);
        setTypeOfReport(TypeOfReport.OTHER);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Report Type: ").append(getTypeOfReport()).append("\n");
        sb.append(super.toString());
        return sb.toString();
    }


}
