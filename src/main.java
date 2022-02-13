import com.trophy.Controller.*;

import    com.trophy.Controller.TrophiesController;
import com.trophy.entity.Category;
import com.trophy.entity.Games;
import com.trophy.entity.Trophies;
public class main {
    public static void main(String[] args) {
        Category c=new Category(1,"Action");
        Games g=new Games(c,"God Of War","Descripion",4.9f);
        Trophies t=new Trophies(g,"sff","sdfsdfdsf  sf","dsfdsf","hard");
        //CategoryController.getInstance().insert(c);
        //GamesController.getInstance().insert(g);
        g.setId_game(4);
        //TrophiesController.getInstance().insert(t);
        //System.out.println(TrophiesController.getInstance().DislayAllList());
        //g.setName("acc");
       // GamesController.getInstance().update(g);
       // System.out.println(GamesController.getInstance().displayById(4));
        t.setTitle("update");
        TrophiesController.getInstance().update(t);
        System.out.println(TrophiesController.getInstance().DisplayAllList());
    }
}

