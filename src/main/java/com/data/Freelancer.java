package com.data;

public class Freelancer {
    // It's a data class for store the info about freelancers

    private String name;
    private String title;
    private String rate;
    private String earnings;
    private String score;
    private String location;
    private String description;
    private String specialization;
    private String skills;

// It's a constructor for creating a new freelancer

    public Freelancer(String namef, String titlef, String ratef, String earningsf, String scoref, String locationf, String descriptionf,
                      String specializationf, String skillsf){
        this.name=namef;
        this.title=titlef;
        this.rate=ratef;
        this.earnings=earningsf;
        this.score=scoref;
        this.location=locationf;
        this.description=descriptionf;
        this.specialization=specializationf;
        this.skills=skillsf;
    }

//Getters for getting freelancers info

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getRate() {
        return rate;
    }

    public String getEarnings() {
        return earnings;
    }

    public String getScore() {
        return score;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getSkills() {
        return skills;
    }

}
