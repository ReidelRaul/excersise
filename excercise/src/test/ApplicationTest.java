/*
 * Copyright (C) Elektrobit Automotive GmbH
 * All rights reserved
 */

package test;

import java.util.ArrayList;
import java.util.List;

import application.AbstractMessage;
import application.MessageManager;
import application.MessageTypeFactory;
import junit.framework.TestCase;

public class ApplicationTest extends TestCase
{
    public void testMessageManager()
    {
        List<AbstractMessage> messages = new ArrayList<>();

        MessageTypeFactory messageFactoryInstance = MessageTypeFactory.getInstance();

        MessageManager messageManager = MessageManager.getInstance();

        AbstractMessage message = messageFactoryInstance.getMessage("orange", 5);
        assertNotNull("The getMessage(type,value) method from the MessageTypeFactory does not work", message);
        messages.add(message);

        AbstractMessage message2 = messageFactoryInstance.getMessage(4, 1, "orange");
        assertNotNull("The getMessage(numberOfSales,value,type) method from the MessageTypeFactory does not work", message2);
        messages.add(message2);

        AbstractMessage message3 = messageFactoryInstance.getMessage("Add", 3, "orange");
        assertNotNull("The getMessage(operation,value,type) method from the MessageTypeFactory does not work", message3);
        messages.add(message3);

        AbstractMessage message4 = messageFactoryInstance.getMessage("apple", 5);
        assertNotNull("The getMessage(type,value) method from the MessageTypeFactory does not work", message4);
        messages.add(message4);

        messageManager.processMessages(messages);
    }
}
