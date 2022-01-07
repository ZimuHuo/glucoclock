package com.glucoclock.database;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class abstractlog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Column(name = "PatientUid")
    protected UUID patientuid;

}
