package demo.orangehrm.utils;

import demo.orangehrm.pages.BasePage;

import java.util.Properties;

public class TestDataHelper {
    private final static String dataLocation = "src/test/resources/data/";
    private final static String languageDataLocation = String.format("%s%s", dataLocation, "language/");
    private final static String environmentDataLocation = String.format("%s%s", dataLocation, "environment/");
    private final static Properties dataProperties = new Properties();
    public static String getDataOf(String dataKey) {
        if (dataProperties.isEmpty()) {
            TestDataHelper testDataHelper = new TestDataHelper();
            testDataHelper.loadEnvironmentTestData();
            testDataHelper.loadLanguageTestData();
        }
//        String result = dataProperties.getProperty(dataKey);
//        return result == null ? dataKey : result;
        return dataProperties.getProperty(dataKey);
    }

    public void loadEnvironmentTestData() {
        String filePath;
        String commonFilePath = environmentDataLocation + "common.properties";
        String testedEnv = System.getProperty("testedEnv", "production");
        switch (testedEnv) {
            case "dev":
                filePath = environmentDataLocation + "dev.properties";
                break;
            case "staging":
                filePath = environmentDataLocation + "staging.properties";
                break;
            case "production":
                filePath = environmentDataLocation + "production.properties";
                break;
            default:
                throw new RuntimeException(String.format("Unsupported test environment: %s", testedEnv));
        }
        Properties commonProperties = new FileUtil().readPropertiesFIle(commonFilePath);
        Properties specificEnvProperties = new FileUtil().readPropertiesFIle(filePath);
        commonProperties.putAll(specificEnvProperties);
        dataProperties.putAll(commonProperties);
        Log.info(TestDataHelper.class, "Test Data Of Environment:", "\n", commonProperties);
    }

    public void loadLanguageTestData() {
        String testedLanguage = BasePage.testedLanguage;
        String filePath = languageDataLocation + testedLanguage + ".properties";
        Properties specificLanguageProperties = new FileUtil().readPropertiesFIle(filePath);
        dataProperties.putAll(specificLanguageProperties);
        Log.info(TestDataHelper.class, "Test Data Of Language:", "\n", specificLanguageProperties);
    }
}
