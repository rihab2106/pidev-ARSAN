
import com.trophy.dao.userdao;
import com.trophy.entity.Users;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lenovo
 */
public class Main {
     public static void main(String[] args){
       
         
        
         Users o = new Users(2,"nerbenamara","gd ff","bengmail.com","11111");
         
         userdao ud=new userdao();
         ud.getInstance().insert(o);
                  

       

    }
}
