package uth.edu.homestay_campingbooking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uth.edu.homestay_campingbooking.repositories.ServiceRepository;

import java.util.List;

@Service
public class ServiceService implements IServiceService {
    @Autowired
    private ServiceRepository serviceRepository;
    @Override
    public List<uth.edu.homestay_campingbooking.models.Service> findAllServices() {
        return serviceRepository.findAllServices();
    }

    @Override
    public uth.edu.homestay_campingbooking.models.Service findById(Long id) {
        return serviceRepository.findById(id);
    }

    @Override
    public uth.edu.homestay_campingbooking.models.Service findByName(String serviceName) {
        return serviceRepository.findByName(serviceName);
    }

    @Override
    public void saveService(uth.edu.homestay_campingbooking.models.Service service) {
        serviceRepository.saveService(service);
    }

    @Override
    public void updateService(Long id, uth.edu.homestay_campingbooking.models.Service service) {
        serviceRepository.updateService(id, service);
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteService(id);
    }

    @Override
    public List<uth.edu.homestay_campingbooking.models.Service> checkService(Long userId) {
        return serviceRepository.checkService(userId);
    }
}
