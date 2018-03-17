package dblayer;

import java.util.ArrayList;
import modlayer.*;

public interface IfDbInvoice {

    public ArrayList<Invoice> getInvoices();
    public ArrayList<Invoice> searchInvoices(String keyword);
    public Invoice selectInvoice(int invoiceNo);
    public int insertInvoice(Invoice invoice);
    public boolean updateInvoice(Invoice invoice);
    public boolean deleteInvoice(Invoice invoice);
}