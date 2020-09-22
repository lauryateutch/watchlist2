package com.openclassrooms.watchlist.service;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.exception.DuplicateTitleException;
import com.openclassrooms.watchlist.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {

    private MovieRatingService movieRatingService;

   private WatchlistRepository watchlistRepository;

   @Autowired
   public WatchlistService(WatchlistRepository watchlistRepository,
                           MovieRatingService movieRatingService){
       super();
       this.watchlistRepository= watchlistRepository;
       this.movieRatingService= movieRatingService;
   }
    //appelation api
    public List<WatchlistItem> getWatchlistItems(){
       List<WatchlistItem> watchlistItems= watchlistRepository.getList();
        for(WatchlistItem watchlistItem: watchlistItems){
            String rating= movieRatingService.
                    getMovieRating(watchlistItem.getTitle());
            if (rating!=null){
                watchlistItem.setRating(rating);
            }

        }
      return watchlistRepository.getList();
    }

    public int getWatchlistItemSize(){
        return watchlistRepository.getList().size();
    }

    public WatchlistItem findWatchlistItemById(Integer id){
        return watchlistRepository.findById(id);
    }

    public WatchlistItem findWatchlistItemByTitle(String title){
        return watchlistRepository.findByTitle(title);
    }

    public void addOrUpdateWatchlistItem(WatchlistItem watchlistItem) throws DuplicateTitleException{
        WatchlistItem existingItem= findWatchlistItemById(watchlistItem.getId());

        if (existingItem== null) {
            if
            (watchlistRepository.findByTitle(watchlistItem.getTitle()) != null) {
                throw new DuplicateTitleException();
            }

            watchlistRepository.addItem(watchlistItem);
        } else{
            existingItem.setComment(watchlistItem.getComment());
            existingItem.setPriority(watchlistItem.getPriority());
            existingItem.setRating(watchlistItem.getRating());
            existingItem.setTitle(watchlistItem.getTitle());
        }
    }


}
