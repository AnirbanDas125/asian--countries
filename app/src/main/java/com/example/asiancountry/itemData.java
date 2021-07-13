package com.example.asiancountry;

public class itemData {
    private String countryName,countryCapital,countryFlag,countryRegion,countrySubRegion;
    private int countryPopulation;

    public itemData(){

    }
    public itemData(String countryName,String countryCapital,String countryFlag,
                    String countryRegion,String SubRegion,int countryPopulation){
        this.countryName = countryName;
        this.countryCapital = countryCapital;
        this.countryFlag = countryFlag;
        this.countryRegion = countryRegion;
        this.countrySubRegion = SubRegion;
        this.countryPopulation = countryPopulation;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public String getCountrySubRegion() {
        return countrySubRegion;
    }

    public int getCountryPopulation() {
        return countryPopulation;
    }
}
