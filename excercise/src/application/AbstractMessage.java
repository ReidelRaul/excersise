package application;

public abstract class AbstractMessage
{
    //These are the parameters needed by the messages in order to create or modify the sales
    String productType;
    String operation;
    int value;
    int numberOfSales;

    //Add constructors that will be used by the two Classes that extend the AbstractMessage

    public AbstractMessage(int numberOfSales, String productType, int value)
    {
        setValues(null, numberOfSales, value, productType);

    }

    public AbstractMessage(String operation, int value, String productType)
    {
        setValues(operation, 0, value, productType);

    }

    // Set the values that we get from the messages
    public void setValues(String operation, int numberOfSales, int value, String productType)
    {
        this.operation = operation;
        this.numberOfSales = numberOfSales;
        this.value = value;
        this.productType = productType;
    }

    // Create getters so that we have access to the parts of the Message

    /**
     * @return the productType
     */
    public String getProductType()
    {
        return productType;
    }

    /**
     * @return the operation
     */
    public String getOperation()
    {
        return operation;
    }

    /**
     * @return the value
     */
    public int getValue()
    {
        return value;
    }

    /**
     * @return the numberOfSales
     */
    public int getNumberOfSales()
    {
        return numberOfSales;
    }
}
