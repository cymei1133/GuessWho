
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI implements ActionListener {
    private static JPanel panel;
    private static JFrame frame;
    private static JLabel firstNameLabel;
    private static JTextField userText;
    private static JLabel lastNameLabel;
    private static JTextField lastNameText;
    private static JButton enterButton;
    private static JButton clearButton;
    private static JLabel success;
    private static JButton guessButton;
    private static JLabel imgLabel;
    private static JTextField guessField;
    private static int randIndex;
    private static ArrayList<String> imgURLS;
    private static ArrayList<String> imgAnswers;
    private static ImageIcon image;
    private static JButton closeButton;

    public static void main(String[] args) {

        imgURLS = new ArrayList<>();
        imgURLS.add("/Users/_cndi_m1/Downloads/rif.jpeg"); // Riftan
        imgURLS.add("/Users/_cndi_m1/Desktop/eur.jpg"); // Euredian

        imgAnswers = new ArrayList<>();
        imgAnswers.add("Riftan");
        imgAnswers.add("Euredian");

        panel = new JPanel(new FlowLayout());
        frame = new JFrame();
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        // set frame in the center of the screen
        frame.setLocationRelativeTo(null);

        panel.setLayout(null);
        firstNameLabel = new JLabel("First Name");
        firstNameLabel.setBounds(10, 20, 80, 25);
        panel.add(firstNameLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setBounds(10, 50, 80, 25);
        panel.add(lastNameLabel);

        lastNameText = new JTextField(20);
        lastNameText.setBounds(100, 50, 165, 25);
        panel.add(lastNameText);

        enterButton = new JButton("Enter");
        enterButton.setBounds(10, 80, 80, 25);
        enterButton.addActionListener(new GUI()); // will go to actionPerformed method
        panel.add(enterButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(100, 80, 80, 25);
        clearButton.addActionListener(new GUI());
        panel.add(clearButton);

        success = new JLabel("");
        success.setBounds(10, 450, 300, 25);
        panel.add(success);

        imgLabel = new JLabel("");
        imgLabel.setBounds(10, 150, 450, 250);
        panel.add(imgLabel);

        closeButton = new JButton("Close");
        closeButton.setBounds(400, 10, 80, 25);
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(closeButton);

        frame.setVisible(true);
    }

    // This code runs whenever you click on that button
    @Override
    public void actionPerformed(ActionEvent e) {
        // button is login button
        if (e.getSource() == enterButton) {
            // success.setText("Don't give up!");
            // success.setFont(new Font("Arial", Font.BOLD, 20));
            randIndex = new Random().nextInt(imgURLS.size());
            String img = imgURLS.get(randIndex);
            image = new ImageIcon(img);
            imgLabel.setIcon(image);
            addGuessTextField();

        }
        // make guess text box appear first
        else if (e.getSource() == guessButton) {
            String guess = guessField.getText();
            if (guess.equals(imgAnswers.get(randIndex))) {
                success.setText("Good job " + userText.getText() + " " + lastNameText.getText() + "!");
            } else {
                success.setText("Please try again :)");
            }
        } else if (e.getSource() == clearButton) {
            userText.setText("");
            lastNameText.setText("");
            success.setText("");
            imgLabel.setIcon(null);
            panel.remove(guessField);
            panel.remove(guessButton);
            panel.repaint();
        }

    }

    private void addGuessTextField() {
        guessField = new JTextField(20);
        guessField.setBounds(10, imgLabel.getY() + imgLabel.getHeight() + 10, 165, 25);
        panel.add(guessField);
        addGuessButton();
        panel.repaint();
    }

    private void addGuessButton() {
        guessButton = new JButton("Guess");
        guessButton.setBounds(guessField.getWidth() + 10, imgLabel.getY() + imgLabel.getHeight() + 10, 80, 25);
        guessButton.addActionListener(this);
        panel.add(guessButton);

    }

}