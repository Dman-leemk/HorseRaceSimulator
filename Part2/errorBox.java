import javax.swing.*;

public class errorBox
{
    public errorBox (String message )
    {
        JFrame frame = new JFrame("Error");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(200, 100);

        frame.add(new JLabel(message));

        frame.setVisible(true);
    }

}