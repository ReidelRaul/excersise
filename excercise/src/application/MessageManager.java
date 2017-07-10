/*
 * Copyright (C) Elektrobit Automotive GmbH
 * All rights reserved
 */

package application;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible to process the messages
 */
public final class MessageManager
{
    private static volatile MessageManager instance = null;

    private static int messagesNumber = 0;

    private MessageManager()
    {
    }

    public static MessageManager getInstance()
    {
        if (instance == null)
        {
            synchronized (MessageManager.class)
            {
                if (instance == null)
                {
                    instance = new MessageManager();
                }
            }
        }
        return instance;
    }

    public void processMessages(List<AbstractMessage> messages)
    {
        int orangeSales = 0;
        int appleSales = 0;
        int orangeSalesValue = 0;
        int appleSalesVaue = 0;

        //Get the salesManager that will create/modify the sales
        SalesManager salesManager = SalesManager.getInstance();

        for (AbstractMessage message : messages)
        {

            messagesNumber++;
            if (messagesNumber > 0)
            {
                //If the messageNumber is multiple of 10 then we do the first writing
                if ((messagesNumber % 10) == 0)
                {
                    for (Sale sale : salesManager.getSales())
                    {
                        AbstractProductType productType = sale.getProductType();
                        if ("orange".equals(productType.getProductType()))
                        {
                            orangeSales++;
                            orangeSalesValue += sale.getValue();
                        }
                        else if ("apple".equals(productType.getProductType()))
                        {
                            appleSales++;
                            appleSalesVaue += sale.getValue();
                        }
                    }
                    System.out.println("The number of orangeSales is: " + orangeSales + " and the total value of those sales is: " + orangeSalesValue);
                    System.out.println("The number of appleSales is: " + appleSales + " and the total value of those sales is: " + appleSalesVaue);
                }
                //If the messageNumber is multiple of 50 then we do the second writing and pause the app
                if ((messagesNumber % 50) == 0)
                {
                    System.out.println("\n==============================================================");
                    for (Sale sale : salesManager.getSales())
                    {
                        composeMessageForTheChangesThatWereDoneOnTheSales(sale);
                        System.out.println("\n==============================================================");
                    }
                    System.out.println("App is pausing");
                    break;
                }
            }

            //Get the message parts
            String operation = message.getOperation();
            int numberOfSales = message.getNumberOfSales();
            String productTypeName = message.getProductType();
            int value = message.getValue();

            //Create a new product
            ProductType productType = new ProductType(productTypeName);

            //Implementation can be easily switched to a factory that will create the type
            //ProductTypeFactory instance = ProductTypeFactory.getInstance();
            //AbstractProductType productType = instance.getProductType(productTypeName);

            //If we do not have an operation then we create a sale
            if (operation == null)
            {

                for (int i = 0; i < numberOfSales; i++)
                {
                    salesManager.createSale(message, value, productType);
                }

            }
            else
            {
                //if we do have an operation then we alter the sales given by the message
                salesManager.doPerformOperationOnSales(operation, productType, value, message);
            }

        }
        System.out.println("No more messages");
    }

    /**
     * This method will create the message for each of the Sales
     * 
     * @param sale - The sale for whom the current output console message is created
     *
     */
    private static void composeMessageForTheChangesThatWereDoneOnTheSales(Sale sale)
    {
        SalesManager salesManager = SalesManager.getInstance();
        Map<Sale, LinkedList<AbstractMessage>> salesToMessagesMap = salesManager.getSalesToMessagesMap();
        LinkedList<AbstractMessage> linkedList = salesToMessagesMap.get(sale);
        for (AbstractMessage operationMessage : linkedList)
        {
            if (linkedList.getFirst().equals(operationMessage))
            {
                System.out.print("The sale with the product " + sale.getProductType().getProductType() + " and the value " + sale.getValue() + " was created by the message containing the productType :" + operationMessage.getProductType() + "; the value : " + operationMessage.getValue());
                if (operationMessage.getNumberOfSales() > 0)
                {
                    System.out.print("; and the number of sales: " + operationMessage.getNumberOfSales());
                }
                if (linkedList.size() > 1)
                {
                    System.out.println(" and was changed by the following message(s)");
                }
            }
            else
            {
                System.out.println("\n ProductType: " + operationMessage.getProductType() + "; Value : " + operationMessage.getValue() + "; Operation: " + operationMessage.getOperation());
            }
        }
    }

}
