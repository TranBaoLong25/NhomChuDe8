package uth.edu.homestay_campingbooking.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import uth.edu.homestay_campingbooking.exception.AppException;
import uth.edu.homestay_campingbooking.exception.ErrorCode;
import uth.edu.homestay_campingbooking.models.Service;
import uth.edu.homestay_campingbooking.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class ServiceRepository implements IServiceRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Service> findAllServices() {
        return entityManager.createQuery("from Service", Service.class).getResultList();
    }

    @Override
    public Service findById(Long id) {
        if (entityManager.find(Service.class, id) == null) {
            throw new AppException(ErrorCode.ID_OR_NAME_NOT_EXISTED);
        }
        return entityManager.find(Service.class, id);
    }

    @Override
    public Service findByName(String serviceName) {
        try {
            return entityManager.createQuery("SELECT u FROM Service u WHERE u.serviceName = :serviceName", Service.class)
                    .setParameter("serviceName", serviceName)
                    .getSingleResult();
        } catch (Exception e) {
            throw new AppException(ErrorCode.ID_OR_NAME_NOT_EXISTED);
        }
    }

    @Override
    public void saveService(Service service) {
        List<Service> services = entityManager.createQuery("from Service", Service.class).getResultList();
        for (Service s : services) {
            if (s.getServiceName().equals(service.getServiceName())) {
                throw new AppException(ErrorCode.EXISTED);
            }
        }
        entityManager.persist(service);
    }

    @Override
    public void updateService(Long id, Service newData) {
        Service ser = findById(id);
        if (ser != null) {
            ser.setServiceName(newData.getServiceName());
            ser.setServicePrice(newData.getServicePrice());
            ser.setServiceDescription(newData.getServiceDescription());
            entityManager.merge(ser);
        }else {
            throw new AppException(ErrorCode.ID_OR_NAME_NOT_EXISTED);
        }
    }

    @Override
    public void deleteService(Long id) {
        Service ser = findById(id);
        if (ser == null) {
            throw new AppException(ErrorCode.ID_OR_NAME_NOT_EXISTED);
        }
        entityManager.remove(ser);
    }

    @Override
    public List<Service> checkService(Long userId) {
        List<Service> services = entityManager.createQuery("from Service", Service.class).getResultList();
        List<Service> filteredServices = services.stream()
                .filter(ser -> ser.getUser() != null &&  ser.getUser().getId().equals(userId))
                .collect(Collectors.toList());
        if (filteredServices.isEmpty()) {
            throw new AppException(ErrorCode.ID_OR_NAME_NOT_EXISTED);
        }
        return filteredServices;
    }
}
