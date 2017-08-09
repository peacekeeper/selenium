SetIt-Credit extensions to Selenium-Grid-Extras
===============================================

This documents a set up SetIt-Credit specific extensions to the Selenium-Grid-Extras codebase.

# #1: Password-protected nodes

A job has to send a "password capability" which has to match the corresponding capability
at the nodes.

Example Java code that sets the password "secret!" for a job:

	@Before
	public void setUp() throws Exception {
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setCapability("jobuuid", "3dbf98d8-a0a6-4b01-9432-0a6811c77f59");
        capability.setCapability("password", "secret!");
        capability.setCapability("platform", "LINUX");
        // capability.SetCapability("version", "46");
        //capability.SetCapability("video", "False");
        URL markushub = new URL("http://11347-04.root.nessus.at:4444/wd/hub/");
        driver = new RemoteWebDriver(markushub, capability);
	}

Example hub configuration using `SetItCreditCapabilityMatcher`:

	{
	  "port": 4444,
	  "newSessionWaitTimeout": 25000,
	  "servlets": [
	    "com.groupon.seleniumgridextras.grid.servlets.ProxyStatusJsonServlet"
	  ],
	  "capabilityMatcher": "com.setitcredit.SetItCreditCapabilityMatcher",
	  "throwOnCapabilityNotPresent": true,
	  "nodePolling": 5000,
	  "cleanUpCycle": 5000,
	  "browserTimeout": 120000,
	  "timeout": 120000,
	  "maxSession": 5
	}

Example node configuration that requires the password "secret!":

	{
	  "capabilities": [
	    {
	      "seleniumProtocol": "WebDriver",
	      "browserName": "firefox",
	      "maxInstances": 3,
	      "version": "",
	      "platform": "LINUX",
	      "password": "secret!"
	    }
	  ],
	  "loadedFromFile": "node_5555.json",
	  "proxy": "com.setitcredit.SetItCreditProxy",
	  "maxSession": 3,
	  "port": 5555,
	  "register": true,
	  "unregisterIfStillDownAfter": 10000,
	  "hubPort": 4444,
	  "hubHost": "127.0.0.1",
	  "registerCycle": 5000,
	  "nodeStatusCheckTimeout": 10000,
	  "downPollingLimit": 0
	}

# #2: JOBUUID Capability

Note that the above example also includes a "jobuuid" capability, which will be included in log files.

This requires no additional configuration.

Example:

	10:34:32.890 INFO - Creating a new session for Capabilities [{jobuuid=8624052b-7ded-46b0-a53b-0ef4278a4464, password=**OMITTED: PASSWORD**, marionette=true, browserName=firefox, version=, platform=LINUX}]

# #3: PrivacyLogger

Some information is automatically filtered from log files using the `PrivacyLogger`, `FilterCreditCardNumbers`, `FilterPasswords` and `FilterEmailAddresses` classes.

This requires no additional configuration.

Example:

	10:35:02.874 INFO - Executing: [send keys: 2 [[FirefoxDriver: Firefox on LINUX (dc810b0e-3f47-4e88-b912-d3f8557ce7dc)] -> id: card_number], [**OMITTED: CREDIT CARD** with address **OMITTED: EMAIL**]])

# #4. DataDog Metrics

The `SetItCreditProxy` will submit metrics to a local DataDog agent about job
commands and sessions.

The following metrics and service checks are submitted to DataDog:

 * TBD
 
# #5. Grid Check-in to Grid Status Service

The `GridStatusUpdate` class will call the Grid Status Service with the following information stored in a **statusupdate.properties** file:

 * Grid UUID (configured with **'grid.uuid'** environment variable)
 * Grid Name (configured with **'grid.name'** environment variable)
 * Grid Location (configured with **'grid.location'** environment variable)
 * Grid AuthID (configured with **'grid.authid'** environment variable)
 * Grid AuthKey (configured with **'grid.authkey'** environment variable)

E.g. Selenium-Grid-Extras can be launched as follows:

	java -jar SeleniumGridExtras-1.12.12-SNAPSHOT-jar-with-dependencies.jar

With a **statusupdate.properties** file as follows:

	grid.uuid=d074e153-1c30-4d22-a769-b0d31f992fd1
	grid.name=mygrid1
	grid.location=Austria
	grid.authid=azuredevgrid
	grid.authkey=AAAERWEFW@23435767889REVoiwewoie

# #6. Dockerfiles

Two Dockerfiles are provided, one for a "hub" profile, and one for a "node" profile.

## Dockerfile-SetIt-Credit-hub

	docker run \
	-e griduuid='d074e153-1c30-4d22-a769-b0d31f992fd1' \
	-e gridname='markustest' \
	-e gridlocation='Austria' \
	-e gridauthid='markustest' \
	-e gridauthkey='AAAERWEFW@23435767889REVoiwewoie' \
	38e35dc6c8f7

## Dockerfile-SetIt-Credit-node

	docker run \
	-e hubhost='172.17.0.2' \
	cc6477b149c5
