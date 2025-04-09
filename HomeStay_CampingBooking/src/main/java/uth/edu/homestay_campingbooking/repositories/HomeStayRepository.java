package uth.edu.homestay_campingbooking.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import uth.edu.homestay_campingbooking.exception.AppException;
import uth.edu.homestay_campingbooking.exception.ErrorCode;
import uth.edu.homestay_campingbooking.models.HomeStay;
import uth.edu.homestay_campingbooking.models.Location;
import uth.edu.homestay_campingbooking.models.RoomType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Override
    public List<HomeStay> searchHomeStays(RoomType roomType, Location location, Double minPrice, Double maxPrice) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<HomeStay> cq = cb.createQuery(HomeStay.class);
        Root<HomeStay> root = cq.from(HomeStay.class);
        List<Predicate> predicates = new ArrayList<>();

        if (roomType != null) {
            predicates.add(cb.equal(root.get("roomType"), roomType));
        }
        if (location != null) {
            predicates.add(cb.equal(root.get("location"), location));
        }
        if (minPrice != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("roomPrice"), minPrice));
        }
        if (maxPrice != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("roomPrice"), maxPrice));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();
    }

    public String insertHomeStay(RoomType roomType, Location location, double roomPrice, boolean booked, List<MultipartFile> images) {
        String uploadDir = "src/main/resources/static/uploads";
        Path uploadPath = Paths.get(uploadDir);
        try {
            if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);
        } catch (IOException e) {
            return "Lỗi khi tạo thư mục lưu ảnh!";
        }

        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile image : images) {
            if (!image.isEmpty()) {
                String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
                Path imagePath = uploadPath.resolve(fileName);
                try {
                    Files.copy(image.getInputStream(), imagePath);
                    imageUrls.add("/uploads/" + fileName);
                } catch (IOException e) {
                    return "Lỗi khi lưu ảnh!";
                }
            }
        }

        HomeStay newHomeStay = new HomeStay(null, roomType, location, roomPrice, booked, imageUrls);
        createHomeStay(newHomeStay);
        return "Thêm Homestay thành công!";
    }

    public String updateHomeStay(Long id, RoomType roomType, Location location, double roomPrice, boolean booked, List<MultipartFile> images) {
        HomeStay existingHomeStay = findHomeStay(id);
        if (existingHomeStay == null) return "Homestay không tồn tại!";

        List<String> imageUrls = new ArrayList<>(existingHomeStay.getImageUrls()); // Giữ ảnh cũ nếu không upload ảnh mới

        if (images != null && !images.isEmpty()) {
            String uploadDir = "src/main/resources/static/uploads";
            Path uploadPath = Paths.get(uploadDir);

            try {
                if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);
            } catch (IOException e) {
                return "Lỗi khi tạo thư mục lưu ảnh!";
            }

            imageUrls.clear(); // Xóa ảnh cũ nếu có ảnh mới
            for (MultipartFile image : images) {
                if (!image.isEmpty()) {
                    String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
                    Path imagePath = uploadPath.resolve(fileName);
                    try {
                        Files.copy(image.getInputStream(), imagePath);
                        imageUrls.add("/uploads/" + fileName);
                    } catch (IOException e) {
                        return "Lỗi khi lưu ảnh!";
                    }
                }
            }
        }

        existingHomeStay.setRoomType(roomType);
        existingHomeStay.setLocation(location);
        existingHomeStay.setRoomPrice(roomPrice);
        existingHomeStay.setBooked(booked);
        existingHomeStay.setImageUrls(imageUrls);
        updateHomeStay(id, existingHomeStay);
        return "Cập nhật Homestay thành công!";
    }
}