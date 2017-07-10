package application;

/**
 * Abstract class used in order to have the product type and set it's type
 */
public abstract class AbstractProductType
{
    private String productType;

    public AbstractProductType(String productType)
    {
        this.productType = productType;
    }

    /**
     * @return the productType
     */
    public String getProductType()
    {
        return productType;
    }

}
