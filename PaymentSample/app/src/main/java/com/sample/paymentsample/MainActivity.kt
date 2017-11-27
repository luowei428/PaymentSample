package com.sample.paymentsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.braintreepayments.api.dropin.DropInRequest

import kotlinx.android.synthetic.main.activity_main.*
import com.braintreepayments.api.dropin.DropInActivity
import android.app.Activity
import com.braintreepayments.api.dropin.DropInResult
import android.content.Intent
import android.preference.PreferenceActivity
import android.util.Log
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.RequestParams
import com.loopj.android.http.TextHttpResponseHandler
import java.net.URL
import com.paypal.android.sdk.onetouch.core.metadata.d
import cz.msebera.android.httpclient.Header


class MainActivity : AppCompatActivity(), TaskCompleteListener {
    private val TAG = "MainActivity"
    companion object {
        val BRAINTREE_REQUEST_CODE = 4949
    }
    var clientToken: String? = null

    override fun onTaskComplete(result: String) {
        System.out.println(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getClientTokenFromServer()
        btnPaypal.setOnClickListener {
            onBraintreeSubmit()
            //MyAsyncTask(this).execute("https://braintree-paypal-pest.herokuapp.com/checkouts")
            //MyAsyncTask(this).execute("http://localhost:8080/braintree")
            /*var url = URL("https://braintree-paypal-pest.herokuapp.com/client_token")
            val client = url.openConnection()
            client.get("https://braintree-paypal-pest.herokuapp.com/client_token", object : TextHttpResponseHandler() {
                fun onSuccess(statusCode: Int, headers: Array<PreferenceActivity.Header>, clientToken: String) {
                    this.clientToken = clientToken
                }
            })*/
                /*val dropInRequest = DropInRequest()
                        .clientToken("eyJ2ZXJzaW9uIjoyLCJhdXRob3JpemF0aW9uRmluZ2VycHJpbnQiOiI1MTVjMjcyMDgwYzNhOTA1MzQ1NmQ0ZWM1MTBjOGQxYzAwZWQ4ZmJkMzkyYWM1OTg0ZjVjMjUwZTc4ODM1MmE1fGNyZWF0ZWRfYXQ9MjAxNy0xMS0yMlQwNTo0NjoxNi4wNTM0NDAzNjkrMDAwMFx1MDAyNm1lcmNoYW50X2lkPTM0OHBrOWNnZjNiZ3l3MmJcdTAwMjZwdWJsaWNfa2V5PTJuMjQ3ZHY4OWJxOXZtcHIiLCJjb25maWdVcmwiOiJodHRwczovL2FwaS5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tOjQ0My9tZXJjaGFudHMvMzQ4cGs5Y2dmM2JneXcyYi9jbGllbnRfYXBpL3YxL2NvbmZpZ3VyYXRpb24iLCJjaGFsbGVuZ2VzIjpbXSwiZW52aXJvbm1lbnQiOiJzYW5kYm94IiwiY2xpZW50QXBpVXJsIjoiaHR0cHM6Ly9hcGkuc2FuZGJveC5icmFpbnRyZWVnYXRld2F5LmNvbTo0NDMvbWVyY2hhbnRzLzM0OHBrOWNnZjNiZ3l3MmIvY2xpZW50X2FwaSIsImFzc2V0c1VybCI6Imh0dHBzOi8vYXNzZXRzLmJyYWludHJlZWdhdGV3YXkuY29tIiwiYXV0aFVybCI6Imh0dHBzOi8vYXV0aC52ZW5tby5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tIiwiYW5hbHl0aWNzIjp7InVybCI6Imh0dHBzOi8vY2xpZW50LWFuYWx5dGljcy5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tLzM0OHBrOWNnZjNiZ3l3MmIifSwidGhyZWVEU2VjdXJlRW5hYmxlZCI6dHJ1ZSwicGF5cGFsRW5hYmxlZCI6dHJ1ZSwicGF5cGFsIjp7ImRpc3BsYXlOYW1lIjoiQWNtZSBXaWRnZXRzLCBMdGQuIChTYW5kYm94KSIsImNsaWVudElkIjpudWxsLCJwcml2YWN5VXJsIjoiaHR0cDovL2V4YW1wbGUuY29tL3BwIiwidXNlckFncmVlbWVudFVybCI6Imh0dHA6Ly9leGFtcGxlLmNvbS90b3MiLCJiYXNlVXJsIjoiaHR0cHM6Ly9hc3NldHMuYnJhaW50cmVlZ2F0ZXdheS5jb20iLCJhc3NldHNVcmwiOiJodHRwczovL2NoZWNrb3V0LnBheXBhbC5jb20iLCJkaXJlY3RCYXNlVXJsIjpudWxsLCJhbGxvd0h0dHAiOnRydWUsImVudmlyb25tZW50Tm9OZXR3b3JrIjp0cnVlLCJlbnZpcm9ubWVudCI6Im9mZmxpbmUiLCJ1bnZldHRlZE1lcmNoYW50IjpmYWxzZSwiYnJhaW50cmVlQ2xpZW50SWQiOiJtYXN0ZXJjbGllbnQzIiwiYmlsbGluZ0FncmVlbWVudHNFbmFibGVkIjp0cnVlLCJtZXJjaGFudEFjY291bnRJZCI6ImFjbWV3aWRnZXRzbHRkc2FuZGJveCIsImN1cnJlbmN5SXNvQ29kZSI6IlVTRCJ9LCJtZXJjaGFudElkIjoiMzQ4cGs5Y2dmM2JneXcyYiIsInZlbm1vIjoib2ZmIn0=")
                startActivityForResult(dropInRequest.getIntent(this), 0)*/
        }
    }

    fun onBraintreeSubmit() {
        val dropInRequest = DropInRequest().clientToken(clientToken)
        startActivityForResult(dropInRequest.getIntent(this), BRAINTREE_REQUEST_CODE)
    }

    private fun getClientTokenFromServer() {
        val client = AsyncHttpClient();
        client.get("http://127.0.0.1:8080/braintree", object : TextHttpResponseHandler() {
            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {
                Log.i(TAG, getString(R.string.token_failed) + responseString)
            }

            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseToken: String?) {
                Log.d(TAG, "Client token: " + responseToken)
                clientToken = responseToken
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == BRAINTREE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val result = data.getParcelableExtra<DropInResult>(DropInResult.EXTRA_DROP_IN_RESULT)
                val paymentNonce = result.paymentMethodNonce?.nonce!!

                Log.d(TAG, "Testing the app here")
                sendPaymentNonceToServer(paymentNonce)
                // use the result to update your UI and send the payment method nonce to your server
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // the user canceled
            } else {
                // handle errors here, an exception may be available in
                val error = data.getSerializableExtra(DropInActivity.EXTRA_ERROR) as Exception
                System.out.println(error.localizedMessage)
            }
        }
    }

    private fun sendPaymentNonceToServer(paymentNonce: String) {
        val params = RequestParams("NONCE", paymentNonce)
        val androidClient = AsyncHttpClient()
        androidClient.post("http://127.0.0.1:8080/braintree/", params, object : TextHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseString: String?) {
                Log.i(TAG, "Output " + responseString)
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {
                Log.i(TAG, "Error: Failed to create a transaction")
            }

        })
    }
}
