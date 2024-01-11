import java.awt.Font;

public class Format_fuction {
    pad p;
    int size = 16;
    String SelectedFont="Calibri";
    //    Font f1 = new Font("Calibri",Font.PLAIN,size); 
    //    Font  f2 = new Font("Cascadia Code",Font.PLAIN,size); 
    //    Font f3 = new Font("Lucida Handwriting",Font.PLAIN,size); 
    //    Font f4 = new Font("Arial",Font.PLAIN,size); 
    //    Font f5 = new Font("Times New Roman",Font.PLAIN,size); 
       Font f1,f2,f3,f4,f5;
    public Format_fuction(pad pad) {
    this.p =pad;
    }
    public void wordwrap(){
        if(p.wordwrap==false){
            p.wordwrap=true;
            p.t.setLineWrap(true);
            p.t.setWrapStyleWord(true);
            p.Wordwrap.setText("Wordwrap on");
        }else{
            p.wordwrap=false;
            p.t.setLineWrap(false);
            p.t.setWrapStyleWord(false);
            p.Wordwrap.setText("Wordwrap off");
        }
    }
        public void setfont(){
            
        } 
    public void fontsize(int s){
        this.size = s;
        f1 = new Font("Calibri",Font.PLAIN,s); 
         f2 = new Font("Cascadia Code",Font.PLAIN,s); 
        f3 = new Font("Lucida Handwriting",Font.PLAIN,s); 
        f4 = new Font("Arial",Font.PLAIN,s); 
        f5 = new Font("Times New Roman",Font.PLAIN,s); 
        changefont(SelectedFont);
    }
    public void changefont(String Font){
        SelectedFont=Font;
        switch (Font) {
            case "Calibri":
                p.t.setFont(f1);
                break;
            case "Cascadia Code":
                p.t.setFont(f2);
                break;
                case "Lucida Handwriting":
                p.t.setFont(f3);
                break;
                case "Arial":
                p.t.setFont(f4);
                break;
                case "Times New Roman":
                p.t.setFont(f5);
                break;
            default:
                break;
        }
    }
    
}
