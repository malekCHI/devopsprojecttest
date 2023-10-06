package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.services.Iservices.IActivitySector;

import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ActivitySectorImplTest {
    @Mock
    private IActivitySector iActivitySector;
    @Test
    void retrieveAllActivitySectors() {
        List<ActivitySector> listActivitySector = iActivitySector.retrieveAllActivitySectors();
        Assertions.assertEquals(0, listActivitySector.size());
    }
}