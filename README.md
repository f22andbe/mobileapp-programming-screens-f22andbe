
# Rapport

**Skriv din rapport här!**


För att lösa denna uppgift har ett program skrivits som tar en url som input ifrån användaren, startar en ny aktivitet, skickar med nämnda url till den nya aktivteten och laddar denna i en WebView. Programmet har två aktiviteter, MainActivity och VideoActivity(namnet pga att en video visas om ingen url fylls i). 

------------------------
![](first_activity.png) | ![](second_activity_with_default_url.png)



I MainActivity finns en TextView med ett förklarande meddelande och en knapp som skapar den nya aktiviteten vid intryckning. Den url som laddas i den nya aktiviteten har skickats till den med en intent. Har användaren inte fyllt i någon url så skickas istället en default-url till aktiviteten. Detta sker i händelsehanteraren för knappen "Switch activity".   

```
public void onButtonClick(View view) {
        Intent switchIntent = new Intent(this, VideoActivity.class);
        String url = urlField.getText().toString();
		
		 /* if nothing has been entered by user we send a default url with the intent
         * otherwise we extract the entered string and send that 
         */
        if(url.isEmpty()) {
            switchIntent.putExtra("url", defaultUrl);
        }else {
            if( ! ( url.startsWith("http://") ) || url.startsWith(("https://") )) {
                url = "https://" + url;
            }
            switchIntent.putExtra("url", url);
        }
      
        startActivity(switchIntent);
        urlField.setText("", TextView.BufferType.EDITABLE); // set content of field to empty string
                                                            // in case the user returns to this activity
}
```        

Händelsehanteraren registrerades i knappens resurs-definition i activity_main.xml

För att skapa en till aktivitet så var tillägg tvunget att göras i AndroidManifest.xml i form av ett activity element där namnet på klassen som utgör den nya aktiviteten specificerades. 

```
<activity android:name=".VideoActivity">
     android:parentActivityName=".MainActivity"
</activity>
```

Denna nya aktivitet har en LinearLayout och en WebView, dessa definieras i layout-filen res/layout/activity_video.xml. Att det är denna som används försäkras genom anrop till setContenView() i VideoActivity.java i början av dess onCreate() metod. 

```
@Override
protected void onCreate(Bundle SavedInstanceState) {
     super.onCreate(SavedInstanceState);
     setContentView(R.layout.activity_video);
```
            
I VideoActivity.java så laddas en WebView med hjälp av findViewById() och kopplas till en  WebVeiwClient för att den laddade url:en skall visas i appen och inte en extern webbläsare.  

```
@Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_video);
       
        intent = getIntent();
        webview = findViewById(R.id.youtube);
        webview.setWebViewClient(new WebViewClient());
        webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        

        String url = intent.getStringExtra("url"); // URL is extracted from intent here
        Log.i("VideoActivity", "url from intent is " + url);
        webview.loadUrl(url);
    }
```




I VideoActivity så definierades även händelsehanteraren onBackPressed() för att det skulle gå att gå bakåt till den tidigare aktiviteten och även navigera sig om användaren klickat sig framåt till andra hemsidor med hjälp av länkar. 

```
    /* This event handler allows the user to go back to the previous activity by
     * clicking the system back button and also navigate among sites
     */
    @Override
    public void onBackPressed() {
        if(webview.canGoBack())
            webview.goBack();
        else
            super.onBackPressed();

    } 
```
    