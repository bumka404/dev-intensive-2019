package ru.skillbranch.devintensive.utils

object Utils {

    val rus : CharArray = charArrayOf('а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я')
    val eng : List<String> = listOf("a", "b", "v", "g", "d", "e", "e", "zh", "z", "i", "i", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f", "h", "c", "ch", "sh", "sh'", "", "i", "", "e", "yu", "ya")

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        val firstName = if (parts?.getOrNull(0).isNullOrEmpty()) null else parts!![0]
        val lastName = if (parts?.getOrNull(1).isNullOrEmpty()) null else parts!![1]
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var name : String = ""
        for(char : Char in payload) {
            if (rus.contains(char.toLowerCase())) {
                name += if (char.isUpperCase()) eng[rus.indexOf(char.toLowerCase())].capitalize() else eng[rus.indexOf(char.toLowerCase())]
            }
            else if (char.toString() == " ") name+= divider
            else name+=char
        }
        return name
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val first: String? =
            if (firstName.isNullOrBlank()) null else firstName[0].toUpperCase().toString()
        val last: String? =
            if (lastName.isNullOrBlank()) null else lastName[0].toUpperCase().toString()

        return if (first == null && last == null) null
        else if (first != null && last == null) first
        else if (last != null && first == null) last
        else "$first$last"
    }
}