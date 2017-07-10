package application;

/**
 * This represents the class for the Sales
 */
public class Sale
{
    private AbstractProductType productType;
    private int value;

    public Sale(AbstractProductType productType, int value)
    {
        this.productType = productType;
        this.value = value;
    }

    /**
     * @return the productType
     */
    public AbstractProductType getProductType()
    {
        return productType;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(AbstractProductType productType)
    {
        this.productType = productType;
    }

    /**
     * @return the value
     */
    public int getValue()
    {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value)
    {
        this.value = value;
    }

}
