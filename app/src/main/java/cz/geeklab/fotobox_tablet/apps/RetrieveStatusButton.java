package cz.geeklab.fotobox_tablet.apps;

/**
 * Created by Jaroslav on 20. 6. 2015.
 */
public class RetrieveStatusButton extends Thread
{


    @Override
    public void run()
    {
        while(true) {
            System.out.println("Thread is doing something");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


