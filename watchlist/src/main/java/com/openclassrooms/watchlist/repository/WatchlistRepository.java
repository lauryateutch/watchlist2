package com.openclassrooms.watchlist.repository;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
public class WatchlistRepository {
    private List<WatchlistItem> watchlistItemList= new ArrayList<WatchlistItem>();
    private static int index= 1;

    public List<WatchlistItem> getList(){
        return watchlistItemList;
    }

    public void addItem(WatchlistItem watchlistItem){
        watchlistItem.setId(index++);
        watchlistItemList.add(watchlistItem);
    }

    public WatchlistItem findById(Integer id){
        for (WatchlistItem watchlistItem:watchlistItemList){
            if(watchlistItem.getId().equals(id)){
                return watchlistItem;
            }
        }
        return null;
    }

    public WatchlistItem findByTitle(String title){
        for(WatchlistItem watchlistItem: watchlistItemList){
            if(watchlistItem.getTitle().equals(title)){
                return watchlistItem;
            }
        }
        return null;
    }

}
