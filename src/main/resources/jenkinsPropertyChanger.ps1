[String]$Browser = "browser"

gc  browser.properties | Foreach-Object {
	$_ -replace "$Browser.+", "$Browser=$env:Browser" `
	} | Set-Content tempBrowser
	rm browser.properties
	rename-item  browser.properties