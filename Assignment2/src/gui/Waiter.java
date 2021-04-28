package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class Waiter implements ActionListener
{
    private final CountDownLatch latch = new CountDownLatch(1);

    @Override
    public void actionPerformed(ActionEvent e)
    {
        latch.countDown();
    }

    void waitFor()
    {
        try
        {
            latch.await();
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}