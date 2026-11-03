package com.example.practicalfive;

public class TextProcessor {
    public String processMunicipality(String municipality){
        if(municipality == null || municipality.trim().isEmpty()){
            return null;
        }
        String inputTrim = municipality.trim().toLowerCase();
        switch (inputTrim){
            case "beijing":
                return "Beijing";
            case "shanghai":
                return "Shanghai";
            case "chongqing":
                return "Chongqing";
            case "tianjin":
                return "Tianjin";
            default:
                return null;
        }
    }
}
