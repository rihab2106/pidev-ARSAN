
import com.trophy.dao.groupsdao;
import com.trophy.dao.user_groupsdao;
import com.trophy.dao.userdao;
import com.trophy.entity.Groups;
import com.trophy.entity.users;
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
       //users o1=new users(20,"rgh","erty","1236");
        Groups g =new Groups(6,"tas","");
         Groups g2 =new Groups(2,"a","laaa");
        users_groups ug =new users_groups(111,6);
        
         //users o = new users(20,"nermine","kkkkk","","123654");
         users o1 = new users(47,"ttt","gdff","bengmail.com");
         groupsdao go=new groupsdao();
         userdao ud=new userdao();
         //user_groupsdao.getInstance().insert(o1, g);//
         
         
         userdao.getInstance().insert(o1);
         //user_groupsdao.getInstance().insert(o, g);
         //groupsdao.getInstance().delete(g);//
         //o.setFULL_NAME("update");//
         //g.setNAME("kkk");//
          // user_groupsdao.getInstance().displayemail(ug);
        // boolean update =groupsdao.getInstance().update(g);
         
             
           
          //user_groupsdao.getInstance().deleteuser(ug);

       

    }
}
