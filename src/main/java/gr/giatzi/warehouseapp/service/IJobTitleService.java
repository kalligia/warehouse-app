package gr.giatzi.warehouseapp.service;

import gr.giatzi.warehouseapp.model.static_data.JobTitle;

import java.util.List;

public interface IJobTitleService {
    List<JobTitle> findAllJobTitles();
}
