package com.example.powernote

import android.graphics.Path

data class FingerPath(
    var color: Int,
    var emboss: Boolean,
    var blur: Boolean,
    var strokeWidth: Int,
    var path: Path
)