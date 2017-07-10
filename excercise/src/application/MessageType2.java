package application;

public class MessageType2 extends AbstractMessage
{
    /**
     * Instance for the Message type that will actually alter a sale
     */
    public MessageType2(String operation, int value, String productType)
    {
        super(operation, value, productType);
    }

}