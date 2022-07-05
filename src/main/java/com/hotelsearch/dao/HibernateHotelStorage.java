package com.hotelsearch.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateHotelStorage {

    @Autowired
    private SessionFactory sessionFactory;

    /*@Override
    public List<Hotel> findHotelByCountry(Country country) {
        Session session = sessionFactory.openSession();
        List<Hotel> allHotelByCountry = session
                .createQuery("from Hotel where country = :country", Hotel.class)
                .setParameter("country", country)
                .getResultList();
        session.close();
        return allHotelByCountry;
    }*/
}
