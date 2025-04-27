import javax.swing.*;


/** 
 * Provides an error pop up wuth custom text
 * 
*/
public class ErrorBox
{
    public ErrorBox (String message )
    {
        JFrame frame = new JFrame("Error");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(200, 100);

        frame.add(new JLabel(message));

        frame.setVisible(true);
    }

}