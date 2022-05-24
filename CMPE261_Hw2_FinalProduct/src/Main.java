
public class Main {


    // sets the app name and the spawn delay which is used to spawn the cars
    public static final String APP_NAME = "Crossroads";
    public static final int SPAWN_DELAY = 1200;

    // static reference to the main class and references to mainWindow and guiMain
    private static Main main;
    private MainWindow mainWindow;
    private GuiMain guiMain;


//     start method that launches the program and constantly repaints the JFrame

    public void start() {
        guiMain = new GuiMain();
        mainWindow = new MainWindow(guiMain);
        new Thread(() -> {
            while (true) {
                mainWindow.repaint();
            }
        }).start();
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public GuiMain getGuiMain() {
        return guiMain;
    }


//      main method which is used to launch the program

    public static void main(String[] args) {
        main = new Main();
        try {
            main.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Main getMain() {
        return main;
    }
}
