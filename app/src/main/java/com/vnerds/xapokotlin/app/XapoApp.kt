package com.vnerds.xapokotlin.app

import android.app.Application

import com.vnerds.xapokotlin.api.RestClient


class XapoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        restClient = RestClient()

    }

    companion object {
        var restClient: RestClient? = null
            private set
    }
}
