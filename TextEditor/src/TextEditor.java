import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //Declaring properties of TextEditor
    JFrame frame;

    JMenuBar menuBar;

    JMenu file, edit;

    //file menu items
    JMenuItem newFile, openFile, saveFile;

    //edit menu items
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;

    TextEditor(){
         //Initialize a frame
        frame = new JFrame(" TextEditor(by Harisha G B)");

         //Initialize the menuBar
        menuBar = new JMenuBar();

        //Initialize textArea
        textArea = new JTextArea();

        //Initialize menues
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //initialze file menu items
        newFile = new JMenuItem("New File");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        //Add action listeners to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        //add new items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("SelectAll");
        close = new JMenuItem("Close");
        //add action listeners to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //add new items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);


        //add menues to menuBar
        menuBar.add(file);
        menuBar.add(edit);

        //set menubar to frame
        frame.setJMenuBar(menuBar);

        //Create a content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        //add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        //Create Scroll pane
        JScrollPane jScrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(jScrollPane);
        //add panel to frame
        frame.add(panel);

        //set dimension of the frame
        frame.setBounds(100,100,400,400);
        //frame.setTitle("TextEditor");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
      if(actionEvent.getSource()==cut){
          //perform cut operation
          textArea.cut();
      }
      if(actionEvent.getSource()==copy){
          //performs copy operation
          textArea.copy();
      }
      if(actionEvent.getSource()==paste){
          //perform paste operation
          textArea.paste();
      }
      if(actionEvent.getSource()==selectAll){
          //perform selectall operation
          textArea.selectAll();
      }
      if(actionEvent.getSource()==close){
          //perform close operation
          System.exit(0);
      }
      if(actionEvent.getSource()==openFile){
          //open a file chooser
          JFileChooser fileChooser = new JFileChooser("c:");
          int chooseOption = fileChooser.showOpenDialog(null);
          //if we have clicked on open button
          if(chooseOption==JFileChooser.APPROVE_OPTION){
              //Getting the selected file
              File file = fileChooser.getSelectedFile();
              //get the path of selected file
              String filePath = file.getPath();
              try{
                  //initialize file reader
                  FileReader fileReader = new FileReader(filePath);
                  //Initialize Buffered reader
                  BufferedReader bufferedReader = new BufferedReader(fileReader);
                  String intermediate = "", output = "";
                  //read contents of file line by line
                  while((intermediate = bufferedReader.readLine())!=null){
                      output+=intermediate+"\n";
                  }
                  //set output string to text area
                  textArea.setText(output);
              } catch(IOException fileNotFoundException){
                   fileNotFoundException.printStackTrace();
              }
          }
      }
      if(actionEvent.getSource()==saveFile){
          //Initialize file picker
          JFileChooser fileChooser = new JFileChooser("C");
          //get choose option from file chooser
          int chooseOption = fileChooser.showSaveDialog(null);
          //Check if we clicked on save button
          if(chooseOption==JFileChooser.APPROVE_OPTION){
              //create a new file with chosen directory path and file name
              File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
              try{
                  //Initialize file writer
                  FileWriter fileWriter = new FileWriter(file);
                  //Initialize Buffer writer
                  BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                  //write contents of text area to file
                  textArea.write(bufferedWriter);
                  bufferedWriter.close();

              }
              catch(IOException ioException){
                  ioException.printStackTrace();
              }
          }
      }
             if(actionEvent.getSource()==newFile){
                 TextEditor newTextEditor = new TextEditor();
             }
    }

    public static void main(String[] args) {
        TextEditor texteditor= new TextEditor();
    }
}