package gr.aueb.mscis.sample.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorType;

@Entity
@Table(name= "Users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
 columnDefinition = "user",
 discriminatorType = DiscriminatorType.STRING
)
public abstract class User {

}
