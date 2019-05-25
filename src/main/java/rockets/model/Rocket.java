package rockets.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.CompositeIndex;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;
import static org.neo4j.ogm.annotation.Relationship.INCOMING;
import static org.neo4j.ogm.annotation.Relationship.OUTGOING;

@NodeEntity
@CompositeIndex(properties = {"name", "country", "manufacturer"}, unique = true)
public class Rocket extends Entity {
    @Property(name="name")
    private String name;

    @Property(name="country")
    private String country;

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setManufacturer(LaunchServiceProvider manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Relationship(type = "MANUFACTURES", direction = INCOMING)
    private LaunchServiceProvider manufacturer;

    @Property(name="massToLEO")
    private String massToLEO;

    @Property(name="massToGTO")
    private String massToGTO;

    @Property(name="massToOther")
    private String massToOther;

    @Property(name="firstYearFlight")
    private int firstYearFlight;

    @Property(name="lastYearFlight")
    private int latestYearFlight;

    @Relationship(type = "PROVIDES", direction = OUTGOING)
    @JsonIgnore
    private Set<Launch> launches;

    private RocketFamily family;

    public Rocket() {
        super();
    }

    public Rocket(String name, String country, LaunchServiceProvider manufacturer) {
        notNull(name);
        notNull(country);
        notNull(manufacturer);

        this.name = name;
        this.country = country;
        this.manufacturer = manufacturer;

        this.launches = new LinkedHashSet<>();
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public LaunchServiceProvider getManufacturer() {
        return manufacturer;
    }

    public String getMassToLEO() {
        return massToLEO;
    }

    public String getMassToGTO() {
        return massToGTO;
    }

    public String getMassToOther() {
        return massToOther;
    }

    public int getFirstYearFlight() {
        return firstYearFlight;
    }

    public int getLatestYearFlight() {
        return latestYearFlight;
    }

    public void setMassToLEO(String massToLEO) {

        notBlank(massToLEO, "masstoleo cannot be null or empty");
        RocketValidation.massToShouldBeNumbers(massToLEO);
        this.massToLEO = massToLEO;

    }

    public void setMassToGTO(String massToGTO) {

        notBlank(massToGTO, "masstogto cannot be null or empty");
        RocketValidation.massToShouldBeNumbers(massToGTO);
        this.massToGTO = massToGTO;
    }

    public void setMassToOther(String massToOther) {

        notBlank(massToOther, "masstoother cannot be null or empty");
        RocketValidation.massToShouldBeNumbers(massToOther);
        this.massToOther = massToOther;
    }

    public void setLaunchServiceProvider(LaunchServiceProvider manufacturer){
        this.manufacturer = manufacturer;
    }

    public void setFamily(RocketFamily family) {
        notNull(family);
        this.family = family;
    }


    public void setFirstYearFlight(int firstYearFlight) {
        this.firstYearFlight = firstYearFlight;
    }

    public void setLatestYearFlight(int latestYearFlight) {
        this.latestYearFlight = latestYearFlight;
    }

    public Set<Launch> getLaunches() {
        return launches;
    }

    public void setLaunches(Set<Launch> launches) {
        this.launches = launches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rocket rocket = (Rocket) o;
        return Objects.equals(name, rocket.name) &&
                Objects.equals(country, rocket.country) &&
                Objects.equals(manufacturer, rocket.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, manufacturer);
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", massToLEO='" + massToLEO + '\'' +
                ", massToGTO='" + massToGTO + '\'' +
                ", massToOther='" + massToOther + '\'' +
                ", firstYearFlight=" + firstYearFlight +
                ", latestYearFlight=" + latestYearFlight +
                '}';
    }
}
