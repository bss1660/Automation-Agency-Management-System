class Project {
    private String projectId;
    private String clientId;
    private String serviceType;
    private String assignedEmployeeId;
    private String status; // Pending, In Progress, Completed

    public Project(String projectId, String clientId, String serviceType, String assignedEmployeeId, String status) {
        this.projectId = projectId;
        this.clientId = clientId;
        this.serviceType = serviceType;
        this.assignedEmployeeId = assignedEmployeeId;
        this.status = status;
    }

    public String getProjectId() { return projectId; }
    public String getClientId() { return clientId; }
    public String getServiceType() { return serviceType; }
    public String getAssignedEmployeeId() { return assignedEmployeeId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}