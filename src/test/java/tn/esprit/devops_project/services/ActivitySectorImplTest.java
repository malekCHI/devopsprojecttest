package tn.esprit.devops_project.services;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest()
@ActiveProfiles("test")
 class ActivitySectorImplTest {
    @Autowired
    private ActivitySectorRepository activitySectorRepository;
    @Autowired
    private ActivitySectorImpl activitySectorimpl;


    @Test
    @DisplayName("Testing GetAllActivitySectors")
    void GetAllActivitySectors() {
        List<ActivitySector> listActivitySector = activitySectorimpl.retrieveAllActivitySectors();
        Assertions.assertNotNull(listActivitySector);
    }
    @Test
    @DisplayName("Testing add ActivitySector")
    void addActivitySector() {
        ActivitySector activitySector = new ActivitySector().builder()
                .codeSecteurActivite("D1D")
                .libelleSecteurActivite("hellooo").build();
        ActivitySector ActivitySectorResult = activitySectorimpl.addActivitySector(activitySector);
        assertThat(activitySector).isNotNull();
        assertThat(activitySector.getCodeSecteurActivite()).isNotEmpty().isEqualTo("D1D");
        assertThat(ActivitySectorResult.getCodeSecteurActivite()).startsWith("D").endsWith("D").containsPattern("^D.*[1-9].*D");
        ActivitySector ActivitySectorFromDB = activitySectorRepository.findById(ActivitySectorResult.getIdSecteurActivite()).orElse(null);
        assertThat(ActivitySectorFromDB).isNotNull();
    }
    /*@Test
    @DisplayName("Testing updateActivitySector by id")
    void updateActivitySector() {
        ActivitySector activitySector = new ActivitySector().builder()
                .codeSecteurActivite("D13455D")
                .libelleSecteurActivite("malek").build();
        activitySector.getIdSecteurActivite();
        activitySector.setCodeSecteurActivite("UpdatedCode");
        activitySector.setLibelleSecteurActivite("UpdatedLabel");
        ActivitySector updatedSector = activitySectorimpl.updateActivitySector(activitySector);
        Assertions.assertNotNull(updatedSector);
        Assertions.assertEquals("UpdatedLabel", updatedSector.getLibelleSecteurActivite());
        Assertions.assertEquals("UpdatedCode", updatedSector.getCodeSecteurActivite());
        ActivitySector activitySectorFromDB = activitySectorRepository.findById(updatedSector.getIdSecteurActivite()).orElse(null);
        Assertions.assertNotNull(activitySectorFromDB);
    }*/
    @Test
    @DisplayName("Testing deleteActivitySector by id")
    void deleteActivitySector() {
        Long idSecteurActivite = 333L;
        ActivitySector activitySector = activitySectorRepository.findById(idSecteurActivite).orElse(null);
        Assertions.assertNotNull(activitySector, "L'opérateur avec id 333 existe dans la bd");
        activitySectorimpl.deleteActivitySector(idSecteurActivite);
        ActivitySector SecteurActivite = activitySectorRepository.findById(idSecteurActivite).orElse(null);
        Assertions.assertNull(SecteurActivite, "L'opérateur avec id 333 a été supprimé de la bd");
    }


    @Test
    @DisplayName("Testing retrieve by id ActivitySector")
    void retrieveActivitySector() {
        Long idToRetrieve = 222L;
        ActivitySector retrievedActivitySector = activitySectorRepository.findById(idToRetrieve).orElse(null);
        Assertions.assertNotNull(retrievedActivitySector);
        Assertions.assertEquals(idToRetrieve, retrievedActivitySector.getIdSecteurActivite());
    }




}