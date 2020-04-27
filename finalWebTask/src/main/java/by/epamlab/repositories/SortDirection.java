package by.epamlab.repositories;

public enum SortDirection {
    FEATURED("Featured"){
        @Override
        public String getQuery(){
            return " ORDER BY id ASC";
        };
    },
    PRICE_DESC("Price descending"){
        @Override
        public String getQuery(){
            return " ORDER BY price DESC";
        };
    },
    PRICE_ACS("Price ascending"){
        @Override
        public String getQuery(){
            return " ORDER BY price ASC";
        };
    },
    NAME_DESC("Name descending"){
        @Override
        public String getQuery(){
            return " ORDER BY name DESC";
        };
    },
    NAME_ACS("Name ascending"){
        @Override
        public String getQuery(){
            return " ORDER BY name ASC";
        };
    };
    private String order;

    SortDirection(String order){
        this.order = order;
    };

    public String getOrder() {
        return order;
    }

    public abstract String getQuery();

}
