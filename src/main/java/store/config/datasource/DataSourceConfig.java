package store.config.datasource;

public class DataSourceConfig {

    public DataSource dataSource() {
        return new DataSource(dataSourceReader(), dataSourceWriter());
    }

    public DataSourceReader dataSourceReader() {
        return new DataSourceReader();
    }

    public DataSourceWriter dataSourceWriter() {
        return new DataSourceWriter();
    }
}
