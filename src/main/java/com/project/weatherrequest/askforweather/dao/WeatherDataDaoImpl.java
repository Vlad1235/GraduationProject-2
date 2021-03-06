package com.project.weatherrequest.askforweather.dao;

import com.project.weatherrequest.askforweather.AskforweatherApplication;
import com.project.weatherrequest.askforweather.entity.WeatherData;
import com.project.weatherrequest.askforweather.restclient.WeatherInsertion;
import com.project.weatherrequest.askforweather.restclient.openweathermap.Weather;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class WeatherDataDaoImpl implements WeatherDataDao {

    private EntityManager theEntityManager;

    @Autowired
    public WeatherDataDaoImpl(EntityManager theEntityManager) {
        this.theEntityManager = theEntityManager;
    }

    public WeatherDataDaoImpl() {
    }

    @Override
    public List<WeatherData> getInformation() {

        Session currentSession = theEntityManager.unwrap(Session.class);
        Query<WeatherData> theQuery = currentSession.createQuery("FROM WeatherData",WeatherData.class);
        List<WeatherData> dataResult = theQuery.getResultList();

        return dataResult;
    }

    @Override
    public WeatherData findById(int theId) {

        Session currentSession = theEntityManager.unwrap(Session.class);
        WeatherData theWeatherData =currentSession.get(WeatherData.class,theId);

        return theWeatherData;
    }

    @Override
    @Transactional
    public void save(WeatherData objestToSave) {
        Session currentSession = theEntityManager.unwrap(Session.class);
        currentSession.save(objestToSave);
    }
}
