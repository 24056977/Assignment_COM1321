import java.util.ArrayList;
import java.util.Scanner; 
public class Products
{
    private ArrayList<ReportData> products = new ArrayList<>();

    void insertProduct(ReportData Product)
    {
        this.products.add(Product);
    }

    //displayMenu
    public void displayMenu(Scanner input)
    {
        String productCode;
        String codeMessage ="\nPlease enter the product code to search: ";
        //System.out.print("\nPlease select one of the following menu items: "); // make it prompt too
        //main menu
        //System.out.print("\n1. Capture a new product \n2. Search for a product \n3. Update a product \n4. Delete a product \n5. Print report \n6. Exit Application\n"); // make it a prompt is the best
        String menuTitle = "\nPlease select one of the following menu items: ";
        String menuPrompt = menuTitle + "\n1. Capture a new product \n2. Search for a product \n3. Update a product \n4. Delete a product \n5. Print report \n6. Exit Application\nSelect(1-6): ";
        int option = validNumb(input, menuPrompt, "Timeout", 6); // should exit after 3 times of incorrect input
       
        switch(option)
        {
            case 1: // capture a new product
                System.out.println("\nProduct requirements: Unique Code\n"+
                                   "                    : Name\n"+
                                   "                    : Category\n"+
                                   "                    : Warranty\n"+
                                   "                    : Price\n"+
                                   "                    : Supplier\n"+
                                   "                    : Stock Level");
                String optionString = validString(input, "Enter (1) to continue or any other key to cancel >> ");
                if(optionString.equals("1"))
                {
                    System.out.println("\nCAPTURE A NEW PRODUCT");
                    System.out.println("****************************");
                    captureProduct(input);
                }
                else 
                    System.out.println("Product could not be captured!!!");
                break;
            case 2: //Search for a product
                productCode = validString(input, codeMessage);
                displayProduct(productCode);
                break;   
            case 3: //Update a product
                productCode = validString(input, codeMessage);
                updateProduct(input, productCode);
                break;
            case 4: //Delete a product
                productCode = validString(input, codeMessage);
                deleteProduct(input, productCode);   
                break;
            case 5: //Print a report
                displayReport();
                break;
            case 6: break; // Exit Application
            default: System.out.println("Option out of range indicate exiting menu");
                break;
        }
    }

    //captureProduct()
    public void captureProduct(Scanner input)
    {
        ReportData product;
        String code = "Enter the product code: ";
        String name = "Enter the product name: ";
        String warranty = "Indicate the product warranty. Enter (1) for 6 months or any other key for 2 years >> ";
        String category = "Unknown"; // in case switch fails to initialize it 
        double price;
        int level;

        code = validString(input, code);
        if(searchProduct(code) != null)
        {
            System.out.println("Product code already exist!!!");
            return;
        }
        name = validString(input, name);
        int option;

        do
        {
            option = validNumb(input);
            switch(option)
            {
                //String category;
                //Choose category the product falls under
                case 1: //Desktop Computers
                    category = "Desktop Computer";
                    break;
                case 2: //Laptop
                    category = "Laptop";
                    break;
                case 3: //Tablet
                    category = "Tablet";
                    break;
                case 4: //Printer
                    category = "Printer";
                    break;
                case 5: //Gaming Console
                    category = "Gaming Console";
                    break;
                default:
                    System.out.println("Invalid input, Option out of range indicate exiting menu!!!\n"); 
                    continue;
            }
        }while(category.equals("Unknown"));
        warranty = validString(input, warranty);
        warranty = (warranty.equals("1")) ? "6 months" : "2 years"; //ternary operator - I forget this name, don't knw why but it's makes the code shorter
        price = validNumb(input, "Enter the price for " + name + " >> ", 0.0); 
        String supplier = "Enter the supplier for " + name + " >> ";
        supplier = validString(input, supplier);
        String levelPrompt = "Enter the stock level for " + name + " >> ";
        String levelFeedback = "Timeout \nStock level set to default value!!!";
        level = validNumb(input, levelPrompt,levelFeedback, 1); // default value because you will always have at least one quantity of something
        product = new ReportData(code, name, category, warranty, price, level, supplier);

        saveProduct(product);
        //return product;
    }
    
    //saveProduct() to the ArrayList - called by the captureProduct()
    public void saveProduct(ReportData product)
    {
        products.add(product);
        System.out.println("Product details have been saved successfully!!!");
    }

    //searchProduct() from the ArrayList - works under if-else statements
    public ReportData searchProduct(String productCode)
    {
        if(productCode.isEmpty())
            return null;
       for(int i = 0; i < products.size(); i++)
       {
            if(products.get(i).getCode().equalsIgnoreCase(productCode))
            {
               return products.get(i);
            }
       }
       return null;
    }


