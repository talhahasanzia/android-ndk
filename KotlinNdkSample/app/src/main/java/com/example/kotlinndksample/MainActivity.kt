package com.example.kotlinndksample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    companion object {

        /* this is used to load the 'hello-jni' library on application
         * startup. The library has already been unpacked into
         * /data/data/com.example.kotlinjni/lib/libkotlin-jni.so at
         * installation time by the package manager.
         */
        init {
            System.loadLibrary("kotlin-jni")
        }
    }

    private lateinit var jniMessageTextView: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jniMessageTextView = findViewById(R.id.jni_message_textview) as TextView
    }

    override fun onResume() {
        super.onResume()

        showJniMessage()
    }

    external fun stringFromJNI(): String


    external fun intFromJNI(): Int


    private fun showJniMessage() {
        val stringFromInt = StringBuilder().append(intFromJNI()).toString()
        Log.d("JNI_CALL: ", stringFromInt)
        Log.d("JNI_CALL: ", stringFromJNI())

        var stringsFromJni = StringBuilder();

        val separator = " + "

        stringsFromJni.append(intFromJNI())
        stringsFromJni.append(separator)
        stringsFromJni.append(stringFromJNI())
        stringsFromJni.append(separator)



        jniMessageTextView.text = stringsFromJni.toString()
    }
}
