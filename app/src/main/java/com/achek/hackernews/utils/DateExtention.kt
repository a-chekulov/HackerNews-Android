package com.achek.hackernews.utils

import java.text.SimpleDateFormat
import java.util.*


fun Date.dayMonthYearHourMinuteFormat(): String =
    SimpleDateFormat("d MMM yyyy, HH:mm", Locale.getDefault()).format(this)