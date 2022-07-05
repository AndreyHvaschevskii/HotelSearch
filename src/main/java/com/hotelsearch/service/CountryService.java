package com.hotelsearch.service;

import com.hotelsearch.dao.CountryStorage;
import com.hotelsearch.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryService {
    @Autowired
    private final CountryStorage countryStorage;

    public CountryService(CountryStorage countryStorage) {
        this.countryStorage = countryStorage;
    }

    public Country findHotelByCountry(String name) {
        return countryStorage.findHotelByCountry(name);
    }
}
