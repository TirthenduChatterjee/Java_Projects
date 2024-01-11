import java.awt.Color;

public class Color_Function {
    pad p;
    Color black,green,blue,orange,white,ocuryellow,brown;
    Color_Function(pad p){
        this.p=p;
        black =new Color(4, 13, 15);
        green =new Color(162, 255, 134);
        blue = new Color(5, 59, 80);
        ocuryellow =new Color(218, 192, 163);
        brown= new Color(101, 69, 31);
        orange=Color.ORANGE;
        white=Color.WHITE;
        // p.t.setBackground(blue);
        // p.t.setForeground(orange);
        // p.t.setCaretColor(orange);
    }
    public void color(int i){
        if (i==1) {
            p.t.setBackground(blue);
            p.t.setForeground(orange);
            p.t.setCaretColor(orange);
        }
        else if(i==2){
            p.t.setBackground(black);
            p.t.setForeground(green);
            p.t.setCaretColor(green);
        }
        else if(i==3){
            p.t.setBackground(ocuryellow);
            p.t.setForeground(black);
            p.t.setCaretColor(brown);

        }
        else if(i==4){
            p.t.setBackground(black);
            p.t.setForeground(white);
            p.t.setCaretColor(white);

        }
         else if(i==5){
            p.t.setBackground(white);
            p.t.setForeground(black);
            p.t.setCaretColor(black);

        }


    }
}
