package org.exoplanet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Exoplanet {
    @JsonProperty("PlanetIdentifier")
    public String planetIdentifier;
    @JsonProperty("TypeFlag")
    public int typeFlag;
    @JsonProperty("PlanetaryMassJpt")
    public Object planetaryMassJpt;
    @JsonProperty("RadiusJpt")
    public int radiusJpt;
    @JsonProperty("PeriodDays")
    public Object periodDays;
    @JsonProperty("SemiMajorAxisAU")
    public Object semiMajorAxisAU;
    @JsonProperty("Eccentricity")
    public Object eccentricity;
    @JsonProperty("PeriastronDeg")
    public Object periastronDeg;
    @JsonProperty("LongitudeDeg")
    public Object longitudeDeg;
    @JsonProperty("AscendingNodeDeg")
    public Object ascendingNodeDeg;
    @JsonProperty("InclinationDeg")
    public Object inclinationDeg;
    @JsonProperty("SurfaceTempK")
    public Object surfaceTempK;
    @JsonProperty("AgeGyr")
    public Object ageGyr;
    @JsonProperty("DiscoveryMethod")
    public String discoveryMethod;
    @JsonProperty("DiscoveryYear")
    public String discoveryYear;
    @JsonProperty("LastUpdated")
    public String lastUpdated;
    @JsonProperty("RightAscension")
    public String rightAscension;
    @JsonProperty("Declination")
    public String declination;
    @JsonProperty("DistFromSunParsec")
    public Object distFromSunParsec;
    @JsonProperty("HostStarMassSlrMass")
    public Object hostStarMassSlrMass;
    @JsonProperty("HostStarRadiusSlrRad")
    public Object hostStarRadiusSlrRad;
    @JsonProperty("HostStarMetallicity")
    public Object hostStarMetallicity;
    @JsonProperty("HostStarTempK")
    public int hostStarTempK;
    @JsonProperty("HostStarAgeGyr")
    public Object hostStarAgeGyr;
}

