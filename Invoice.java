class Invoice {
    private String invoiceId;
    private String projectId;
    private double amount;
    private String status; // Paid, Unpaid

    public Invoice(String invoiceId, String projectId, double amount, String status) {
        this.invoiceId = invoiceId;
        this.projectId = projectId;
        this.amount = amount;
        this.status = status;
    }

    public String getInvoiceId() { return invoiceId; }
    public String getProjectId() { return projectId; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
