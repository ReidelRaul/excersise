/*
 * Copyright (C) Elektrobit Automotive GmbH
 * All rights reserved
 */

package application;

/**
 * This class will create the messages based on the values that it receives.
 */
public final class MessageTypeFactory
{
    private static volatile MessageTypeFactory instance = null;

    private MessageTypeFactory()
    {
    }

    public static MessageTypeFactory getInstance()
    {
        if (instance == null)
        {
            synchronized (MessageTypeFactory.class)
            {
                if (instance == null)
                {
                    instance = new MessageTypeFactory();
                }
            }
        }
        return instance;
    }

    public AbstractMessage getMessage(String productType, int value)
    {
        return getMessage(null, 1, value, productType);
    }

    public AbstractMessage getMessage(int numberOfSales, int value, String productType)
    {
        return getMessage(null, numberOfSales, value, productType);
    }

    public AbstractMessage getMessage(String operation, int value, String productType)
    {
        return getMessage(operation, 0, value, productType);
    }

    private AbstractMessage getMessage(String operation, int numberOfSales, int value, String productType)
    {
        if ((value > 0) && (productType != null))
        {
            if (operation != null)
            {
                return new MessageType2(operation, value, productType);
            }
            else
            {
                return new MessageType1(numberOfSales, productType, value);
            }
        }
        System.out.println("Message productype " + productType + " or value " + value + " are not configured correctly");
        return null;
    }

}
