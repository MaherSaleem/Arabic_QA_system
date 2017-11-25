package com.AQAS.document_retrieval;

import java.util.ArrayList;
import java.util.Arrays;

public final class ConfigD {

    public static final String packagePath = ".\\src\\main\\java\\com\\AQAS\\document_retrieval\\";

    public final class Keys {
        public static final String searchQuery = "searchQuery";
        public static final String searchNumOfPages = "searchNumOfPages";
    }

    public static final boolean VERBOS = true;

    public static final String[] unwantedWebsites = {"youtube", "anotherWebSite"};
    public static final ArrayList<Website> webSites = new ArrayList<Website>(Arrays.asList(
            new Website("doctoori",
                    "http://www.doctoori.net/search/?word=%s&pg=%s",
                    "pg",
                    "a.btn-primary",
                    0,
                    "div.content-main"
            )
            , new Website("webteb",
                    "https://www.webteb.com/search?q=%s&page=%s",
                    "page",//no page
                    "div.gsc-table-cell-thumbnail a.gs-title",
                    1,
                    "div.main-content")
            , new DailyMedicalInfoWebsite("dailymedicalinfo",
                    "http://www.dailymedicalinfo.com/page/%s/?s=%s",
                    "page",//no page
                    ".post-listing h2.post-box-title a",
                    1,
                    "div#main-content")
            , new Google("google",
                    "https://www.googleapis.com/customsearch/v1?key=AIzaSyAKiXvk5uoSL4Vs9a9DzraCdDd6J4E22oY&cx=013036536707430787589:_pqjad5hr1a&q=%s&alt=json&start=%s",
                    "start",
                    "div.rc h3.r a",
                    0,
                    "body"
            )
    ));

    public static final String[] websitesToremoveDuplicatesFrom = {"google"};

}
