package com.openclassrooms.watchlist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WatchlistController {

    private List<WatchlistItem> watchlistItems= new ArrayList<WatchlistItem>();//create a list of watchlistItems
    private static int index= 1;//autogenerate the id watchlistitem
    @GetMapping("/watchlist")
    public ModelAndView getWachtlist(){
         watchlistItems.clear();//our watchlistItems should not handle old values
        watchlistItems.add(new WatchlistItem("La Reine des Neiges","8.5","MEDIUM","Let's it go",index));
        watchlistItems.add(new WatchlistItem("So close","4","LOW","A LOT OF ACTIONS AND FIGHTS",index));
        watchlistItems.add(new WatchlistItem("Games of thrones","9","HGHT","THE TRAILER WAS VERY AWSOME",index));
        watchlistItems.add(new WatchlistItem("A STAR IS BORNT","7","MEDIUM","VERY INTERSTING",index));

        String viewName="watchlist";
        Map<String,Object> model = new HashMap<String, Object>();
        model.put("watchlistItems",watchlistItems);
        model.put ("numberOfMovies", watchlistItems.size());
        return new ModelAndView(viewName,model);

    }
}
