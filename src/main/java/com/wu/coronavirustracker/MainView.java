package com.wu.coronavirustracker;


import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Route("view")
public class MainView extends VerticalLayout {

    private H1 header;
    private ArrayList<Tab> tab;
    private Tabs tabs;
    private Div dataTables;
    private ArrayList<DataTable> countryNumbers;
    private Map<Tab, DataTable> infectedPage;


    public MainView() {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        header = new H1("CoronaVirusTracker");
        tabs = new Tabs();
        tabs.setFlexGrowForEnclosedTabs(1);
        dataTables = new Div();

        initTabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setHeight("300px");
        initDataTables();

        mapData();
        tabs.addSelectedChangeListener(e -> {
            infectedPage.values().forEach(countryNumbers ->
                    countryNumbers.setVisible(false));
            infectedPage.get(tabs.getSelectedTab()).setVisible(true);
        });

        add(header, tabs, dataTables);

    }

    private void mapData() {
        infectedPage = new HashMap<>();
        for(int i = 0; i < Data.getCountryData().size()-2; i++) {
            infectedPage.put(tab.get(i), countryNumbers.get(i));
        }
    }

    private void initDataTables() {

        countryNumbers = new ArrayList<>();
        for(int i = 0; i < Data.getCountryData().size()-2; i++) {
            if (i == 0) {
                countryNumbers.add(new DataTable(
                        "Global", Data.getCountryNumbers(i)));
            }else {
                countryNumbers.add(
                        new DataTable(Data.getCountryName(i), Data.getCountryNumbers(i)));
                countryNumbers.get(i).setVisible(false);
            }
            dataTables.add(countryNumbers.get(i));
        }

    }

    private void initTabs() {
        tab = new ArrayList<>();
        for(int i = 0; i < Data.getCountryData().size()-2; i++) {
            if (i == 0) {
                tab.add(new Tab("Global"));
            }else {
                tab.add(new Tab(Data.getCountryName(i)));
            }
            tabs.add(tab.get(i));
        }

    }

}
