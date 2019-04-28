package com.sergiocruz.recyclerviewlisteners

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy


class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val logStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
            .methodCount(5)         // (Optional) How many method line to show. Default 2
            .methodOffset(1)        // (Optional) Hides internal method calls up to offset. Default 5
            //.logStrategy() // (Optional) Changes the log strategy to print out. Default LogCat
            .tag("Sergio> ")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()

        Logger.addLogAdapter(AndroidLogAdapter(logStrategy))
    }
}