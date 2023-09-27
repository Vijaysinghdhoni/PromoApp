package com.vijaydhoni.selfpromoapp


data class Message(
    val contactname: String,
    val contactnumber: String,
    val displayname: String,
    val juniorcheckbox: Boolean?,
    val itemspinner: String,
    val immediatestart: Boolean?,
    val date: String
) : java.io.Serializable {
    fun jobdiscription() =
        if (juniorcheckbox == true) "a junior $itemspinner" else "an $itemspinner"

    fun startpositon() = if (immediatestart == true) "immediately" else "from $date"
}