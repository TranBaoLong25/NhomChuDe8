package uth.edu.homestay_campingbooking.services;

import uth.edu.homestay_campingbooking.models.Service;

import java.util.List;

public interface IServiceService {
    List<Service> findAllServices();
    Service findById(Long id);
    Service findByName(String serviceName);
    void saveService(Service service);
    void updateService(Long id, Service service);
    void deleteService(Long id);
    List<Service> checkService(Long userId);
}