    //displayProduct() to the user
    public void displayProduct(String productCode)
    {
        ReportData displayProduct = searchProduct(productCode);
        if(displayProduct != null)
        {
            int borderSize = 60;
            for(int i = 1; i <= borderSize; i++) System.out.print("*"); //printing broader 
            System.out.println("\nPRODUCT SEARCH RESULTS");
            for(int i = 1; i <= borderSize; i++) System.out.print("*");
            System.out.println("\n" + displayProduct);
            for(int i = 1; i <= borderSize; i++) System.out.print("*");
            System.out.println(); //since I won't pass Scanner object to this method I print nothing to move a cursor to a new line
        }
        else System.out.println("The product cannot be located. Invalid input!!!");
    }


    //displayReport() to the user
    public void displayReport()
    {
        int borderSize = 60;
        if(!products.isEmpty())
        {
            int productNumb = 0;
            double totProductPrice = 0;
            double avgProductPrice = 0;
            System.out.println("\nPRODUCT REPORT");
            for(int i = 1; i <= borderSize; i++)
                System.out.print("=");
            System.out.println();
            for(ReportData findProduct : products)
            {
                totProductPrice += findProduct.getPrice() * findProduct.getLevel();
                productNumb++;
                System.out.println("PRODUCT " + (productNumb));
                for(int i = 1; i <= borderSize; i++)
                    System.out.print("-");
                System.out.println();
                //I wanted to print the object straight away since toString() was overrode but sadly the format is not the same (":" and ">>") 
                System.out.println("PRODUCT CODE         >> " + findProduct.getCode() +"\n"
                                 + "PRODUCT NAME         >> "   + findProduct.getName() +"\n"
                                 + "PRODUCT WARRANTY     >> "   + findProduct.getWarranty() +"\n"
                                 + "PRODUCT CATEGORY     >> "   + findProduct.getCategory() +"\n"
                                 + "PRODUCT PRICE        >> R"   + findProduct.getPrice() +"\n"
                                 + "PRODUCT STOCK LEVELS >> "   + findProduct.getLevel() +"\n"
                                 + "PRODUCT SUPPLIER     >> "   + findProduct.getSupplier());
                for(int i = 1; i <= borderSize; i++)
                    System.out.print("-");
                System.out.println();
            }
            avgProductPrice = totProductPrice/products.size(); // ArrayList size is more accurate than productNumb
            for(int i = 1; i <= borderSize; i++)
                System.out.print("=");
            System.out.println();
            System.out.println("TOTAL PRODUCT COUNT  : " + productNumb);
            System.out.printf("TOTAL PRODUCT VALUE  : R%.2f\n", totProductPrice);
            System.out.printf("AVERAGE PRODUCT VALUE: R%.2f\n", avgProductPrice);
            for(int i = 1; i <= borderSize; i++)
                System.out.print("=");
            System.out.println();
        }
        else
        {
            System.out.println("PRODUCT REPORT");
            for(int i = 1; i <= borderSize; i++)
                System.out.print("=");
            System.out.println("\nThere are currently no products registered to display\n");
            for(int i = 1; i <= borderSize; i++)
                System.out.print("=");
            System.out.println();
        }
    }    


    //updateProduct() from the ArrayList
    public void updateProduct(Scanner input, String productCode)
    {
        int countUpdates = 0; //used to define a correct feedback if there where any changes on the product
        ReportData productUpdate = searchProduct(productCode); // temp object is pointing to the same memory address of the original will help not to call the search method all over
        if(productUpdate != null)
        {
           String warrantPrompt = "Update the warranty? ";
           String pricePrompt = "Update the product price? ";
           String levelPrompt = "Update the stock level? ";

           //Warranty Updates
           if(validChar(input, warrantPrompt) == 'y')
           {
                do  //warranty does not fit the validString() loop feedback and do-while loop works the best
                {
                    System.out.print("Enter (1) for 6 months or any other key for 2 years >> ");
                    warrantPrompt = input.nextLine().trim(); // I choose to override this variable because I won't use it anymore and I need it for this job now
                }while(warrantPrompt.isEmpty());
                if(warrantPrompt.equals("1"))
                    productUpdate.setWarranty("6 months");
                else
                    productUpdate.setWarranty("2 years");
                countUpdates++;

           }
           //Price Updates
           if(validChar(input, pricePrompt) == 'y')
           {
                double productPrice = productUpdate.getPrice();
                productUpdate.setPrice(validNumb(input, "Enter the new price for " + productUpdate.getName() + " >> ", productUpdate.getPrice()));
                if(productUpdate.getPrice() != productPrice)
                   countUpdates++;
           }
           //Stock level Updates
           if(validChar(input, levelPrompt) == 'y')
           {
                int productLevel = productUpdate.getLevel();
                levelPrompt = "Enter the new stock level for " + productUpdate.getName() + " >> "; // override because I want to use it differently now
                String levelFeedback = "Timeout \nstock level set to default value!!!";
                productUpdate.setLevel(validNumb(input, levelPrompt, levelFeedback, productUpdate.getLevel()));
                if(productUpdate.getLevel() != productLevel)
                    countUpdates++;
           }
           if(countUpdates >= 1)
                System.out.println("Product details has been updated successfully!!! ");
           else
                System.out.println("Product details were not updated!!!");          
        }
        else
            System.out.println("The product cannot be located. Invalid input!!!");
    }


