public class Edit_function {
   pad p ;
   
   Edit_function(pad p){
    this.p = p;
   }
    public void undo(){
        p.un.undo();
    }
    public void redo(){
        p.un.redo();
    }
}
