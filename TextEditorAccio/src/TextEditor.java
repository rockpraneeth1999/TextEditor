import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;

    //file items
    JMenuItem newFile,openFile,saveFile;
    //edit items
    JMenuItem cut,copy,paste,selectAll,close;

    JTextArea textArea;

    TextEditor(){

        // Initialize the frame
        frame=new JFrame();

        // Initialize MenuBar
        menuBar=new JMenuBar();

        //initialize textArea
        textArea=new JTextArea();

        //add menus before attaching menuBar to frame
        //initialize file-menu
        file=new JMenu("File");
        //initialize edit-menu
        edit=new JMenu("Edit");

        //add items to menuItems before adding to menuBar
        //initialize file items
        newFile=new JMenuItem("New File");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("Save File");

        //initialize edit items
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");

        //add actionListener before adding to Menus
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //add items to respective menuItems
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //add menus to menuBar
        menuBar.add(file);
        menuBar.add(edit);

        /* -------------------------------------------------------------------------------------------------- */

        // set/attach the menuBar to frame
        frame.setJMenuBar(menuBar);

        //set textArea to frame
        //frame.add(textArea);

        //Create content panel
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //Add text area to the panel
        panel.add(textArea,BorderLayout.CENTER);
        //create scroll pane
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);

        // set position and dimensions
        frame.setBounds(100,200,400,400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //make frame visible
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setTitle("Text Editor");

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){

        if(actionEvent.getSource() == copy){
            textArea.copy();
        }

        if(actionEvent.getSource() == cut){
            textArea.cut();
        }

        if(actionEvent.getSource() == paste){
            textArea.paste();
        }

        if(actionEvent.getSource() == selectAll){
            textArea.selectAll();
        }

        if(actionEvent.getSource() == close){
            System.exit(0); // 0 - status - close the application
        }

        //OpenFile
        if(actionEvent.getSource() == openFile){
            // open file chooser
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption=fileChooser.showOpenDialog(null);
            // If we have clicked on Open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //Getting selected file
                File file=fileChooser.getSelectedFile();
                //Get the path of selected file
                String filePath= file.getPath();
                try{
                    //Initialize file reader
                    FileReader fileReader=new FileReader(filePath);
                    //Initialize Buffer Reader
                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    String intermediate="", output="";
                    //Read contents of file line by line
                    while((intermediate= bufferedReader.readLine()) != null){
                        output+=intermediate+"\n";
                    }
                    //set output string to text area
                    textArea.setText(output);
                }
                catch (FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        // Save file
        if(actionEvent.getSource() == saveFile){
            //Initialize file picker
            JFileChooser fileChooser=new JFileChooser("C:");
            //Gte choose option from file chooser
            int chooseOption=fileChooser.showSaveDialog(null);
            //check if we clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //Create a new file with chosen path and file name
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //Initialize file writer
                    FileWriter fileWriter=new FileWriter(file);
                    //Initialize Buffered Writer
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    //write contents od text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        //new window
        if(actionEvent.getSource() == newFile){
            TextEditor newTextEditor=new TextEditor();
        }
    }

    public static void main(String[] args) {

        TextEditor textEditor=new TextEditor();

    }

}
