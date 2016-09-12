package android.org.easystogu;

import android.app.Activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;

//refer to https://developer.android.com/guide/webapps/webview.html
public class MainActivity extends Activity {

    private static final String TAG = "Android";
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView)findViewById(R.id.webView);
        //webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.addJavascriptInterface(new WebAppInterface(this), TAG);
        webView.loadUrl("file:///android_asset/index.htm");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}