    //deleteProduct() from the array - remove it;
    public void deleteProduct(Scanner input, String productCode)// use validChar() to get response
    {
        ReportData deleteProduct = searchProduct(productCode);
        if(deleteProduct == null)
            System.out.println("The product cannot be located. Invalid input!!!");
        else if(validChar(input, "Delete " + deleteProduct.getName() + " from the list\n") =='y') // minimize variable use hence i pass the raw string 
        {
            products.remove(deleteProduct);
            System.out.println("Product details has been deleted successfully!!! ");
        }
        else 
            System.out.println("Product details deletion cancelled!!! ");
    }


    //exitApplication
    public void exitApplication(Scanner input, ReportData products)
    {
        String stringResponse = "";
        while( true)  // while(true || stringResponse.isEmpty())
        {
            stringResponse = validString(input, "Enter (1) to launch menu or any other key to exit: ");
            if(!stringResponse.equals("1")) break;
            else displayMenu(input);
        }
    }


    //Validating string - we do not take empty strings 
    public String validString(Scanner input, String prompt)
    {
        String validString;
        while(true) 
        {
            System.out.print(prompt);
            if(prompt.toLowerCase().contains("name") || prompt.toLowerCase().contains("supplier")) //I use .contains() to validate name and supplier because they are allowed to have spaces in between
                validString = input.nextLine().trim();
            else 
                validString = input.nextLine().replace(" ","");
            if(!validString.isEmpty())
                break;
        }
        return validString;
    }


    //  Validating User Inputs Methods
    public char validChar(Scanner input, String prompt)
    {
        int invalidInputs = 0;  // count the number of invalid inputs and default char value;
        System.out.print(prompt + "(y) Yes, (n) No: ");
        String stringResponse = input.nextLine().trim().toLowerCase();
        while(stringResponse.isEmpty() || (stringResponse.charAt(0) != 'y' && stringResponse.charAt(0) != 'n'))
        {
            if(stringResponse.isEmpty())
                System.out.print(prompt + "(y) Yes, (n) No: ");
            else
            {
                System.out.print("Invalid input \n" + prompt + "(y) Yes, (n) No: ");
                if(++invalidInputs == 3)
                {
                    System.out.println("Timeout");
                    return 'n';
                }
            }
            stringResponse = input.nextLine().trim().toLowerCase();
        }
        
        return stringResponse.charAt(0);
    }
    //1. I overloaded with double hence used validNumb() not validInt() 
    public int validNumb(Scanner input, String prompt, String feedback, int numb) //the price I am passing is the default value in-case no new value entered
    {
        int count=0;
        for(int i = 1; i <= 3; i++)
        {
            try
            {
                String stringNumb = validString(input, prompt); //take string avoid spaces in between the value entered. only a string can handle that
                numb = Integer.parseInt(stringNumb);
                count++;
                break;
            }
            catch(NumberFormatException e)
            {
                if(i <=2)
                    System.out.print("Invalid input, Letters are not allowed!!!\n");
            }
        }
        if(count == 0) // it means that the new number was not captured
        {
            System.out.println(feedback);
        }
        return Math.abs(numb); //there will be no occasion in the program were a user need a negative number(only typo)
    }
    //2. Method overload for double
    public double validNumb(Scanner input,String prompt, double numb ) //the price I am passing is the default value in-case no new value entered
    {
        int count=0;
        for(int i = 1; i <= 3; i++)
        {
            try
            { 
                String stringNumb = validString(input, prompt); //I have forgot why I am reading price as a string😭
                numb = Double.parseDouble(stringNumb); //oh! I think i'm reading it as a string because I prevent user from leaving a space in between a price value🥱😅
                count++;
                break;
            }
            catch(NumberFormatException e)
            {
                if(i <=2)
                    System.out.println("Invalid input, Letters are not allowed!!!");
            }
        }
        if(count == 0) // it means that the new number was not captured
            System.out.println("Timeout \nPrice set to default amount!");
        return Math.abs(numb);//there price will not be added or subtracted, it shall always be positive 
    }
    //3. Method overload for inputs only
    public int validNumb(Scanner input)
    {
        int exitApplication = 0;  // not a magic number but the last option on the menu
        String categoryPrompt = "Select the product category:\n1. Desktop Computer \n2. Laptop \n3. Tablet \n4. Printer \n5. Gaming Console\n";
        String prompt =categoryPrompt + " \nProduct Category >> ";
        return validNumb(input, prompt,"Timeout", exitApplication);
    }
}