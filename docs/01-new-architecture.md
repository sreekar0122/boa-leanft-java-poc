# New Architecture – BOA Mainframe Automation (LeanFT Java)

## Architecture Diagram
```mermaid
flowchart TD
    subgraph SUITE["🧪 TEST SUITE (TestNG)"]
        TS["FtdMainframeTest.java\n@Test · Assertions"]
    end
    subgraph DRIVER["⚙️ EXECUTION ENGINE (BaseTest.java)"]
        DR["SDK Init · Session Lifecycle\n@BeforeSuite · @AfterMethod"]
    end
    subgraph CONFIG["🔧 CONFIGURATION (Settings.java)"]
        CS["TE Host · Port · Timeouts"]
    end
    subgraph DATA["📊 TEST DATA (TestDataProvider.java)"]
        TDM["@DataProvider\nInjects data directly into @Test methods"]
    end
    subgraph ACTION["🛠️ ACTION LAYER (TerminalHelper.java)"]
        TH["Business Keywords:\nlogin() · fundsTransfer() · accountInquiry()"]
    end
    subgraph OR["📦 OBJECT REPOSITORY (Screens.java)"]
        SCR["Strongly-typed Java Application Models\nMainframeScreenDescription"]
    end
    SUITE --> DRIVER
    DATA --> SUITE
    DRIVER --> ACTION
    CONFIG --> DRIVER
    ACTION --> OR
