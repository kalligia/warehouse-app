package gr.giatzi.warehouseapp.service;

import gr.giatzi.warehouseapp.model.static_data.Material;

import java.util.List;

public interface IMaterialService {
    List<Material> findAllMaterials();
}
