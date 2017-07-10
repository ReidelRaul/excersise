/*
 * Copyright (C) Elektrobit Automotive GmbH
 * All rights reserved
 */

package application;

/**
 * This will create a product if we decide to use the factory implementation for the Products
 */
public final class ProductTypeFactory
{
    private static volatile ProductTypeFactory instance = null;

    private ProductTypeFactory()
    {
    }

    public static ProductTypeFactory getInstance()
    {
        if (instance == null)
        {
            synchronized (ProductTypeFactory.class)
            {
                if (instance == null)
                {
                    instance = new ProductTypeFactory();
                }
            }
        }
        return instance;
    }

    public AbstractProductType getProductType(String productType)
    {
        if (productType.equals("apple"))
        {
            return new Apple("apple");
        }
        else if (productType.equals("orange"))
        {
            return new Orange("orange");
        }

        return null;
    }

}
