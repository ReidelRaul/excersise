package application;

//Instance of the Abstract product in case we want a different implementation using a factory for only certain types
public class Apple extends AbstractProductType
{

    /**
     * @param productType
     */
    public Apple(String productType)
    {
        super(productType);
    }

}
