import java.awt.Dimension;
import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class File_function {
    pad pad;
    String FileName;
    String FileAddress;
    String line;

    public File_function(pad pad){
        this.pad = pad;
    }
    public void newFile() {
        pad.t.setText(" ");
        pad.f.setTitle("New");
    }
    public void saveFile(){
        try{
         String name = JOptionPane.showInputDialog(pad.f, "Enter the name of the file");
                    if (name.length() > 0) {
                        File f1 = new File(name + ".txt");
                        f1.createNewFile();
                        FileWriter w = new FileWriter(name + ".txt");
                        w.write(pad.t.getText().toString());
                        w.close();
                    } else {
                        JOptionPane.showMessageDialog(pad.f, "Please enter a file name.");
                    }
    }catch(Exception e){
        JOptionPane.showMessageDialog( pad.f, "Unexpected error happened.");
    }
}
public void open(){
    FileDialog fd = new FileDialog(pad.f, "open", FileDialog.LOAD);
    fd.setVisible(true);
    if(fd.getFile()!=null){
        FileName = fd.getFile();
        FileAddress = fd.getDirectory();
        pad.f.setTitle(FileName);
        try{
        BufferedReader rd = new BufferedReader(new FileReader(FileAddress+FileName));
        pad.t.setText("");
        while((line=rd.readLine())!=null){
            pad.t.append(line+"\n");
        }
        rd.close();
        }catch(Exception e){
            System.out.println(" File Can't be opened.");
        }
    }
}
public void save(){
    if (FileName==null) {
        saveas();
    }else{
            try {
        FileWriter fw = new FileWriter(FileAddress+FileName);
        fw.write(pad.t.getText());
        pad.f.setTitle(FileName);
        fw.close();
    } catch (Exception e) {
        System.out.println("File Cannot Be Saved.");
    }

    }
}
public void saveas(){
    FileDialog fd = new FileDialog(pad.f, "save", FileDialog.SAVE);
    fd.setVisible(true);
    if(fd.getFile()!=null){
        FileName= fd.getFile();
        FileAddress=fd.getDirectory();
        pad.f.setTitle(FileName);
    }
    try {
        FileWriter fw = new FileWriter(FileAddress+FileName);
        fw.write(pad.t.getText());
        fw.close();
    } catch (Exception e) {
        System.out.println("File Cannot Be Saved.");
    }
}
public void exit(){
    System.exit(0);
}
public void setPreferredSize(Dimension dimension) {
}
    
}
