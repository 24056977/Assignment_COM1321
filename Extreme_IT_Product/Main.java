import javax.swing.JOptionPane;
import java.util.Scanner;
public class Main
{
    public static void main(String [] password) // no longer just any [] argument but array that should contain a password to access existing products🥱
    {
        Scanner input = new Scanner(System.in);
        Products productManager = new Products(); //instantiation of product storage object(inventory)
        if(password.length > 0)  
        {
            Main product = new Main();
            product.existingProducts(productManager, password);
        }
        System.out.println("\nBRIGHT FUTURE TECHNOLOGIES APPLICATION");
        System.out.println("**************************************");
        productManager.exitApplication(input, null);
        input.close();
    }
    
    /* 
        this method will save you time of capturing new products to test if my methods are working
        You can type my names(MM KHOZA) on the command line as a password to access the feature 
    */
    void existingProducts(Products productManager,String [] password)
    {
        String myPassword = String.join(" ", password); // String method that join array elements into one String
        if (!myPassword.equals("MM KHOZA"))
        {
            JOptionPane.showMessageDialog(null, "Password is my name, 'MM KHOZA' ");
            return;
        }
        ReportData product1 = new ReportData("A55", "EliteBook", "Laptop", "2 years", 14500, 3, "IT_4_Africa");
        ReportData product2 = new ReportData("A54", "ExtremeBook", "Desktop Computer", "2 years", 12500, 3, "IT_4_Africa");
        ReportData product3 = new ReportData("A53", "PS5", "Gaming Console", "6 months", 14500, 3, "IT_4_Africa");
        productManager.insertProduct(product1);
        productManager.insertProduct(product2);
        productManager.insertProduct(product3);
    }
}