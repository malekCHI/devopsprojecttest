package tn.esprit.devops_project.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.DevOps_ProjectSpringBootApplication;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = DevOps_ProjectSpringBootApplication.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class activitySectorImplTestMockito {

    @Mock
    //Create a mock
    private ActivitySectorRepository activitySectorRepository;
    //IActivitySector iActivitySector = Mockito.mock(IActivitySector.class);
    @InjectMocks
    //Inject the mocks as dependencies into ActivitySectorImpl
    private ActivitySectorImpl activitySectorimpl;
    private AutoCloseable closeable;

    @BeforeEach
    void initService() {
        System.out.println("Before each test: Initializing service...");
        closeable = MockitoAnnotations.openMocks(this);
        activitySectorimpl =new ActivitySectorImpl(activitySectorRepository);
    }
    @AfterEach
    void closeService() throws Exception {
        System.out.println("After each test: Closing service...");
        closeable.close();
    }
    @Test
    @DisplayName("TEST retrieve All ActivitySectors")
    void retrieveAllActivitySectors() {
        System.out.println("testing with mock...");
        List<ActivitySector> listActivitySector = Arrays.asList(new ActivitySector());
        Mockito.when(activitySectorRepository.findAll()).thenReturn(listActivitySector);
        List<ActivitySector> retrievedActivitySector = activitySectorimpl.retrieveAllActivitySectors();
        Assertions.assertEquals(listActivitySector.size(),retrievedActivitySector.size());
        Assertions.assertNotNull(listActivitySector);
        Assertions.assertTrue(retrievedActivitySector.contains(listActivitySector.get(0)));
    }

    @Test
    @DisplayName("TEST add ActivitySector")
    void addActivitySector() {
        ActivitySector activitySector = ActivitySector.builder()
                .codeSecteurActivite("Db235687654D")
                .libelleSecteurActivite("helloooo").build();
        Mockito.when(activitySectorRepository.save(Mockito.any(ActivitySector.class))).thenReturn(activitySector);
        ActivitySector savedAct = activitySectorimpl.addActivitySector(activitySector);
        Assertions.assertSame(activitySector, savedAct);
        Assertions.assertEquals("Db235687654D", savedAct.getCodeSecteurActivite());
        Assertions.assertEquals("helloooo", savedAct.getLibelleSecteurActivite());
        Assertions.assertNotNull(savedAct);
        String codeSecteurActivite = activitySector.getCodeSecteurActivite();
        Assertions.assertTrue(codeSecteurActivite.matches("^D.*[1-9].*D$"));
        System.out.println("Activity test added with success ...");
    }

    @Test
    @DisplayName("TEST delete ActivitySector")
    void deleteActivitySector() {
        ActivitySectorRepository activitySectorRepository = Mockito.mock(ActivitySectorRepository.class);
        Long idToDelete = 1L;
        Mockito.doNothing().when(activitySectorRepository).deleteById(idToDelete);
        ActivitySectorImpl activitySectorImpl = new ActivitySectorImpl(activitySectorRepository);
        activitySectorImpl.deleteActivitySector(idToDelete);
        Mockito.verify(activitySectorRepository, Mockito.times(1)).deleteById(idToDelete);
        System.out.println("Activity test deleted with success ...");

    }

    @Test
    @DisplayName("TEST update ActivitySector")
    void updateActivitySector() {
        ActivitySectorRepository activitySectorRepository = Mockito.mock(ActivitySectorRepository.class);
        //Set up the mock to return an updated ActivitySector when save is called
        ActivitySector updatedActivitySector = new ActivitySector(); // Create an updated version
        updatedActivitySector.setIdSecteurActivite(1L);
        Mockito.when(activitySectorRepository.save(updatedActivitySector)).thenReturn(updatedActivitySector);
        //Call the updateActivitySector method
        ActivitySectorImpl activitySectorImpl = new ActivitySectorImpl(activitySectorRepository);
        ActivitySector result = activitySectorImpl.updateActivitySector(updatedActivitySector);
        Mockito.verify(activitySectorRepository, Mockito.times(1)).save(updatedActivitySector);
        Assertions.assertEquals(updatedActivitySector, result);
        Assertions.assertEquals(1L, result.getIdSecteurActivite());
        Assertions.assertNotNull(result);
        System.out.println("Activity test updated with success ...");

    }

    @Test
    @DisplayName("TEST get by id ActivitySector")
    void retrieveActivitySector() {
        ActivitySectorRepository activitySectorRepository = Mockito.mock(ActivitySectorRepository.class);
        Long idToget = 1L;
        ActivitySector activitySector = new ActivitySector(); // Create a sample activity sector
        Mockito.when(activitySectorRepository.findById(idToget)).thenReturn(Optional.of(activitySector));
        ActivitySectorImpl activitySectorImpl = new ActivitySectorImpl(activitySectorRepository);
        Optional<ActivitySector> result = Optional.ofNullable(activitySectorImpl.retrieveActivitySector(idToget));
        Mockito.verify(activitySectorRepository, Mockito.times(1)).findById(idToget);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(activitySector, result.get());
        Assertions.assertNotNull(result);
        System.out.println("Activity test get it by id  with success ...");
    }



}