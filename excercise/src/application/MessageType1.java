package application;

/**
 * Instance for the 2 Message types that will actually create a sale
 */
public class MessageType1 extends AbstractMessage
{

    public MessageType1(int numberOfSales, String productType, int value)
    {
        super(numberOfSales, productType, value);

    }

}