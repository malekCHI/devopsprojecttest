package tn.esprit.devops_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "activity_sector")
public class ActivitySector  implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long idSecteurActivite;
        String codeSecteurActivite;
        String libelleSecteurActivite;
        @ManyToMany(mappedBy="activitySectors")
        @JsonIgnore
        private Set<Supplier> suppliers;

}