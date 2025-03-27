package uth.edu.homestay_campingbooking.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import uth.edu.homestay_campingbooking.exception.AppException;
import uth.edu.homestay_campingbooking.exception.ErrorCode;
import uth.edu.homestay_campingbooking.models.HomeStay;
import uth.edu.homestay_campingbooking.models.Location;
import uth.edu.homestay_campingbooking.models.RoomType;
import uth.edu.homestay_campingbooking.models.User;

import java.util.ArrayList;
import java.util.List;
@Repository
@Transactional
public class HomeStayRepository implements IHomeStayRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public void createHomeStay(HomeStay hs) {
        em.persist(hs);
    }

    @Override
    public HomeStay findHomeStay(Long id) {
        if (em.find(HomeStay.class, id) == null) {
            throw new AppException(ErrorCode.ID_OR_NAME_NOT_EXISTED);
        }
        return em.find(HomeStay.class, id);
    }

    @Override
    public void updateHomeStay(Long id, HomeStay hs) {
        HomeStay oldHomeStay = findHomeStay(id);
        if (oldHomeStay == null) {
            throw new AppException(ErrorCode.ID_OR_NAME_NOT_EXISTED);
        }
        oldHomeStay.setRoomType(hs.getRoomType());
        oldHomeStay.setRoomPrice(hs.getRoomPrice());
        oldHomeStay.setBooked(hs.isBooked());
        em.merge(oldHomeStay);
    }

    @Override
    public void deleteHomeStay(Long id) {
        HomeStay hs = findHomeStay(id);
        if (hs == null) {
            throw new AppException(ErrorCode.ID_OR_NAME_NOT_EXISTED);
        }
        em.remove(hs);
    }

    @Override
    public List<HomeStay> findAllHomeStays() {
        List<HomeStay> list = em.createQuery("from HomeStay", HomeStay.class).getResultList();
        return (list.isEmpty()) ? new ArrayList<>() : list;
    }

    @Override
    public List<HomeStay> findByType(RoomType type) {
        List<HomeStay> result = new ArrayList<>();
        for (HomeStay hs : findAllHomeStays()) {
            if (hs.getRoomType().equals(type)) {
                result.add(hs);
            }
        }
        if (result.isEmpty()) {
            throw new AppException(ErrorCode.ID_OR_NAME_NOT_EXISTED);
        }
        return result;
    }

    @Override
    public List<HomeStay> findByLocation(Location location) {
        List<HomeStay> result = new ArrayList<>();
        for (HomeStay hs : findAllHomeStays()) {
            if (hs.getLocation().equals(location)) {
                result.add(hs);
            }
        }
        if (result.isEmpty()) {
            throw new AppException(ErrorCode.ID_OR_NAME_NOT_EXISTED);
        }
        return result;
    }

    @Override
    public List<HomeStay> findByPrice(double min, double max) {
        List<HomeStay> ds = new ArrayList<>();
        for (HomeStay hs : findAllHomeStays()) {
            if (hs.getRoomPrice() >= min && hs.getRoomPrice() <= max) {
                ds.add(hs);
            }
        }
        if (ds.isEmpty()) {
            throw new AppException(ErrorCode.ID_OR_NAME_NOT_EXISTED);
        }
        return ds;
    }
}


