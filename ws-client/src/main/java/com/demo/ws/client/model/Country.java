package com.demo.ws.client.model;

public class Country {
    private final String name;
    private final String capital;
    private final String currency;
    private final int population;

    public Country(String name, String capital, String currency, int population) {
        this.name = name;
        this.capital = capital;
        this.currency = currency;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getCurrency() {
        return currency;
    }

    public int getPopulation() {
        return population;
    }
}
