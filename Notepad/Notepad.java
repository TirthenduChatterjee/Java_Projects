import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

class pad implements ActionListener {
    JFrame f;
    JTextArea t;
    JMenuBar menubar;
    JMenu file, edit, format, color,Fnt,Fntsize;
    JMenuItem New, Open, Save, Saveas, Exit, Undo, Redo,Wordwrap;
    JMenuItem Fnt8,Fnt12,Fnt14,Fnt16,Fnt18,Fnt20,Fnt22,Fnt24;
    JMenuItem F1,F2,F3,F4,F5;
    JMenuItem S1,S2,S3,S4,S5;
    Color blue = new Color(5, 59, 80);
    File_function File = new File_function(this);
    Edit_function File2 = new Edit_function(this);
    Format_fuction File3 =new Format_fuction(this);
    Color_Function File4 =new Color_Function(this);
    KeyHandler_Function File5 = new KeyHandler_Function(this);
     Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    Dimension V= new Dimension(10, Integer.MAX_VALUE);
    Dimension H= new Dimension(Integer.MAX_VALUE,10);
    boolean wordwrap = false;
     JScrollPane sp;
     UndoManager un = new UndoManager();
    pad() {
        gui();
        menubar();
        createtextarea();
        sp = new JScrollPane(t,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.getVerticalScrollBar().setPreferredSize(V);
        sp.getHorizontalScrollBar().setBackground(Color.LIGHT_GRAY);
        sp.getVerticalScrollBar().setBackground(Color.lightGray);
        sp.getHorizontalScrollBar().setPreferredSize(H);
        f.setJMenuBar(menubar);
        f.getContentPane().setBackground(Color.green);
        f.getContentPane().setForeground(Color.WHITE);
        f.add(sp);
        Save.addActionListener(this);
        f.validate();
    }
    void gui(){
        f = new JFrame();
        f.setTitle("TirthoPad");
        f.setBounds(0, 0,screensize.width,screensize.height);
        // f.setLayout(null);
        ImageIcon I = new ImageIcon("Notepadicon.png");
        f.setIconImage(I.getImage());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    
    }
    void menubar(){
        menubar = new JMenuBar();
        menubar.setBackground(Color.white);
        // menubar.setFont(new Font("Arial", Font.BOLD, 28));
        // menubar.setSize(Integer.MAX_VALUE, 60);
        menubar_File();
        menubar_Edit();
        menubar_Format();
       menubar_Color();
        menubar.add(file);
        menubar.add(edit);
        menubar.add(format);
        menubar.add(color);

    }
    void menubar_File(){
         file = new JMenu("File");
         file.setFont(new Font("Arial",Font.BOLD,16));
          New = new JMenuItem("New");
        New.addActionListener(this);
        New.setActionCommand("new");

        Open = new JMenuItem("Open");
        Open.setActionCommand("open");
        Open.addActionListener(this);

        Save = new JMenuItem("Save");
        Save.setActionCommand("save");
        Save.addActionListener(this);

        Saveas = new JMenuItem("Save as");
        Saveas.setActionCommand("saveas");
        Saveas.addActionListener(this);

        Exit = new JMenuItem("Exit");
        Exit.setActionCommand("exit");
        Exit.addActionListener(this);

        file.add(New);
        file.add(Open);
        file.add(Save);
        file.add(Saveas);
        file.add(Exit);

    }
    void menubar_Edit(){
        edit = new JMenu("Edit");
        edit.setFont(new Font("Arial",Font.BOLD,16));
        Undo = new JMenuItem("Undo");
        Undo.setActionCommand("undo");
        Undo.addActionListener(this);

        Redo = new JMenuItem("Redo");
        Redo.setActionCommand("redo");
        Redo.addActionListener(this);

        edit.add(Undo);
        edit.add(Redo);
    }
    void menubar_Format(){
        format = new JMenu("Format");
        format.setFont(new Font("Arial",Font.BOLD,16));
        Fnt = new JMenu("Font");
        Fntsize = new JMenu("Font Size");

        Wordwrap = new JMenuItem("Wordwrap off");
        Wordwrap.setActionCommand("wordwrap");
        Wordwrap.addActionListener(this);

        F1 = new JMenuItem("Calibri");
        F1.setActionCommand("Calibri");
        F1.addActionListener(this);
        Fnt.add(F1);

        F2 = new JMenuItem("Cascadia Code");
        F2.setActionCommand("Cascadia Code");
        F2.addActionListener(this);
        Fnt.add(F2);

        F3 = new JMenuItem("Lucida Handwriting");
        F3.setActionCommand("Lucida Handwriting");
        F3.addActionListener(this);
        Fnt.add(F3);

        F4 = new JMenuItem("Arial");
        F4.setActionCommand("Arial");
        F4.addActionListener(this);
        Fnt.add(F4);

        F5 = new JMenuItem("Times New Roman");
        F5.setActionCommand("Times New Roman");
        F5.addActionListener(this);
        Fnt.add(F5);

        Fnt8 = new JMenuItem("8");
        Fnt8.setActionCommand("fnt8");
        Fnt8.addActionListener(this);
        Fntsize.add(Fnt8);

        Fnt12 = new JMenuItem("12");
        Fnt12.setActionCommand("fnt12");
        Fnt12.addActionListener(this);
        Fntsize.add(Fnt12);

        Fnt14 = new JMenuItem("14");
        Fnt14.setActionCommand("fnt14");
        Fnt14.addActionListener(this);
        Fntsize.add(Fnt14);

        Fnt16 = new JMenuItem("16");
        Fnt16.setActionCommand("fnt16");
        Fnt16.addActionListener(this);
        Fntsize.add(Fnt16);

        Fnt18 = new JMenuItem("18");
        Fnt18.setActionCommand("fnt18");
        Fnt18.addActionListener(this);
        Fntsize.add(Fnt18);

        Fnt20 = new JMenuItem("20");
        Fnt20.setActionCommand("fnt20");
        Fnt20.addActionListener(this);
        Fntsize.add(Fnt20);

        Fnt22 = new JMenuItem("22");
        Fnt22.setActionCommand("fnt22");
        Fnt22.addActionListener(this);
        Fntsize.add(Fnt22);

        Fnt24 = new JMenuItem("24");
        Fnt24.setActionCommand("fnt24");
        Fnt24.addActionListener(this);
        Fntsize.add(Fnt24);

       
        
        format.add(Wordwrap);
        format.add(Fnt);
        format.add(Fntsize);
    }
    void menubar_Color(){

         color = new JMenu("Color");
        color.setFont(new Font("Arial",Font.BOLD,16));
         S1=new JMenuItem("Default");
         S1.setActionCommand("Default");
         S1.addActionListener(this);
         S2 = new JMenuItem("Hackers Theme");
         S2.setActionCommand("Hackers Theme");
         S2.addActionListener(this);
         S3= new JMenuItem("Old Priest Theme");
         S3.setActionCommand("Old Priest Theme");
         S3.addActionListener(this);
         S4 = new JMenuItem("Dark Theme");
         S4.setActionCommand("Dark Theme");
         S4.addActionListener(this);
         S5 = new JMenuItem("Light Theme");
         S5.setActionCommand("Light Theme");
         S5.addActionListener(this);
         color.add(S1);
         color.add(S2);
         color.add(S3);
         color.add(S4);
         color.add(S5);
    }
    void createtextarea(){
        t = new JTextArea();
        t.addKeyListener(File5);
        t.getDocument().addUndoableEditListener(new UndoableEditListener() {
           public void undoableEditHappened(UndoableEditEvent e){
                    un.addEdit(e.getEdit());
            }
        });
        t.setCaretColor(Color.ORANGE);
        t.setFont(new Font("Calibri", Font.PLAIN, 16));
        t.setBackground(blue);
        t.setForeground(Color.ORANGE);
    }
    @Override
    public void actionPerformed(ActionEvent n) {
        try {
            String command = n.getActionCommand();
            switch (command) {
                case "new":
                    File.newFile();
                    break;
                case "save":
                    File.save();
                    break;
                case "open":
                    File.open();
                    break;
                case "saveas":
                    File.saveas();
                    break;
                case "exit":
                   File.exit();
                   break;
                   case "undo":
                   File2.undo();
                   break;
                   case "redo":
                   File2.redo();
                   break;
                   case "wordwrap":
                   File3.wordwrap();
                   break;
                   case "fnt8":
                   File3.fontsize(8);
                   break;
                   case "fnt12":
                   File3.fontsize(12);
                   break;
                   case "fnt14":
                   File3.fontsize(14);
                   break;
                   case "fnt16":
                   File3.fontsize(16);
                   break;
                   case "fnt18":
                   File3.fontsize(18);
                   break;
                   case "fnt20":
                   File3.fontsize(20);
                   break;
                   case "fnt22":
                   File3.fontsize(22);
                   break;     
                   case "fnt24":
                   File3.fontsize(24);
                   break; 
                   case "Calibri":
                   File3.changefont(command); 
                   break;
                     case "Cascadia Code":
                   File3.changefont(command); 
                   break;
                    case "Lucida Handwriting":
                   File3.changefont(command); 
                   break;
                    case "Arial":
                   File3.changefont(command); 
                   break;
                    case "Times New Roman":
                   File3.changefont(command); 
                   break;
                    case "Default":
                    File4.color(1);
                    break;
                    case "Hackers Theme":
                    File4.color(2);
                    break;
                    case "Old Priest Theme":
                    File4.color(3);
                    break;
                    case "Dark Theme":
                    File4.color(4);
                    break;
                    case "Light Theme":
                    File4.color(5);
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            System.out.println("Unexpected error happened.");
        }
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}

public class Notepad {
    public static void main(String[] args) {
        try {
            pad foo = new pad();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
