/*
 * Copyright (C) Elektrobit Automotive GmbH
 * All rights reserved
 */

package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for creating the Sales or performing operations on them
 */
public final class SalesManager
{
    private static volatile SalesManager instance = null;
    private static List<Sale> sales = new ArrayList<>();
    private static Map<Sale, LinkedList<AbstractMessage>> salesToMessagesMap = new HashMap<>();

    private SalesManager()
    {
    }

    public static SalesManager getInstance()
    {
        if (instance == null)
        {
            synchronized (SalesManager.class)
            {
                if (instance == null)
                {
                    instance = new SalesManager();
                }
            }
        }
        return instance;
    }

    /**
     * @param message
     * @param value
     * @param productType
     */
    public void createSale(AbstractMessage message, int value, ProductType productType)
    {
        Sale sale = createSaleAndAddToList(value, productType);
        linkMessagesToSales(message, sale);
    }

    /**
     * @param operation
     * @param productType
     * @param value
     * @param message
     */
    public void doPerformOperationOnSales(String operation, ProductType productType, int value, AbstractMessage message)
    {
        for (Sale sale : sales)
        {
            AbstractProductType saleProductType = sale.getProductType();
            if ((saleProductType != null) && saleProductType.getProductType().equals(productType.getProductType()))
            {
                performUpdateOperationOnSale(sale, operation, value);

                linkMessagesToSales(message, sale);
            }
        }
    }

    private static Sale createSaleAndAddToList(int value, AbstractProductType productType)
    {
        Sale sale = new Sale(productType, value);
        sales.add(sale);
        return sale;
    }

    /**
     * @param sale
     * @param operation
     * @param value
     */
    private static void performUpdateOperationOnSale(Sale sale, String operation, int value)
    {
        if (operation != null)
        {

            if (operation.equals("Add"))
            {
                int updatedValue = sale.getValue() + value;
                sale.setValue(updatedValue);
            }
            else if (operation.equals("Substract"))
            {
                int updatedValue = sale.getValue() - value;
                sale.setValue(updatedValue);
            }

        }
    }

    private static void linkMessagesToSales(AbstractMessage message, Sale sale)
    {
        LinkedList<AbstractMessage> linkedList = salesToMessagesMap.get(sale);
        if (linkedList == null)
        {
            linkedList = new LinkedList<>();
        }
        linkedList.add(message);
        salesToMessagesMap.put(sale, linkedList);
    }

    /**
     * @return the sales
     */
    public List<Sale> getSales()
    {
        return sales;
    }

    /**
     * @return the salesToMessagesMap
     */
    public Map<Sale, LinkedList<AbstractMessage>> getSalesToMessagesMap()
    {
        return salesToMessagesMap;
    }
}
