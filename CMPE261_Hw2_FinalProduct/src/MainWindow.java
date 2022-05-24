import javax.swing.*;


public class MainWindow extends JFrame {


//      Basic constructor of the MainWindow
//      sets the appName as title such as the bounds, the content pane and the default exit operation


    public MainWindow(JPanel screen) {
        setTitle(Main.APP_NAME);
        setBounds(100, 100, 1280, 720);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(screen);
    }
}
