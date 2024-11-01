import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HTMLSearch implements ActionListener {


    private JFrame mainFrame;
    private JPanel gridPanel;
    private JPanel borderPanel;
    private JTextArea textArea1, textArea2, textArea3;
    private JButton button;// go button
    private JTextArea results;


    public static void main(String[] args) {
        HTMLSearch Search = new HTMLSearch();
    }

    /**TO DO!
     * search throughout the url's html code for all webpages
     * display the found links in a non-editable text area!
     * */

    public HTMLSearch(){
        mainFrame = new JFrame ("HTML Search Project!");
        borderPanel = new JPanel(new BorderLayout(2,1));
        gridPanel = new JPanel(new GridLayout());
        textArea1  = new JTextArea("Enter URL Here! (delete me)");
        textArea2 = new JTextArea("Enter Search Term! (delete me) ");
        button = new JButton("GO!"); // have when button pressed in action listener
        button.addActionListener(this); // ask Ms. K how to fix it
        results = new JTextArea ("results here!!");
        results.setLineWrap(true);

        borderPanel.add(textArea1, BorderLayout.NORTH);
        borderPanel.add(textArea2, BorderLayout.SOUTH);
        borderPanel.add(button, BorderLayout.EAST);
        borderPanel.add(gridPanel, BorderLayout.CENTER);
        gridPanel.add (results, SwingConstants.CENTER);
        mainFrame.add (borderPanel);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500,500);
        mainFrame.setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("searching");
        String searchterm = textArea2.getText();
        String searchURL = textArea1.getText();

        try {
            System.out.println();
            System.out.print("hello \n");
            URL url = new URL(searchURL);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            while ((line = reader.readLine()) != null ) {
                if (line.contains("href") && line.contains(searchterm)){
                    int begin = line.indexOf("href")+6;
                    int end = line.indexOf(">", begin) - 1;
                    String link = line.substring(begin, end);
                    System.out.println(link);
                    results.setText(results.getText() + link + "\n");
                }

//                if (button.contains()){
//                }


            }
            reader.close();


        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }

}