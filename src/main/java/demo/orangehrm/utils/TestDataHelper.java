package demo.orangehrm.utils;

import java.util.Properties;

public class TestDataHelper {
    private final static String dataLocation = "src/test/resources/data/";
    private final static Properties dataProperties = new Properties();
    public static String getDataOf(String dataKey) {
        if (dataProperties.isEmpty()) {
            new TestDataHelper().loadTestData();
        }
//        String result = dataProperties.getProperty(dataKey);
//        return result == null ? dataKey : result;
        return dataProperties.getProperty(dataKey);
    }

    public void loadTestData() {
        String filePath;
        String commonFilePath = dataLocation + "common.properties";
        String testedEnv = System.getProperty("testedEnv", "production");
        switch (testedEnv) {
            case "dev":
                filePath = dataLocation + "dev.properties";
                break;
            case "staging":
                filePath = dataLocation + "staging.properties";
                break;
            case "production":
                filePath = dataLocation + "production.properties";
                break;
            default:
                throw new RuntimeException(String.format("Unsupported test environment: %s", testedEnv));
        }
        Properties commonProperties = new FileUtil().readPropertiesFIle(commonFilePath);
        Properties specificEnvProperties = new FileUtil().readPropertiesFIle(filePath);
        dataProperties.putAll(commonProperties);
        dataProperties.putAll(specificEnvProperties);
        Log.info(TestDataHelper.class, "Test Data:", "\n", dataProperties);
    }
}
