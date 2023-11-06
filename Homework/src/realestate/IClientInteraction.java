package realestate;

public interface IClientInteraction {
    void scheduleVisit(Property property);
    void requestInformation();
    void negotiateTerms(Property property);
    void submitOffer(Property property, double offerAmount);
    void reviewContract(Bill bill);
    void provideFeedback(Property property);
    void finalizeTransaction(Transaction transaction);
    void handleComplaints(String complaint);
    void referClient(Client client, Agent agent);

}
