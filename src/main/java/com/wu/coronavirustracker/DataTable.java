package com.wu.coronavirustracker;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.text.DecimalFormat;

public class DataTable extends HorizontalLayout {

    private Div countyLabel;
    private TextField infectedLabel;


    public DataTable(String countryName, int numInfected) {
        countyLabel = new Div();
        countyLabel.setText("Total #Infected in " + countryName + ":");

        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);

        infectedLabel = new TextField();
        infectedLabel.setValue(decimalFormat.format(numInfected));
        infectedLabel.setReadOnly(true);
        setVerticalComponentAlignment(Alignment.CENTER,
                countyLabel, infectedLabel);

        add(countyLabel, infectedLabel);
    }

}
