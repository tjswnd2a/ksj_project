package com.example.ksj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WebActivity extends AppCompatActivity {
    private ImageView back;
    private TextView website;
    private WebView web;
    private ProgressBar pBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        back=(ImageView)findViewById(R.id.back);
        web=(WebView)findViewById(R.id.web);
        website=(TextView)findViewById(R.id.website);//웹뷰
        pBar=(ProgressBar)findViewById(R.id.pBar); //로딩바
        pBar.setVisibility(View.GONE); //로딩바 가리기 (로딩할때만 보여야함)
        Intent web_intent= getIntent();

        String info =web_intent.getStringExtra("이벤트");
        website.setText(info);

        WebStieView(info);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void initWebView(){
        web.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pBar.setVisibility(View.VISIBLE);//로딩이 시작되면 로딩바 보이기
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pBar.setVisibility(View.GONE); //로딩이끝나면 로딩바 없애기
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings ws= web.getSettings();
        ws.setJavaScriptEnabled(true);//자바스크립트 사용 허가

    }

    @Override
    public void onBackPressed() {
        if(web.canGoBack()){//이전 페이지가 존재하면
            web.goBack(); //이전 페이지로 돌아간다.
        }
        else {
            super.onBackPressed();
        }
    }

    public void WebStieView(String info)
    {
        initWebView();
        if(info.equals("겟앰프드"))
        {
            web.loadUrl("http://getamped.juneinter.com/sub_main/menu/news/view/news_event.ws");
        }
        else if(info.equals("던전 앤 파이터"))
        {
            web.loadUrl("http://df.nexon.com/df/news/event");
        }
        else if(info.equals("로스트아크"))
        {
            web.loadUrl("https://lostark.game.onstove.com/News/Event/Now");
        }
        else if(info.equals("검은사막"))
        {
            web.loadUrl("https://www.kr.playblackdesert.com/News/Notice?boardType=3");
        }
        else if(info.equals("메이플스토리"))
        {
            web.loadUrl("https://m.maplestory.nexon.com/News/Event");
        }
        else if(info.equals("피파온라인4"))
        {
            web.loadUrl("http://m.fifaonline4.nexon.com/news/events/list");
        }
        else if(info.equals("배틀그라운드"))
        {
            web.loadUrl("https://pubg.game.daum.net/pubg/m/event/progress/index.daum");
        }
        else if(info.equals("카트라이더"))
        {
            web.loadUrl("https://kart.nexon.com/Kart/News/Event/List.aspx");
        }
        else if(info.equals("서든어택"))
        {
            web.loadUrl("http://sa.nexon.com/news/events/list.aspx");
        }
    }

}