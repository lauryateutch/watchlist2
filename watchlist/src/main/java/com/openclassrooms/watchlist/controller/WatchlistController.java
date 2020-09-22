package com.openclassrooms.watchlist.controller;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.exception.DuplicateTitleException;
import com.openclassrooms.watchlist.repository.WatchlistRepository;
import com.openclassrooms.watchlist.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

@Controller
public class WatchlistController {

	/*private List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();// create a list of watchlistItems
	private static int index = 1;// autogenerate the id watchlistitem*/

	private WatchlistService watchlistService;
	@Autowired
	public WatchlistController(WatchlistService watchlistService) {
		this.watchlistService = watchlistService;
	}

	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchlistItemForm(@Valid @ModelAttribute("watchlistItem") WatchlistItem watchlistItem, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return new ModelAndView("watchlistItemForm");
		}

		try{
		    watchlistService.addOrUpdateWatchlistItem(watchlistItem);
        }catch (DuplicateTitleException e){
		    bindingResult.rejectValue("title","",
                    "this title already exists on your watchlist");
		    return new ModelAndView("watchlistItemForm");
        }
		

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/watchlist");
		return new ModelAndView(redirectView);
	}

//cette méthode affiche le formulaire qui permet d'ajouter un film
	@GetMapping("/watchlistItemForm")
	public ModelAndView ShowWatchlistItemForm(@RequestParam(required = false) Integer id) {

		String viewName = "watchlistItemForm";
		Map<String, Object> model = new HashMap<String, Object>();
		WatchlistItem watchlistItem = watchlistService.findWatchlistItemById(id);

		if (watchlistItem == null) {
			model.put("watchlistItem", new WatchlistItem());
		} else {
			model.put("watchlistItem", watchlistItem);

		}
		return new ModelAndView(viewName, model);

	}



//méthode qui permets d'afficher les données de manière dynamiques
	@GetMapping("/watchlist")
	public ModelAndView getWachtlist() {
		/*
		 * watchlistItems.clear();//our watchlistItems should not handle old values
		 * watchlistItems.add(new
		 * WatchlistItem("La Reine des Neiges","8.5","MEDIUM","Let's it go",index));
		 * watchlistItems.add(new
		 * WatchlistItem("So close","4","LOW","A LOT OF ACTIONS AND FIGHTS",index));
		 * watchlistItems.add(new
		 * WatchlistItem("Games of thrones","9","HGHT","THE TRAILER WAS VERY AWSOME"
		 * ,index)); watchlistItems.add(new
		 * WatchlistItem("A STAR IS BORNT","7","MEDIUM","VERY INTERSTING",index));
		 */

		String viewName = "watchlist";
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("watchlistItems", watchlistService.getWatchlistItems());
		model.put("numberOfMovies", watchlistService.getWatchlistItemSize());
		return new ModelAndView(viewName, model);

	}
}
