package com.eden.orchid.utilities

public fun String.encodeSpaces(): String {
    var output = ""
    var inTag = false

    for (i in 0 until this.length) {
        if(inTag) {
            if(this[i] == '>') {
                inTag = false
                output += this[i]
            }
            else {
                output += this[i]
            }
        }
        else {
            if(this[i] == '<') {
                inTag = true
                output += this[i]
            }
            else if(this[i] == ' ') {
                println("encodeSpaces: converting space")
                output += "&nbsp;"
            }
            else if(this[i] == '\t') {
                println("encodeSpaces: converting tab")
                output += "&emsp;"
            }
            else if(this[i] == '\n') {
                println("encodeSpaces: converting newline")
                output += "<br>"
            }
            else {
                output += this[i]
            }
        }
    }

    return output
}

public fun String.nl2br(): String {
    return this.replace("\\n".toRegex(), "<br>")
}
