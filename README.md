# orangehrm automation project


DEVELOP AUTOMATION PROJECT:
1. Tools: Selenium WebDriver
2. Programing Language: Java ()
3. Framework: TestNG/Maven

Build
- Multiple browsers
- Multiple language
- Multiple platform
- Multiple test environment
  =>POM Design pattern: A page map a corresponding java class
- Easy to scale up
- Easy to maintain
- Easy to read/approach

Command to run test: =>Decide code

`mvn test -Dtest=TestLogin -Dbrowser=chrome/edge/firefox -Dlanguage=english -Dplatform=desktop -DtestedEnv=prod`
`mvn test -Dtest=<className>#<methodName> -Dbrowser=chrome/edge/firefox -Dlanguage=english -Dplatform=desktop -DtestedEnv=prod`


## CONVENTION

### -Dlanguage accepts

- List = {english, vietnamese, chinese, german, italian, korean, japanese}

### Properties Data File

- Folder & File Name:

  - Folder environment =>Contains environment properties file
    - {environmentName}.properties => Test data of specific environment (e.g: dev.properties)
    - common.properties => Common test data across all environments

  - Folder language =>Contains langauge properties file
    - {language}.properties => Test data of specific language (e.g: vietnamese.properties)
    - Note: prefix {language} must be equal system property value of -Dlanguage (e.g: -Dlanguage-english, then file name must be english.properties)

- Key Name: 
  - lower case
  - separator of each word is underscore (_)
        
        
