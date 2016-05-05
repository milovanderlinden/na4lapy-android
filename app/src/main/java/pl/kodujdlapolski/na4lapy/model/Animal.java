package pl.kodujdlapolski.na4lapy.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.io.Serializable;

import pl.kodujdlapolski.na4lapy.model.type.ActivityAnimal;
import pl.kodujdlapolski.na4lapy.model.type.Gender;
import pl.kodujdlapolski.na4lapy.model.type.Size;
import pl.kodujdlapolski.na4lapy.model.type.Species;
import pl.kodujdlapolski.na4lapy.model.type.Training;
import pl.kodujdlapolski.na4lapy.model.type.Vaccination;

@DatabaseTable(tableName = "animals")
public class Animal extends BaseEntity implements Serializable {

    public final static String COLUMN_NAME_FAVOURITE = "favourite";

    @DatabaseField(foreign = true)
    private Shelter shelter;

    @DatabaseField private String name;
    @DatabaseField private String race;
    @DatabaseField private String description;
    @DatabaseField private Long birthDate;
    @DatabaseField private Long admittanceDate;
    @DatabaseField private String chipId;
    @DatabaseField private Boolean sterilization;

    @DatabaseField private Species species;
    @DatabaseField private Gender gender;
    @DatabaseField private Size size;
    @DatabaseField private ActivityAnimal activity;
    @DatabaseField private Vaccination vaccination;
    @DatabaseField private Training training;

    @ForeignCollectionField(eager = false)
    private Collection<Photo> photos;

    @DatabaseField(columnName = Animal.COLUMN_NAME_FAVOURITE)
    private Boolean favourite;

    public Animal() {
        // needed by ormlite
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public Long getAdmittanceDate() {
        return admittanceDate;
    }

    public void setAdmittanceDate(Long admittanceDate) {
        this.admittanceDate = admittanceDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public ActivityAnimal getActivity() {
        return activity;
    }

    public void setActivity(ActivityAnimal activity) {
        this.activity = activity;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Boolean getSterilization() {
        return sterilization;
    }

    public void setSterilization(Boolean sterilization) {
        this.sterilization = sterilization;
    }

    public String getChipId() {
        return chipId;
    }

    public void setChipId(String chipId) {
        this.chipId = chipId;
    }

    public Collection<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ForeignCollection<Photo> photos) {
        this.photos = photos;
    }

    public Boolean isFavourite() {
        return favourite != null && favourite;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vaccination getVaccination() {
        return vaccination;
    }

    public void setVaccination(Vaccination vaccination) {
        this.vaccination = vaccination;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "shelter=" + shelter +
                ", admittanceDate=" + admittanceDate +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", species=" + species +
                ", gender=" + gender +
                ", size=" + size +
                ", race='" + race + '\'' +
                ", activity=" + activity +
                ", vaccination=" + vaccination +
                ", training=" + training +
                ", description='" + description + '\'' +
                ", chipId='" + chipId + '\'' +
                ", sterilization=" + sterilization +
                ", photos=" + photos +
                ", favourite=" + favourite +
                '}';
    }
}
