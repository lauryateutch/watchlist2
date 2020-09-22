package com.openclassrooms.watchlist;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.repository.WatchlistRepository;
import com.openclassrooms.watchlist.service.MovieRatingService;
import com.openclassrooms.watchlist.service.WatchlistService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class WatchlistServiceTest {
    @InjectMocks
    private WatchlistService watchlistService;
    @Mock
    private WatchlistRepository watchlistRepositoryMock;
    @Mock
    private MovieRatingService movieRatingServiceMock;

    public void testGetWatchlistItemsReturnsAllItemsFromRepository() {
        //arrange
        WatchlistItem item1 = new WatchlistItem
                ("star", "5", "Medium", "", 1);
        WatchlistItem item2 = new WatchlistItem
                ("Star Treck", "8.8", "M", "", 2);

        List<WatchlistItem> mockItems = Arrays.asList(item1, item2);


        when(watchlistRepositoryMock.getList()).thenReturn(mockItems);

        //Act
        List<WatchlistItem> result = watchlistService.getWatchlistItems();

        //Assert
        assertTrue(result.size() == 2);
        assertTrue(result.get(0).getTitle().equals("star"));
        assertTrue(result.get(0).getTitle().equals("Star Treck"));}

        public void testGetwatchlistItemsRatingFormOmdbServiceOverrideTheValueInItems () {
            //Arange
            WatchlistItem items1 = new WatchlistItem("Star Wars", "7.7", "M", "", 1);
            List<WatchlistItem> mockItem = Arrays.asList(items1);
            when(watchlistRepositoryMock.getList()).thenReturn(mockItem);
            when(movieRatingServiceMock.getMovieRating(any(String.class))).thenReturn("10");

            //Act
            List<WatchlistItem> results = watchlistService.getWatchlistItems();

            //Assert
            assertTrue(results.get(0).getRating().equals("10"));


        }

        public void returnTestGetwatchlistSize(){
        //arrange
            WatchlistItem item1 = new WatchlistItem
                    ("star", "5", "Medium", "", 1);
            WatchlistItem item2 = new WatchlistItem
                    ("Star Treck", "8.8", "M", "", 2);
            List<WatchlistItem> mockItems = Arrays.asList(item1, item2);

            //Act
            Integer size=watchlistService.getWatchlistItemSize();

            //Assert
            assertTrue(size==mockItems.size());

        }
    }

