import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPage implements ActionListener{

    Game game  = new Game();

    JFrame frame = new JFrame();
    JLabel label = new JLabel("Game Over");
    JButton restart = new JButton("Restart");
    GameOverPage(){
        label.setBounds(50,0,200,25);
        label.setFont(new Font(null, Font.BOLD,15));

        frame.add(label);

        restart.setBounds(50,100,100,25);
        restart.setFocusable(false);
        restart.addActionListener((ActionListener) this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250,200);
        frame.add(restart);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==restart){
            frame.dispose();
            game.newGame();
        }
    }
}
