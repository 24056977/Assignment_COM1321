public class ReportData
{
    private final String code;
    private final String name;
    private final String category;
    private String warranty;
    private double price;
    private int level;
    private final String supplier;

    public ReportData( String code, String name, String category,
        String warranty, double price, int level, String supplier)
    {
        this.code = code;
        this.name = name;
        this.category = category;
        this.warranty = warranty;
        this.price = price;
        this.level = level;
        this.supplier = supplier;
    }
    @Override
    public String toString()
    {
        String product = "PRODUCT CODE         : " + code +"\n"
                       + "PRODUCT NAME         : "   + name +"\n"
                       + "PRODUCT WARRANTY     : "   + warranty +"\n"
                       + "PRODUCT CATEGORY     : "   + category +"\n"
                       + "PRODUCT PRICE        : R"   + price +"\n"
                       + "PRODUCT STOCK LEVELS : "   + level +"\n"
                       + "PRODUCT SUPPLIER     : "   + supplier;
        return product;
    }
    public void setWarranty(String warranty)
    {
        this.warranty = warranty;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    public void setLevel(int level)
    {
        this.level = level;
    }

    public String getCode()
    {
        return code;
    }
    public String getName()
    {
        return name;
    }
    public String getCategory()
    {
        return category;
    }
    public String getWarranty()
    {
        return warranty;
    }
    public double getPrice()
    {
        return price;
    }
    public int getLevel()
    {
        return level;
    }
    public String getSupplier()
    {
        return supplier;
    }
 }