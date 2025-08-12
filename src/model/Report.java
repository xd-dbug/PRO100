package model;



public abstract class Report {
    private String fileName;
    private String associateName;
    private TypeOfReport typeOfReport;
    private String description;
    private static int id;
    private String actionTaken;
    private String status;


    public Report(String fileName, String associateName, String description, String actionTaken, String status) {
        setFileName(fileName);
        setAssociateName(associateName);
        setDescription(description);
        setActionTaken(actionTaken);
        setStatus(status);
        id++;
    }

    public void updateStatus(String status){
        System.out.println("Updating report status to " + status);
        setStatus(status);
    }

    //region
    public String getFileName() {
        return fileName;
    }
    protected void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getAssociateName() {
        return associateName;
    }
    protected void setAssociateName(String associateName) {
        this.associateName = associateName;
    }
    public TypeOfReport getTypeOfReport() {
        return typeOfReport;
    }
    protected void setTypeOfReport(TypeOfReport typeOfReport) {
        this.typeOfReport = typeOfReport;
    }
    public String getDescription() {
        return description;
    }
    protected void setDescription(String description) {
        this.description = description;
    }
    public int getId() {
        return id;
    }
    protected void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }
    public String getActionTaken() {
        return actionTaken;
    }
    private void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    //endregion
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(getId()).append("\n");
        sb.append("File: ").append(getFileName()).append("\n");
        sb.append("Associate Name: ").append(getAssociateName()).append("\n");
        sb.append("Description: ").append(getDescription()).append("\n");
        sb.append("Action Taken: ").append(getActionTaken()).append("\n");
        sb.append("Status: ").append(getStatus()).append("\n");
        return sb.toString();
    }

}
