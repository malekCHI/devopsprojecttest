package tn.esprit.devops_project.services;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.devops_project.DevOps_ProjectSpringBootApplication;
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
        Assertions.assertEquals(3, listActivitySector.size());
    }


    @Test
    @DisplayName("Testing add ActivitySector")
    void addActivitySector() {
        ActivitySector activitySector = new ActivitySector().builder()
                .codeSecteurActivite("D1D")
                .libelleSecteurActivite("hellooo").build();
        // Appel méthode à tester depuis le service
        ActivitySector ActivitySectorResult = activitySectorimpl.addActivitySector(activitySector);
        assertThat(activitySector).isNotNull();
        assertThat(activitySector.getCodeSecteurActivite()).isNotEmpty().isEqualTo("D1D");
        assertThat(ActivitySectorResult.getCodeSecteurActivite()).startsWith("D").endsWith("D").containsPattern("^D.*[1-9].*D");
        ActivitySector ActivitySectorFromDB = activitySectorRepository.findById(ActivitySectorResult.getIdSecteurActivite()).orElse(null);
        assertThat(ActivitySectorFromDB).isNotNull();
    }

    @Test
    @DisplayName("Testing updateActivitySector by id")
    void updateActivitySector() {
        // Arrange: Create an initial activity sector to be updated
        ActivitySector initialActivitySector = new ActivitySector().builder()
                .codeSecteurActivite("D1D")
                .libelleSecteurActivite("Initial Value").build();
        ActivitySector savedActivitySector = activitySectorRepository.save(initialActivitySector);

        // Act: Update the activity sector
        savedActivitySector.setLibelleSecteurActivite("Updated Value");
        ActivitySector updatedActivitySector = activitySectorimpl.updateActivitySector(savedActivitySector);

        // Assert: Verify the update
        Assertions.assertNotNull(updatedActivitySector);
        Assertions.assertEquals("Updated Value", updatedActivitySector.getLibelleSecteurActivite());

        // Retrieve from DB to verify update
        ActivitySector activitySectorFromDB = activitySectorRepository.findById(updatedActivitySector.getIdSecteurActivite()).orElse(null);
        Assertions.assertNotNull(activitySectorFromDB);
        Assertions.assertEquals("Updated Value", activitySectorFromDB.getLibelleSecteurActivite());
    }
    @Test
    @DisplayName("Testing deleteActivitySector by id")
    void deleteActivitySector() {
        Long idSecteurActivite = 14L;
        ActivitySector activitySector = activitySectorRepository.findById(idSecteurActivite).orElse(null);
        Assertions.assertNotNull(activitySector, "L'opérateur avec id 14 existe dans la bd");
        activitySectorimpl.deleteActivitySector(idSecteurActivite);
        ActivitySector SecteurActivite = activitySectorRepository.findById(idSecteurActivite).orElse(null);
        Assertions.assertNull(SecteurActivite, "L'opérateur avec id 14 a été supprimé de la bd");
    }


    @Test
    @DisplayName("Testing retrieve by id ActivitySector")
    void retrieveActivitySector() {
        Long idToRetrieve = 8L;
        ActivitySector retrievedActivitySector = activitySectorRepository.findById(idToRetrieve).orElse(null);
        // Assert
        Assertions.assertNotNull(retrievedActivitySector);
        Assertions.assertEquals(idToRetrieve, retrievedActivitySector.getIdSecteurActivite());
    }

}