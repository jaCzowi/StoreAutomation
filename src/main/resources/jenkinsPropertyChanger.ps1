[String]$Browser="browser"
[String]$nameBrowser=$name

gc  src\main\resources\browser.properties | Foreach-Object {
	$_ -replace "$Browser.+", "$Browser=$env:Browser"`
	} | Set-Content src\main\resources\tempBrowser
	rm src\main\resources\browser.properties
	rename-item src\main\resources\tempBrowser browser.properties