package ru.oleg.korytnikov;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;

public class CsvToTable {

    private final static String PATH_TO_FILE = System.getProperty("user.dir") + "/clients.csv";

    public static void main(String... args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the customer name:");
        Timer timer = new Timer();
        new Thread(timer).start();
        try {
            String customerName = br.readLine();
            System.out.println("Enter the company:");
            String company = br.readLine();
            System.out.println("Enter phone number:");
            String phoneNumber = br.readLine();
            br.close();
            Customer customer = new Customer(customerName, company, phoneNumber);
            File csvData = new File(PATH_TO_FILE);
            if (csvData.exists()) {
                FileWriter pw = new FileWriter(PATH_TO_FILE, true);
                CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");
                CSVPrinter csvFilePrinter = new CSVPrinter(pw, csvFileFormat);
                csvFilePrinter.printRecord(customer.getCustomerCsvData());
                pw.flush();
                pw.close();
            } else {
                FileWriter pw = new FileWriter(PATH_TO_FILE);
                CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");
                CSVPrinter csvFilePrinter = new CSVPrinter(pw, csvFileFormat);
                String[] headers = new String[3];
                headers[0] = "CustomerName";
                headers[1] = "CustomerCompany";
                headers[2] = "CustomerPhoneNumber";
                csvFilePrinter.printRecord(headers);
                csvFilePrinter.printRecord(customer.getCustomerCsvData());
                pw.flush();
                pw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        timer.stop();
    }
}
