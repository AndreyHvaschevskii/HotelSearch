package com.hotelsearch.dao;

import com.hotelsearch.entity.Country;

public interface CountryStorage {
    Country findHotelByCountry(String name);

}
