package Springweb.service;

import java.util.List;

public class ChartResponse {
    public List<String> labels;
    public List<DataSet> datasets;

    public static class DataSet {
        public String label;
        public List<Integer> data;
        public List<String> backgroundColor;
        public List<String> borderColor;
        public int borderWidth;
    }
}
