
import com.trophy.dao.groupsdao;
import com.trophy.dao.user_groupsdao;
import com.trophy.dao.userdao;
import com.trophy.entity.Groups;
import com.trophy.entity.Users;
import com.trophy.entity.users_groups;

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
       
        Groups g =new Groups(6,"tas","");
         Groups g2 =new Groups(2,"a","laaa");
        users_groups ug =new users_groups(111,6);
        
         Users o = new Users(20,"nermine","kkkkk","","123654");
         Users o1 = new Users(47,"ttt","gdff","bengmail.com","11111");
         groupsdao go=new groupsdao();
         userdao ud=new userdao();
         //user_groupsdao.getInstance().insert(o1, g);//
         
         
         //userdao.getInstance().insert(o1);
         //user_groupsdao.getInstance().insert(o, g);
         //groupsdao.getInstance().delete(g);//
         //o.setFULL_NAME("update");//
         //g.setNAME("kkk");//
          // user_groupsdao.getInstance().displayemail(ug);
        // boolean update =groupsdao.getInstance().update(g);
         
             
           
          //user_groupsdao.getInstance().deleteuser(ug);

       

    }
}
