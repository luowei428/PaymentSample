package com.sample.paymentsample

import android.os.AsyncTask
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by weiweiluo on 23/11/17.
 */
class MyAsyncTask(var listener: TaskCompleteListener): AsyncTask<String, Boolean, String>() {


    override fun doInBackground(vararg params: String?): String {
        val response = getClientToken(params[0].toString())
        val status = response.keys.elementAt(0)

        return response.get(status)!!
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        listener.onTaskComplete(result!!)
    }

    fun getClientToken(urlString: String): HashMap<Int, String> {
        var token = ""
        var inputStream: InputStream

        val url = URL(urlString)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        val statusCode = connection.responseCode
        if (statusCode == 200) {
            inputStream = connection.inputStream
            token = inputStream.bufferedReader().use { it.readText() }
        } else {
            inputStream = connection.errorStream
            token = inputStream.bufferedReader().use { it.readText() }
        }

        return hashMapOf(Pair(statusCode, token))
    }
}