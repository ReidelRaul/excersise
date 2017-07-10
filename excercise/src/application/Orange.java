package application;

//Instance of the Abstract product in case we want a different implementation using a factory for only certain types
public class Orange extends AbstractProductType
{

    /**
     * @param productType
     */
    public Orange(String productType)
    {
        super(productType);
    }

}
