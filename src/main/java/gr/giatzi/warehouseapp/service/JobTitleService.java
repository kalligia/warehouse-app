package gr.giatzi.warehouseapp.service;

import gr.giatzi.warehouseapp.model.static_data.JobTitle;
import gr.giatzi.warehouseapp.repository.JobTitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobTitleService implements IJobTitleService {
    private final JobTitleRepository jtRepository;

    @Override
    public List<JobTitle> findAllJobTitles() {
        return  jtRepository.findAll();
    }
}
