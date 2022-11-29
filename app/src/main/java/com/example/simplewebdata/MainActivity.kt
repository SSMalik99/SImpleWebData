package com.example.simplewebdata

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler
import okhttp3.Headers
import okhttp3.internal.http2.Header

class MainActivity : AppCompatActivity() {
    var textView: TextView? = null
    var btn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        btn = findViewById<Button>(R.id.Button)

        btn?.setOnClickListener{
            val client = AsyncHttpClient()
            val params = RequestParams()
            params["limit"] = "5"
            params["page"] = "0"
            val url = "https://jsonplaceholder.typicode.com/posts/1" //URL USED
            client[url, params, object :
                TextHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: Headers, response: String) {
                    // called when response HTTP status is "200 OK"
                    val str = response.toString()!!
                    textView!!.text = str
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    textView!!.text = "Error in calling api"
                }
            }]
        }
    }

}