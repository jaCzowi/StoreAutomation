[String]$Browser = "browser"
[String]$nameBrowser= $name

gc  browser.properties | Foreach-Object {
	$_ -replace "$Browser.+", "$Browser=$env:Browser"`
	} | Set-Content tempBrowser
	rm browser.properties
	rename-item tempBrowser browser.properties