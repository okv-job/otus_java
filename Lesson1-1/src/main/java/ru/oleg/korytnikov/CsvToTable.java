package ru.oleg.korytnikov;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;

public class CsvToTable {
    private final static String PATH_TO_FILE = System.getProperty("user.dir") + "/clients.csv";

    public static void main(String... args) {
        double startTime = System.currentTimeMillis();
        try {
            Customer customer = getCustomer();

            File csvData = new File(PATH_TO_FILE);
            boolean newFile = !csvData.exists();

            FileWriter pw = new FileWriter(PATH_TO_FILE, true);
            CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");
            CSVPrinter csvFilePrinter = new CSVPrinter(pw, csvFileFormat);
            if (newFile) {
                addHeader(csvFilePrinter);
            }
            csvFilePrinter.printRecord(customer.getCustomerCsvData());
            pw.flush();
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The recording was created. It took " +
                (System.currentTimeMillis() - startTime) / 1000.0 + " seconds.");
    }

    private static Customer getCustomer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the customer name:");
        String customerName = br.readLine();
        System.out.println("Enter the company:");
        String company = br.readLine();
        System.out.println("Enter phone number:");
        String phoneNumber = br.readLine();
        br.close();
        return new Customer(customerName, company, phoneNumber);
    }

    private static void addHeader(CSVPrinter csvFilePrinter) throws IOException {
        String[] headers = {"CustomerName", "CustomerCompany", "CustomerPhoneNumber"};
        csvFilePrinter.printRecord(headers);
    }
}
