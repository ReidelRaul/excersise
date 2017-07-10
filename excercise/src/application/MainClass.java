package application;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the main class
 */
public class MainClass
{
    private static List<AbstractMessage> messages = new ArrayList<>();

    public static void main(String[] args)
    {

        //Get message Factory Instance
        MessageTypeFactory messageFactoryInstance = MessageTypeFactory.getInstance();

        //Create messages
        messages.add(messageFactoryInstance.getMessage("orange", 5));
        messages.add(messageFactoryInstance.getMessage("orange", 3));
        messages.add(messageFactoryInstance.getMessage("orange", 4));

        messages.add(messageFactoryInstance.getMessage("apple", 1));
        messages.add(messageFactoryInstance.getMessage("apple", 2));
        messages.add(messageFactoryInstance.getMessage("apple", 6));

        messages.add(messageFactoryInstance.getMessage(26, 7, "apple"));
        messages.add(messageFactoryInstance.getMessage(25, 6, "orange"));

        messages.add(messageFactoryInstance.getMessage("Add", 3, "apple"));
        messages.add(messageFactoryInstance.getMessage("Substract", 1, "orange"));
        messages.add(messageFactoryInstance.getMessage("orange", 25));

        for (int i = 1; i < 40; i++)
        {
            messages.add(messageFactoryInstance.getMessage("orange", i * 10));

        }

        //Get MessageManager Instance and process the messages that we receive
        MessageManager messageManager = MessageManager.getInstance();
        messageManager.processMessages(messages);

    }

}