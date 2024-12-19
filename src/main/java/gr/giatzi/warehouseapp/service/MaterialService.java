package gr.giatzi.warehouseapp.service;

import gr.giatzi.warehouseapp.model.static_data.Material;
import gr.giatzi.warehouseapp.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService implements IMaterialService {
    private final MaterialRepository materialRepository;

    @Override
    public List<Material> findAllMaterials() {
        return  materialRepository.findAll();
    }
}
