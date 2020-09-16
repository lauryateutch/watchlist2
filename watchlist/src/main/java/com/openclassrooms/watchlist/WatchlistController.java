package com.openclassrooms.watchlist;

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

	private List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();// create a list of watchlistItems
	private static int index = 1;// autogenerate the id watchlistitem

	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchlistItemForm(@Valid @ModelAttribute("watchlistItem") WatchlistItem watchlistItem, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return new ModelAndView("watchlistItemForm");
		}
		
		WatchlistItem existingItem = findWatchlistItemById(watchlistItem.getId());
		if (existingItem == null) {
			watchlistItem.setId(index++);
			watchlistItems.add(watchlistItem);

		} else {
			existingItem.setComment(watchlistItem.getComment());
			existingItem.setPriority(watchlistItem.getPriority());
			existingItem.setRating(watchlistItem.getRating());
			existingItem.setTitle(watchlistItem.getTitle());
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
		WatchlistItem watchlistItem = findWatchlistItemById(id);

		if (watchlistItem == null) {
			model.put("watchlistItem", new WatchlistItem());
		} else {
			model.put("watchlistItem", watchlistItem);

		}
		return new ModelAndView(viewName, model);

	}

	private WatchlistItem findWatchlistItemById(Integer id) {

		for (WatchlistItem watchlistItem : watchlistItems) {
			if (watchlistItem.getId().equals(id)) {
				return watchlistItem;
			}
		}
		return null;
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
		model.put("watchlistItems", watchlistItems);
		model.put("numberOfMovies", watchlistItems.size());
		return new ModelAndView(viewName, model);

	}
}
